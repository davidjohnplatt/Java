import java.util.Hashtable;
import java.util.Enumeration;

class Disc
{
   public String title;
   public String singer;

   Disc(String t, String s)
   {
       title = t;
       singer = s;
   }
   public String toString()
   {
       return(""+title+"by"+singer);
   }
}

class Store
{
    // print the contents of the disk holder

    public static void print(String msg, Hashtable box, boolean all)
    {
         if (msg != null)
            System.out.println(msg+":");
         if (box.isEmpty())
            System.out.println("The disk holder is empty");
         else
            {
               System.out.println("There are "+ box.size()+ "discs in the juke box :");
               for (Enumeration e = (all?box.elements() : box.keys());
               e.hasMoreElements();
               System.out.println("\t"+e.nextElement()));
            }
     }

     public static void main(String[] args)
     {

     // Create a disk holder with initial capacity of 13 and 0.5 load factor

     Hashtable diskHolder = new Hashtable(13,0.5f);
     Disc houndDog;
     houndDog = new Disc("Hound Dog","Elvis");
     diskHolder.put("Hound Dog", houndDog );
     diskHolder.put("Yesterday", new Disc("yesterday","Beatles"));
     diskHolder.put("On top of the world", new Disc("on top of the world","Carpenters"));
     diskHolder.put("Only you", new Disc("Only you","platters"));
     print("diskHolder after adding 4 titles", diskHolder, true);

     // Search by title

     System.out.println("yesterday is "+ (diskHolder.containsKey("yesterday")?"":"not")+"in the diskholder");


     // Search by content

     System.out.println(houndDog + "is" + (diskHolder.contains(houndDog)?"":"not") +"in the diskholder");

     // empty diskholder

     diskHolder.clear();
     print("diskHolder after clearing it",diskHolder, true);

  }

}







