import java.util.*;

class ExVec extends Object      // Can we write a class which extends from Vector? Explain.
{
    Vector v;
    String type;
    Class cls;
    public ExVec(String s)throws ClassNotFoundException
    {
         v=new Vector(10,5);
         type=s;
         cls = Class.forName(s);
    }
    public void addElement(Object o) throws AddNotAllowedException
    {
         if(cls.isInstance(o))
               v.addElement(o);
         else
               throw new AddNotAllowedException();
    }
    public void printElements()
    {
         for(int i = 0; i<v.size(); i++)
            System.out.println(v.elementAt(i));
     }
 }

 class AddNotAllowedException extends Exception
 {
     public AddNotAllowedException()
     {}
     public String getMessage()
     {
        return this.toString();
     }
     public String toString()
     {
        return "AddNotAllowedException" + "Element not of the type already added \n";
     }
  }


