module creditcheck {
  requires criteria;

  provides criteria.Criteria with creditcheck.CreditReportCheck;
}