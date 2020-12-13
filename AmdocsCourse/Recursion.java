class Recursion
{
     Recursion()
     {     }
     
     double calcfact(int n)
     {
         double result;
         if (n==0)
         result=1;
         else
         result=n*calcfact(n-1);
         return result;
     }
     
     
     public static void main(String arg[])
     {
         Recursion r= new Recursion();
         double d=r.calcfact(5);
         System.out.println(d);
     }
}