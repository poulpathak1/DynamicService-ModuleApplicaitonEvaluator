package creditcheck;

import criteria.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CreditReportCheckTest implements CriteriaTest {
  private CreditReportCheck creditReportCheck;

  public Criteria createCriteria() {
    return new CreditReportCheck();
  }

  public Result getEvaluationStatus() {
    return new Result(EvaluationStatus.PASS, "Applicant has good credit");
  }

  public String getExpectedName() {
    creditReportCheck = new CreditReportCheck();
    return creditReportCheck.getName();
  }
}
