class Distance
{
    int feet;
    double inches;
    Distance()
    {
        feet=5;
        inches=18.6;
    }
    Distance(int f, double i)
    {
        feet=f;
        inches=i;
    }
    void display()
    {
        System.out.println("feet is" + feet);
        System.out.println("inches is" + inches);
    }
}


class Starter
{
     public static void main(String arg[])
     {
          Distance d = new Distance(10,12.5);
          d.display();
          d=null;
          Distance d1=new Distance();
          d1.display();
          d.display(); // NullPointerException is thrown
      }
      
}
      
      