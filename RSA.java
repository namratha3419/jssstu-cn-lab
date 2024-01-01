import java.util.*;
import java.io.DataInputStream;
import java.io.IOException;
import java.math.*;
import java.util.Random;

class RSA
{
  private BigInteger p, q, N, phi, e, d;
  private int bitlength = 1024;
  private Random r;
  public RSA ()
  {
    r = new Random ();
    p = BigInteger.probablePrime (bitlength, r);
    q = BigInteger.probablePrime (bitlength, r);
    System.out.println ("value of p:" + p);
    System.out.println ("value of q:" + q);
    N = p.multiply (q);
    phi = p.subtract (BigInteger.ONE).multiply (q.subtract (BigInteger.ONE));
    e = BigInteger.probablePrime (bitlength / 2, r);
    while (phi.gcd (e).compareTo (BigInteger.ONE) > 0
	   && e.compareTo (phi) < 0)
      {
	e = e.add (BigInteger.ONE);
      }
    System.out.println ("public key:" + e);
    d = e.modInverse (phi);
    System.out.println ("private kry:" + d);
  }
  public RSA (BigInteger e, BigInteger d, BigInteger N)
  {
    this.e = e;
    this.d = d;
    this.N = N;
  }
  public static void main (String args[]) throws IOException
  {
      RSA rsa = new RSA();
      DataInputStream in = new DataInputStream(System.in);
      System.out.println("enter plaintext:");
      String teststring = in.readLine();
      System.out.println("the encrypting message:"+teststring);
      System.out.println("string to byte"+bytesToString(teststring.getBytes()));
      byte [] encrypted = rsa.encrypt(teststring.getBytes());
      byte [] decrypted = rsa.decrypt(encrypted);
      System.out.println("decrypted message in bytes:"+bytesToString(decrypted));
      System.out.println("decrypted message:"+new String(decrypted));
  }
  public static String bytesToString (byte[]encrypted)
  {
    String test = " ";
  for (byte b:encrypted)
      {
	test += Byte.toString(b);
      }
    return test;
  }
  public byte[] encrypt(byte[]message)
  {
    return (new BigInteger (message)).modPow (e, N).toByteArray ();
  }
  public byte[] decrypt(byte [] message){
        return (new BigInteger(message)).modPow(d,N).toByteArray();
    }

}
