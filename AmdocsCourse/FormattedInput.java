import java.io.*;

public class FormattedInput {

  private StreamTokenizer tokenizer = new(StreamTokenizer (new InputStreamReader(System.in)));

}

public int intRead() {

  try{
    
     for (int i = 0; i < 5; i++) {
        if tokenizer.nextToken()==tokenizer.TT_NUMBER)
            return (int)tokenizer.nval;
        else {
           System.out.println("Incorrect input: " + tokenizer.sval + " Re-enter and Integer");
           continue;
        }
        System.out.println("Five failures in reading an int value - program terminated");
        System.exit(1);
        return 0;
   }
   catch (IOException e) {
      System.out.println(e);
      System.exit(1);
      return 0;
   }
}
     