package employmentcheck;

import criteria.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmploymentCheckTest implements CriteriaTest {
  private EmploymentCheck employmentCheck;

  public Criteria createCriteria() {
    return new EmploymentCheck();
  }

  public Result getEvaluationStatus() {
    return new Result(EvaluationStatus.PASS,"Applicant has previous employment" );
  }

  public String getExpectedName() {
    employmentCheck = new EmploymentCheck();
    return employmentCheck.getName();
  }
}
