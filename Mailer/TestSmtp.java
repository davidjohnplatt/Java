// test client
// import JSmtpClient

class TestSmtp {

    public static void main(String args[]) {

        try {
            JSmtpClient smtp = new JSmtpClient("192.168.1.5");
            smtp.setFrom("doyourhomework@right.now");
            smtp.setTo("jooptacook@hotmail.com");
            smtp.setSubject("This is your Father Speaking");
            smtp.setBody("Petty neat hunh?\n\nDad");
            smtp.send();
        }
        catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

