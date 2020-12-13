class Point
{
   int x;
   int y;
   Point()
   {
      x=0;
      y=0;
   }
   Point(int x1,int y1)
   {
      Point();  // cannot call this way : use super();
      x=x1;
      y=y1;
   }
   public static void main(String arg[])
   {
      Point p1=new Point(7,4);
      System.out.println(p1);
   }
}