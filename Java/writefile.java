import java.io.*;

class Input extends Object
{
    public static void main(String args[])
    {
        try
        {
           System.out.println("Enter the Filename with extension & press Enter key");
           int name;
           StringBuffer sb = new StringBuffer();
           while ((name = System.in.read()) != '\n')
           {
              sb.append((char)name);
           }
           int l=sb.length()-1;
           sb.setLength(l);
           File inFile = new File(sb.toString());
           FileOutputStream fos = new FileOutputStream(inFile);
           int onechar;
           System.out.println("Start entering the data and press Enter key to Stop");
           while ((onechar = System.in.read()) != '\n')
           {
                fos.write((char)onechar);
           }
           fos.close();
        }
           catch(IOException e)
           {
                 System.out.println("File"+e);
           }
   }
}