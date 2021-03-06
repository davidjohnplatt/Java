import java.io.*;

class fileRead {

  public static void main (String[] args) {
    String fname=(args.length <= 0) ? "fileRead.java": args[0];

    try {
      BufferedReader in = new BufferedReader(new FileReader(fname));
      BufferedWriter out = new BufferedWriter(new OutputStreamWriter(System.out));
      String str;
      
      while ((str = in.readLine()) != null) {
        out.write(str);
        out.newLine();
      }

      in.close();
      out.close();
    } 
    catch (IOException e){
        e.printStackTrace();
    }
  }
}