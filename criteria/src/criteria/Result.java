package criteria;

public class Result {
  public final EvaluationStatus response;
  public final String detail;

  public Result(EvaluationStatus responseReceived, String stringResponseReceived ){
    response=responseReceived;
    detail=stringResponseReceived;
  }
}
