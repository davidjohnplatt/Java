import java.lang.*;

public class boat {
  public String _boatOwner;
  public String _boatNumber;
  public String _boatClass;
 // public String _boatRating;
  public double _boatRating;

  public boat() {}

  public boat(String rec){
    int i = rec.indexOf( ',', 0 );
    _boatOwner = rec.substring( 0, i ).trim();
    int j = rec.indexOf(',',i + 1 );
    _boatNumber = rec.substring( i + 1, j ).trim();
    int k = rec.indexOf( ',', j + 1 );
    _boatClass = rec.substring( j + 1 , k ).trim();
 //   _boatRating = rec.substring( k + 1 ).trim();
   _boatRating = Double.parseDouble(rec.substring( k  + 1 ).trim());
  }

  boolean equals( boat b ) {
    return( _boatClass.equals( b._boatClass ) );
  }


  void print() {
    System.out.println(_boatOwner + ',' +
                       _boatNumber + ',' +
                       _boatClass + ',' +
                       _boatRating);
  }
}
