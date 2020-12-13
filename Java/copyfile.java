import java.io.*;

class Copy extends Object
{
     public static void main(String args[])
     {
        try
        {
           System.out.println("Enter the Filename which has got the data");
           int name;
           StringBuffer sb = new StringBuffer();
           while ((name = System.in.read())!= '\n')
           {
               sb.append((char)name);
           }
           int z1 = sb.length()-1;
           sb.setLength(z1);
           File inFile = new File(sb.toString());

           System.out.println("Enter the file name to be copied");
           int name1;
           StringBuffer sb1 = new StringBuffer();
           while ((name1=System.in.read())!='\n')
           {
                 sb1.append((char)name1);
           }
           int z2 = sb1.length()-1;
           sb1.setLength(z2);
           File outFile = new File(sb1.toString());

           FileInputStream fis = new FileInputStream(inFile);
           FileOutputStream fos = new FileOutputStream(outFile);

           int onechar;
           while((onechar = fis.read()) != -1)
           {
                 fos.write(onechar);
           }
           fis.close();
           fos.close();
           System.out.println("File is copied");
      }
            catch(FileNotFoundException e)
              { System.out.println("File"+e);  }
            catch(IOException e)
              { System.out.println("File"+e);  }
     }
 }