package evaluator.ui;

import criteria.Applicant;
import criteria.Criteria;
import criteria.Result;
import evaluator.ApplicationEvaluator;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

  private static ApplicationEvaluator applicationEvaluator = new ApplicationEvaluator();
  private static List<Criteria> criteria = applicationEvaluator.getCriteria();

  public static void main(String[] args) {
    String yOrNKey = "Y";

    while (yOrNKey.equals("Y")) {
      Scanner scanner = new Scanner(System.in);

      String applicant;
      System.out.println("\nEnter the name of the Applicant");
      applicant = scanner.nextLine();

      List<Criteria> selectedCriteria;
      boolean successful = false;
      do {
        displayCriteriaOption();

        System.out.println("\n Enter the criteria(number), separated by a space");
        String inputSeleciton = scanner.nextLine();

        try {
          selectedCriteria = getSelectionList(inputSeleciton);
          printSelectedCriteria(selectedCriteria);

          Result result = applicationEvaluator.evaluateApplicant(selectedCriteria, new Applicant());
          printResult(applicant, result);

          successful = true;
        } catch (Exception e) {
          System.out.println("Please enter valid option");
        }
      } while (!successful);

      System.out.println("\nWould you like another evaluation? Press 'Y' to continue, any other key to exit ");
      yOrNKey = scanner.nextLine().toUpperCase().trim();
    }
  }

  private static void displayCriteriaOption(){
    System.out.println("Please enter the Criteria you would like to evaluate from the option below\n");

    for (int i = 0; i < criteria.size(); i++) {
      System.out.println(i + " : " + criteria.get(i).getName());
    }
  }

  private static List<Criteria> getSelectionList(String inputSeleciton) {
    return Stream.of(inputSeleciton.split(" "))
      .map(x -> criteria.get(Integer.parseInt(x)))
        .collect(Collectors.toList());
  }

  private static void printResult(String applicant, Result result) {
    System.out.println("\n" + applicant + "'s Evaluation Result: \n" + result.response + "\n" + result.detail);
  }

  private static void printSelectedCriteria(List<Criteria> selectedCriteria) {
    System.out.println("\nYou selected " +
      selectedCriteria.stream()
        .map(Criteria::getName)
          .collect(Collectors.joining(", "))
    );
  }

}
