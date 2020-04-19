package criteria;

public interface Criteria {
  Result check(Applicant applicant);
  String getName();
}
