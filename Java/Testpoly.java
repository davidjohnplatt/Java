class A {
     int a;

     void f1()
     {
        System.out.println("f1 of class A");
        }

        }

        class B extends A {
	     int a;

	     void f1()
	     {
                 a=a+1;

	        System.out.println("f1 of class B");

	        if(a <=2)
	            f1();

	        }

	        }

        class Testpoly {


	     public static void main(String s[])
	     {

                A obja = new B();
                obja.f1();
                }
}