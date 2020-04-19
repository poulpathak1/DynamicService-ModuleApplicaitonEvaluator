module employmentcheck {
  requires criteria;

  provides criteria.Criteria with employmentcheck.EmploymentCheck;
}