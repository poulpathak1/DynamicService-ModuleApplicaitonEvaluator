module securityclearance {
  requires criteria;

  provides criteria.Criteria with securityclearance.SecurityClearanceCheck;
}