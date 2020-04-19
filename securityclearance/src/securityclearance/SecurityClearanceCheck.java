package securityclearance;

import criteria.Applicant;
import criteria.Criteria;
import criteria.EvaluationStatus;
import criteria.Result;

public class SecurityClearanceCheck implements Criteria {

  public String getName() {
    return "Security Clearance Check";
  }

  public Result check(Applicant applicant) {
    return new Result(EvaluationStatus.FAIL,
     "Applicant does not have a security clearance");
  }
}
