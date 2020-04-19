package criminalcheck;

import criteria.Applicant;
import criteria.Criteria;
import criteria.EvaluationStatus;
import criteria.Result;

public class CriminalRecordCheck implements Criteria {
  public String getName() {
    return "Criminal Record Check";
  }

  public Result check (Applicant applicant) {
    return new Result(EvaluationStatus.FAIL,
      "Applicant has done time for robbery");
  }
}
