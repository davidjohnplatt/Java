class First
{
   First()
   {
       System.out.println("first class constructors");
    }
 }

 class Second extends First
 {
     Second()
     {
        System.out.println("second constructors");
     }
  }

  class Third extends Second
  {
      Third()
      {
        System.out.println("Third constructors");
      }
  }

  class Starter
  {
     public static void main(String arg[])
     {
        Third three = new Third();
        System.out.println("This from Starter");
     }
  }

