package securityclearance;

import criteria.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SecurityClearanceCheckTest implements CriteriaTest {
  private SecurityClearanceCheck securityClearanceCheck;

  public Criteria createCriteria() {
    return new SecurityClearanceCheck();
  }

  public Result getEvaluationStatus() {
    return new Result(EvaluationStatus.FAIL,"Applicant does not have a security clearance" );
  }

  public String getExpectedName() {
    securityClearanceCheck = new SecurityClearanceCheck();
    return securityClearanceCheck.getName();
  }
}
