package criteria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EvaluationStatusTest {
  @Test
  public void EvaluationTest(){
    EvaluationStatus status = EvaluationStatus.FAIL;
    assertEquals(EvaluationStatus.FAIL,status);
  }
}
