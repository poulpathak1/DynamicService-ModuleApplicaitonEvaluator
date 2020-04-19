module criminalcheck {
  requires criteria;

  provides criteria.Criteria with criminalcheck.CriminalRecordCheck;
}