import java.lang.System;
import java.net.Socket;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;

class JSmtpClient {

    private Socket connection;
    private DataOutputStream outStream;
    private DataInputStream inStream;

    private String host_;
    private String from_;
    private String to_;
    private String subject_;
    private String body_;

    public JSmtpClient(String host) throws Exception {

        host_ = new String(host);
        int port = 25;
        String action = new String("OPEN");
        System.err.println("Action: == " + action);

        try {
            connection = new Socket(host, port);
            inStream = new DataInputStream(connection.getInputStream());
            outStream = new DataOutputStream(connection.getOutputStream());
            if ( !checkResponse("220") ) {
                throw (new Exception("Could not open connection on " + host + ":" + port));
            }
        }
        catch (UnknownHostException e) {
            throw (new Exception(e.getMessage()));
        }
        catch (IOException e) {
            throw (new Exception(e.getMessage()));
        }
        catch (Exception e) {
            throw (new Exception(e.getMessage()));
        }
        System.err.println("Connected to " + host + " at port " + port);
        System.err.println("Streams opened correctly");
    }

    public void setFrom(String from) {

        from_ = new String(from);
    }

    public void setTo(String to) {

        to_ = new String(to);
    }

    public void setSubject(String subject) {

        subject_ = new String(subject);
    }

    public void setBody(String body) {

        body_ = new String(body);
    }

    public void send() throws Exception {

        Date date = new Date();

        if ( from_ == null ) {
            throw (new Exception("SEND: failed because from_ is null"));
        }
        if ( to_ == null ) {
            throw (new Exception("SEND: failed because to_ is null"));
        }
        if ( subject_ == null ) {
            throw (new Exception("SEND: failed because subject_ is null"));
        }
        if ( body_ == null ) {
            throw (new Exception("SEND: failed because body_ is null"));
        }
        try {
            // Start transmission
            outStream.writeBytes("HELO smtp.cogeco.ca\n");
            outStream.flush();
            if ( !checkResponse("250") ) {
                shutDown();
                throw (new Exception("HELO: failed, closing connection."));
            }

            outStream.writeBytes("MAIL FROM: <" + from_ + ">\n");
            outStream.flush();
            if ( !checkResponse("250") ) {
                shutDown();
                throw (new Exception("MAIL FROM: failed, closing connection."));
            }

            outStream.writeBytes("RCPT TO: <" + to_ + ">\n");
            outStream.flush();
            if ( !checkResponse("250") ) {
                shutDown();
                throw (new Exception("RCPT TO: failed, closing connection."));
            }

            outStream.writeBytes("DATA\n");
            outStream.flush();
            if ( !checkResponse("354") ) {
                shutDown();
                throw (new Exception("DATA: failed, closing connection."));
            }
            outStream.writeBytes("To:      " + to_ + "\n");
            outStream.writeBytes("From:    " + from_ + "\n");
            outStream.writeBytes("Date:    " + date.toString() + "\n");
            outStream.writeBytes("Subject: " + subject_ + "\n");
            outStream.writeBytes("\n");
            outStream.writeBytes(body_ + "\n");
            outStream.writeBytes(".\n");
            outStream.flush();
            if ( !checkResponse("250") ) {
                shutDown();
                throw (new Exception("DATA: failed, closing connection."));
            }

            quit();
        }
        catch (Exception e) {
            throw (new Exception(e.getMessage()));
        }
    }

    private void quit() throws Exception {

        String action = new String("QUIT");
        System.err.println("Action: == " + action);

        try {
            outStream.writeBytes("QUIT\n");
                  }
        catch (Exception e) {
            throw (new Exception(e.getMessage()));
        }
    }

    private void shutDown() throws Exception {

        try {
            connection.close();
        }
        catch (IOException e) {
            throw (new Exception(e.getMessage()));
        }
    }

    private boolean checkResponse(String returnCode) throws Exception {

        String response = null;

        try {
            response = inStream.readLine();
            System.err.println(response);

            if ( response.startsWith(returnCode) ) {
                return true;
            }
        }
        catch (IOException e) {
            throw (new Exception(e.getMessage()));
        }
        return false;
    }
}
