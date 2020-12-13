
abstract class Base {

int x;

public abstract void meth1();

public void pr()
{
   System.out.println("pr of Base class");
   }


}

class Derv extends Base implements Inf
{
    int y;

    public void meth1()
    {
       System.out.println("meth1 redefined in derv  class");
   }



 }

 public class Testmulderv {

 public static void main(String [] ar)
  {
        Derv obj = new Derv();
        obj.meth1();
        }
}



