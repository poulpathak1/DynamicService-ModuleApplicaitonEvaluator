package criteria;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ResultTest {
  @Test
  public void resultTest(){
    Result result = new Result(EvaluationStatus.FAIL, "Test");

    assertEquals(EvaluationStatus.FAIL, result.response);
    assertEquals("Test", result.detail);
  }
}
