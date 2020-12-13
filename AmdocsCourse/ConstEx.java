class Point{

  int x,y;
  Point()  {x=0;
  y=0;
  }

  Point (int x, int y)
  {this.x=x;
  this.y = y;
  }

    void pr()
   { System.out.println("X coordinate"+x+"  Y coordinate :"+y);
}




  }

  class ConstEx {
  public static void main(String [] st)
  {  Point p=new Point();
     Point q = new Point(3,4);
     p.pr() ;   q.pr();
    }
    }
