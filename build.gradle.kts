plugins {
  java
  pmd
}       

var baseTestModule = "criteria"

fun getModuleName(javaSource: SourceDirectorySet): String {
  val regEx = "module (\\w+) \\{".toRegex()
  
  return regEx.find(File(javaSource.filter { it.name == "module-info.java" }.asPath).readText())?.groups?.get(1)?.value ?: "notfound"
}

fun getPackageNames(javaSource: SourceDirectorySet): List<String> {
  return javaSource.filter { it.path.endsWith(".java") }
    .map { it.getParentFile().path }
    .map { sourcePath ->
      javaSource.getSrcDirs()
       .fold(sourcePath) { path, parentDir ->
         path.removePrefix(parentDir.path)
      }
    }
    .map { it.removePrefix("/") }
    .map { it.removePrefix("\\") }
    .filter { !it.isEmpty() }
    .map { it.replace("/", ".")}
    .map { it.replace("\\", ".")}
}

subprojects {
  apply {
    plugin("java")
    plugin("jacoco")
    plugin("pmd")
  }

  repositories {
    mavenCentral()
  }

  val junit by configurations.creating

  dependencies {
    junit("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.2.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.2.0")
    testRuntimeOnly("org.junit.platform:junit-platform-console:1.2.0")
  }

  tasks {
    getByName<JacocoReport>("jacocoTestReport") {
      afterEvaluate {
        classDirectories.setFrom(files(classDirectories.files.map {
          fileTree(it) { exclude("**/evaluator/ui/**") }
        }))
      }
    }

    val compilerOptions =
      listOf("-Xlint:unchecked", "-Xlint:deprecation", "-Werror", "-p", "$rootDir/build/libs")

    getByName<JavaCompile>("compileJava") {
      options.compilerArgs = compilerOptions
    }

    getByName<JavaCompile>("compileTestJava") {
        options.compilerArgs = compilerOptions
    }
  }

  sourceSets {
    main {
      java.srcDirs("src")
    }
    test {
      java.srcDirs("test")
    }
  }
  
  val test by tasks.getting(Test::class) {
    useJUnitPlatform {}
      
    val moduleName = getModuleName(sourceSets["main"].java)
    val openPackages = getPackageNames(sourceSets["main"].java).map { 
      aPackage -> listOf("--add-opens", "$moduleName/$aPackage=org.junit.platform.commons")
    }.flatten()
    
    jvmArgs = listOf(
      "-p", "$rootDir/build/libs${File.pathSeparator}${junit.asPath}",
      "--add-modules", "$moduleName",
      "--add-modules", "org.junit.jupiter.api",
      "--add-reads", "$moduleName=org.junit.jupiter.api",
      "--add-reads", "$baseTestModule=org.junit.jupiter.api",
      "--patch-module", "$moduleName=${files(sourceSets["test"].output).asPath}"
    ) + openPackages
  }

  pmd {
      ruleSets = listOf()
      ruleSetFiles = files("../../conf/pmd/ruleset.xml")
      toolVersion = "6.22.0"      
  }                                                

  tasks.withType<Jar> {
    destinationDir = file("$rootDir/build/libs")
    from(sourceSets["test"].output)
  }
}

project(":employmentcheck") {
  val baseTestDependency = files(project(":criteria").sourceSets["test"].output)
  
  dependencies {
    implementation(project(":criteria"))
    testImplementation(baseTestDependency)
  }
}

project(":criminalcheck") {
  val baseTestDependency = files(project(":criteria").sourceSets["test"].output)

  dependencies {
    implementation(project(":criteria"))
    testImplementation(baseTestDependency)
  }
}

project(":creditcheck") {
  val baseTestDependency = files(project(":criteria").sourceSets["test"].output)

  dependencies {
    implementation(project(":criteria"))
    testImplementation(baseTestDependency)
  }
}

project(":securityclearance") {
  val baseTestDependency = files(project(":criteria").sourceSets["test"].output)

  dependencies {
    implementation(project(":criteria"))
    testImplementation(baseTestDependency)
  }
}

project(":evaluator") {
  val baseTestDependency = files(project(":criteria").sourceSets["test"].output)

  dependencies {
    implementation(project(":criteria"))
    implementation(project(":employmentcheck"))
    implementation(project(":criminalcheck"))
    implementation(project(":creditcheck"))
    implementation(project(":securityclearance"))
    testImplementation(baseTestDependency)
  }
}

defaultTasks("clean", "jar", "test", "jacocoTestReport", "pmdMain")
