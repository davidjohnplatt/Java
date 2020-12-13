import java.io.StreamTokenizer;
import java.io.InputStream;
import java.util.Hashtable;
import java.io.IOException;

class KeyValue {
    public static void parser(InputStream in, Hashtable h) {
        StreamTokenizer p = new StreamTokenizer(in);
        
        // key values are separated by white spaces or '='
        p.whitespaceChars('=', '=');
        int c;
        String key = null;
        boolean errFlag = false;
        boolean expecting_value = false;
        try {
        out:
            while (true) {
                c = p.nextToken();
                switch (c) {
                case StreamTokenizer.TT_EOF:
                    break out;
                case StreamTokenizer.TT_EOL:
                    // should not see this because we didn't make
                    // EOL significant
                    System.err.println("warning: unexpected EOL token");
                    break;
                case StreamTokenizer.TT_NUMBER:
                    if (expecting_value) {
                        h.put(key, new Double(p.nval));
                        expecting_value = false;
                    } else {
                        // cannot have numeric keys
                        errFlag = true;
                        break out;
                    }
                    break;
                case StreamTokenizer.TT_WORD:
                    if (expecting_value) {
                        h.put(key, p.sval);
                        expecting_value = false;
                    } else {
                        expecting_value = true;
                        key = p.sval;
                    }
                    break;
                default:
                    errFlag = true;
                    break out;
                }
            }
            if (errFlag)
                System.err.println("Error encountered around '" + key +"'");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Hashtable h = new Hashtable();
        parser(System.in, h);
        System.out.println(h.toString());
    }
}
