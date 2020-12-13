class A{
	     int i= 1;
	     int f() {return i;}
	 }

class B  extends A{
	      int i = 2;
	      int f(){ return -i;}

	 }


class polyab{



  public static void main(String [] st)
  {  B b = new B();

	  System.out.println(b.i);
	  System.out.println(b.f());

	 A a = new B();

	  System.out.println(a.i);
	  System.out.println(a.f());

    }
    }
