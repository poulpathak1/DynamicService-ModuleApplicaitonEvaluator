package criteria;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ApplicantTest {
  @Test
  void createApplicant(){
    assertNotNull(new Applicant());
  }
}
