public class Account {

   int custnum;
   String FirstName;
   String Lastname;
   String City;

   public void set(int one, String two, String three, String four)
     {
      custnum = one;
      FirstName = two;
      Lastname = three;
      City = four;
     }

   public void changeFirstName(String one){
      FirstName = one;
     }

   public String toString() {
      return ("ACCNO = "+custnum+" NAME = "+FirstName+" "+Lastname+" CITY = "+City);
     }

    
   
   public static void main(String arg[]) {
     Account a = new Account();
     a.set (101005,"David","Platt","Oakville");
     a.changeFirstName("Fred");
     System.out.println(a);
   }
}

