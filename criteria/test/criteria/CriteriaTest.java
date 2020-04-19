package criteria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public interface CriteriaTest {

  Criteria createCriteria();
  Result getEvaluationStatus();
  String getExpectedName() ;

  @Test
  default void canary(){
    assertTrue(true);
  }

  @Test
  default void checkResult() {
    var background = createCriteria();
    Result result = background.check(new Applicant());

    assertEquals(this.getEvaluationStatus().response,
      result.response);

    assertEquals(this.getEvaluationStatus().detail,
      result.detail);
  }

  @Test
  default void checkCriteriaName() {
    var background = createCriteria();
    assertEquals(this.getExpectedName(), background.getName());
  }

  @Test
  default void checkResultClass(){
    Result result = new Result(EvaluationStatus.PASS, "Result Class Test");

    assertEquals(EvaluationStatus.PASS, result.response);

    assertEquals("Result Class Test", result.detail);
  }


}
