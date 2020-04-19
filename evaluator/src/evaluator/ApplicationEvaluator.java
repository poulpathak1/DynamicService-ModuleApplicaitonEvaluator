package evaluator;

import criteria.Applicant;
import criteria.Criteria;
import criteria.EvaluationStatus;
import criteria.Result;
import java.util.*;
import java.util.stream.Collectors;

public class ApplicationEvaluator {

  public List<Criteria> getCriteria() {

    return ServiceLoader.load(Criteria.class)
      .stream()
      .map(ServiceLoader.Provider::get)
      .collect(Collectors.toList());
  }
  
  public List<Criteria> getSelectedCriteria(List<Criteria> criteria, List<String> selectedCriteriaNames) {

    return criteria.stream()
      .filter(
        criterion -> selectedCriteriaNames.contains(criterion.getName()))
        .collect(Collectors.toList());
  }

  public Result evaluateApplicant(List<Criteria> criteriaList, Applicant applicant){

    if (criteriaList.isEmpty()) return new Result(EvaluationStatus.FAIL,
      "No criteria was provided");

    EvaluationStatus[] finalResponse = new EvaluationStatus[] { EvaluationStatus.PASS };

    String detail = criteriaList.stream()
      .map( criteria ->{
        Result result = criteria.check(applicant);

        if (result.response == EvaluationStatus.FAIL) finalResponse[0] = EvaluationStatus.FAIL;

        return result.detail;
      })
      .collect(Collectors.joining(", "));

    return new Result(finalResponse[0], detail);
  }
}
