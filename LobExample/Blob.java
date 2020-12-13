package com.wamoz.db;

import javax.mail.internet.MimeUtility;
import javax.mail.MessagingException;
import java.io.*;
import java.util.*;

/**
<p>Copyright (C) 2001 Peter Routtier-Wone, Wombat &amp; Me Pty Ltd</p>
<p>This library is free software; you can redistribute it and/or modify it under the terms of the GNU Lesser General Public License as published by the Free Software Foundation; either version 2.1 of the License, or (at your option) any later version.</p>
<p>This library is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the <a href="http://www.fsf.org/copyleft/lesser.html">GNU Lesser General Public License</a> for more details.</p>
<p>Represents a BLOB in memory for Java.</p>
<p>Accessor methods support value transfer as <code>byte[]</code> and <code>String</code>.</p>
<p>You can also set the value from an <code>InputStream</code> - in this case it is assumed that the stream contains raw data. This is supported for convenience with databases since blobs are retrieved via <code>InputStream</code> objects returned by JDBC.</p>
*/
public class Blob {

  private String Binhex;
  private byte[] Raw;
  private boolean hasValue = false;

  /**
  <p>Default constructor in case you want to create the object and then recycle it in a loop.</p>
  */
  public Blob() {};

  /**
  <p>Construct a blob object and set its value from the supplied binhex string.</p>
  */
  public Blob(String Binhex) {
    setBinhex(Binhex);
  }; //constructor

  /**
  <p>Construct a blob object and set its value from the supplied raw data.</p>
  */
  public Blob(byte[] Raw) {
    setRaw(Raw);
  }; //constructor

  /**
  <p>Construct a blob object and set its value from the raw data available from the supplied <code>InputStream</code> object.</p>
  */
  public Blob(InputStream is) {
    setRaw(is);
  }; //constructor

  /**
  <p>Sets the value of this blob object from the supplied binhex string and induces the raw data.</p>
  */
  public void setBinhex(String Binhex) {
    this.Binhex = Binhex;
    //decode Binhex to Raw
    int blockSize;
    try {
      InputStream is = MimeUtility.decode(new StringBufferInputStream(Binhex),"base64");
      DataInputStream dis = new DataInputStream(is);
      blockSize = dis.readInt();
      Raw = new byte[blockSize];
      is.read(Raw);
    } catch (Exception e) {
      e.printStackTrace();
    }; //catch
  }; //setBinhex(String Binhex)

  /**
  <p>Sets the value of this blob object from the supplied raw data, and induces the binhex value.</p>
  */
  public void setRaw(byte[] Raw) {
    this.Raw = Raw;
    int blockSize = Raw.length;
    ByteArrayOutputStream baosDWord = new ByteArrayOutputStream(8);
    DataOutputStream dos = new DataOutputStream(baosDWord);
    ByteArrayOutputStream baos = new ByteArrayOutputStream(Raw.length);
    //encode Raw to Binhex
    try {
      //put the blockSize into the stream
      dos.writeInt(blockSize);
      byte[] dwa = baosDWord.toByteArray();
      OutputStream os = MimeUtility.encode(baos,"base64");
      os.write(dwa);
      //put the data into the stream
      os.write(Raw);
      //prevent truncation of trailing whitespace
      os.write(dwa);
      //get the stream data as a string
      StringBuffer buf = new StringBuffer();
      Binhex = baos.toString("ASCII");
    } catch (Exception e) {
      e.printStackTrace();
    }; //catch
  }; //setRaw(byte[] Raw)

  /**
  <p>Sets the value of this blob object from the raw data available from the supplied <code>InputStream</code> object, and induces the binhex value.</p>
  */
  public void setRaw(InputStream is) {
    //read it into a byte[] and call setRaw(byte[])
    Vector buffers = new Vector();
    byte[]
      b2k,
      lastBuf;
    int
      bufCount = -1,
      bytesRead;
    try {
      do {
        b2k = new byte[2048];
        bytesRead = is.read(b2k);
        buffers.addElement(b2k);
        bufCount++;
      } while (2048 == bytesRead); //on exit, bytesRead is the part of the buffer that is valid.
      lastBuf = b2k;
      int totalBufferSize = (bufCount * 2048) + bytesRead;
      if (totalBufferSize > 0) {
        Raw = new byte[totalBufferSize];
        for (int i = 0; i < bufCount; i++) {
          b2k = (byte[])buffers.elementAt(i);
          System.arraycopy(b2k,0,Raw,i * 2048,2048);
        }; //for (int i = 0; i < bufCount; i++)
        System.arraycopy(lastBuf,0,Raw,bufCount * 2048,bytesRead);
      } else {
        Raw = new byte[0];
      }; //else
    } catch (IOException ioe) {
      ioe.printStackTrace();
    }; //catch
    setRaw(Raw); //cause side-effects
  }; //setRaw(InputStream is)

  /**
  <p>Returns the object's value as a binhex string.</p>
  */
  public String getBinhex() {
    return Binhex;
  }; //getBinhex()

  /**
  <p>Returns the object's value as raw data (<code>byte[]</code>)</p>
  */
  public byte[] getRaw() {
    return Raw;
  }; //getRaw()

  /**
  <p>Returns an <code>InputStream</code> referring to the raw data for the purpose of writing the content of the blob into a <code>PreparedStatement</code> or <code>CallableStatement</code> parameter.</p>
  */
  public InputStream getInputStream() {
    return new ByteArrayInputStream(Raw);
  }; //getInputStream()

  /**
  <p>Debug version of getBinhex().</p>
  <p><code>System.out.println(MyBlob)</code> will output the object identifier returned by the default toString() and a line break followed by the binhex String representation of the object's value.</p>
  */
  public String toString() {
    return super.toString() + "\n" + getBinhex();
  }; //toString()

}; //class