package creditcheck;

import criteria.Applicant;
import criteria.Criteria;
import criteria.EvaluationStatus;
import criteria.Result;

public class CreditReportCheck implements Criteria {
  public String getName() {
    return "Credit Report Check";
  }

  public Result check(Applicant applicant) {
    return new Result(EvaluationStatus.PASS,
     "Applicant has good credit");
  }
}
