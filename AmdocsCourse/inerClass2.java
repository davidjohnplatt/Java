 /* Example of Nested Top level class

     Static Inner Class , declaring object of inner class


     */



 class Class1 {
	 static int a=5;


  static class InnerClass1 {
	public void getString() {
	    System.out.println("InnerClass1: getString invoked" + a );
	}
}


    public void displayStrings() {

	System.out.println("display of outer");
    }
}
public class inerClass2{

    static public void main(String[] args) {
        Class1.InnerClass1 c1 = new Class1.InnerClass1();
	c1.getString();
    }


}
