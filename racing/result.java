import java.lang.*;

public class result {
  public int _raceNumber;
  public String _sailor;
  public String _boatNumber;
  public String _boatClass;
  public int _elapsedTime;
  public int _position;
  public int _positionOf;
  public double _score;

  public result( int raceNumber,
                 String sailor,
                 String boatNumber,
                 String boatClass,
                 int elapsedTime,
                 int position,
                 int positionOf,
                 double score){
    _raceNumber = raceNumber;
    _sailor = sailor;
    _boatNumber = boatNumber;
    _boatClass = _boatClass;
    _elapsedTime = elapsedTime;
    _position = position;
    _positionOf = positionOf;
    _score = score;
  }

}