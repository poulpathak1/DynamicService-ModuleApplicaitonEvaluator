package employmentcheck;

import criteria.Applicant;
import criteria.Criteria;
import criteria.EvaluationStatus;
import criteria.Result;

public class EmploymentCheck implements Criteria {
  public String getName() {
    return "Employment Check";
  }

  public Result check(Applicant applicant) {
    return new Result(EvaluationStatus.PASS,
      "Applicant has previous employment");
  }
}
