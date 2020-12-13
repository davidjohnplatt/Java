
import mypack.Point;

public class PointTest
    {
      public static void main(String[] args) {
        Point  p1,p2;
        p1 = new Point();
        p2 = new Point();
        p1.set(20,70);
        p2.set(30,100);
        System.out.println(p1.getX());
        System.out.println(p2.getX());
        p1 = p2;
        System.out.println(p1.getX());
        System.out.println(p2.getX());
        }
    }
