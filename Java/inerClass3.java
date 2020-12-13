 /* Example of Member inner  class

      declaring object of inner class within method of outer class

      Declaring just the object of inner class in class other than outer class

      inner class object needs reference of outer class

     */



 class Class1 {
	  int a=10;


   class InnerClass1 {
	public void getString() {
	    System.out.println("InnerClass1: getString invoked" + a );
	}
}


    public void displayStrings() {

	InnerClass1 ob = new InnerClass1();
	ob.getString();
	System.out.println("display of outer");
    }
}
public class inerClass3{

    static public void main(String[] args) {

		Class1 obj1 = new Class1();
		obj1.displayStrings();

        Class1.InnerClass1 c1 = new Class1().new InnerClass1();
        c1.getString();

        Class1.InnerClass1 c2 = obj1.new InnerClass1(); // constructing object of inner using
                                        //object of inner class
        c2.getString();

    }


}
