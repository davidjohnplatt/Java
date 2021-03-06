import java.io.*;
import java.security.*;

class testSig1 {

    public static void main(String[] args) {

        /* Test generating and verifying a DSA signature */

        try {

            /* generate a key pair */
            System.out.println("step 1");
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("DSA");
            keyGen.initialize(1024, new SecureRandom());
            KeyPair pair = keyGen.generateKeyPair();

            /* create a Signature object to use
             * for signing and verifying */

            Signature dsa = Signature.getInstance("SHA/DSA");
              System.out.println("step 2");
            /* initialize the Signature object for signing */

            PrivateKey priv = pair.getPrivate();

            dsa.initSign(priv);

            /* Update and sign the data */

            FileInputStream fis = new FileInputStream(args[0]);
            byte b;
            while (fis.available() != 0) {
                b = (byte) fis.read();
                System.out.print((char)b);
                dsa.update(b);
                };

            System.out.println("step 3 data   signed");
            fis.close();


            /* Now that all the data to be signed
             * has been read in, sign it */
            byte[] sig = dsa.sign();
            for(int i = 0; i<sig.length;i++)
            { System.out.print((char)sig[i]);
		}

            /* Verify the signature */

            /* Initialize the Signature object for verification */
            PublicKey pub = pair.getPublic();
            dsa.initVerify(pub);

            /* Update and verify the data */

            fis = new FileInputStream(args[0]);
            while (fis.available() != 0) {
                b = (byte) fis.read();
                dsa.update(b);
                };

            fis.close();

            boolean verifies = dsa.verify(sig);

            System.out.println("signature verifies: " + verifies);

        } catch (Exception e) {
            System.err.println("Caught exception " + e.toString());
        }

    }

}


