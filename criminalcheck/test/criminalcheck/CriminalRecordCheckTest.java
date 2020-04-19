package criminalcheck;

import criteria.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriminalRecordCheckTest implements CriteriaTest {
  private CriminalRecordCheck criminalRecordCheck;

  public Criteria createCriteria() {
    return new CriminalRecordCheck();
  }

  public Result getEvaluationStatus() {
    return new Result(EvaluationStatus.FAIL,"Applicant has done time for robbery" );
  }

  public String getExpectedName() {
    criminalRecordCheck = new CriminalRecordCheck();
    return criminalRecordCheck.getName();
  }
}
