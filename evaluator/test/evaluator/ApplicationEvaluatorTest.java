package evaluator;

import criteria.Applicant;
import criteria.Criteria;
import criteria.EvaluationStatus;
import criteria.Result;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class ApplicationEvaluatorTest {
  private ApplicationEvaluator applicationEvaluator;
  private Map<String, Criteria> criteria;
  private List<Criteria> selectedCriteria;

  @BeforeEach
  void init() {
    applicationEvaluator = new ApplicationEvaluator();
    criteria = applicationEvaluator.getCriteria()
      .stream()
      .collect(
        Collectors.toMap(
          Criteria::getName,
          criterion -> criterion
        ));

    selectedCriteria = new ArrayList<>();
  }

  @Test
  void getCriteriaTest(){

    assertTrue(criteria.containsKey("Employment Check"));

    assertTrue(criteria.containsKey("Credit Report Check"));

    assertTrue(criteria.containsKey("Criminal Record Check"));

    assertTrue(criteria.containsKey("Security Clearance Check"));

  }

  @Test
  void getSelectedCriteriaTest(){
    List<String> selectedCriteriaNames = new ArrayList<>();

    selectedCriteriaNames.add("Employment Check");
    selectedCriteriaNames.add("Criminal Record Check");

    List<Criteria> criteriaList = applicationEvaluator
      .getSelectedCriteria(applicationEvaluator.getCriteria(),
        selectedCriteriaNames);

    assertEquals("class employmentcheck.EmploymentCheck",
      criteriaList.get(0).getClass().toString());

    assertEquals("class criminalcheck.CriminalRecordCheck",
      criteriaList.get(1).getClass().toString());
  }

  @Test
  void evaluateApplicantNoCriteria(){

    Result result = applicationEvaluator.evaluateApplicant(new ArrayList<>(), new Applicant());

    assertEquals(EvaluationStatus.FAIL, result.response);

    assertEquals("No criteria was provided", result.detail);
  }

  @Test
  void evaluateApplicantOnePassingCriteria() {

    selectedCriteria.add(criteria.get("Employment Check"));

    var result = applicationEvaluator.evaluateApplicant(selectedCriteria,
      new Applicant());

    assertEquals(EvaluationStatus.PASS, result.response);

    assertEquals("Applicant has previous employment",
      result.detail);
  }

  @Test
  void evaluateApplicantOneFailingCriteria(){

    selectedCriteria.add(criteria.get("Criminal Record Check"));

    var result = applicationEvaluator.evaluateApplicant(selectedCriteria,
      new Applicant());

    assertAll(
      ()->assertEquals(EvaluationStatus.FAIL, result.response),
      ()->assertEquals("Applicant has done time for robbery",
        result.detail)
    );
  }

  @Test
  void evaluateApplicantTwoFailingCriteria(){

    selectedCriteria.add(criteria.get("Credit Report Check"));
    selectedCriteria.add(criteria.get("Employment Check"));

    var result = applicationEvaluator.evaluateApplicant(selectedCriteria,
      new Applicant());

    assertAll(
      ()->assertEquals(EvaluationStatus.PASS, result.response),
      ()->assertEquals("Applicant has good credit, " +
          "Applicant has previous employment",
        result.detail)
    );

  }

  @Test
  void evaluateApplicantThreeCriteria(){

    selectedCriteria.add(criteria.get("Credit Report Check"));
    selectedCriteria.add(criteria.get("Security Clearance Check"));
    selectedCriteria.add(criteria.get("Employment Check"));

    var result = applicationEvaluator.evaluateApplicant(selectedCriteria,
      new Applicant());

    assertAll(
      ()->assertEquals(EvaluationStatus.FAIL, result.response),
      ()->assertEquals("Applicant has good credit, " +
          "Applicant does not have a security clearance, " +
          "Applicant has previous employment",
        result.detail)
    );
  }
}
