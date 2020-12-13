import java.io.*;
class Appendex
{
    public static void main(String args[])
    {  
         int ch;
         StringBuffer sb = new StringBuffer();
         try
         {
            FileInputStream is = new FileInputStream(args[0]);
            FileInputStream is1 = new FileInputStream(args[1]);
            SequenceInputStream si = new SequenceInputStream(is,is1);
            while((ch=si.read()) != -1)
            {
                 if (ch != '\n')
                 sb.append((char)ch);
                 else
                 System.out.println(sb.toString());
             }
          }
          catch(IOException io)
          {  }
          catch(Exception e)
          {  }
      }
 }
          