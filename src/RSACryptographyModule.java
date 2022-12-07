
/**
* The Program will provide RSA Encryption Scheme
* 
*
* @author  SUBHABRATA RANA
* @version 1.0
* @since   2022-10-14
* @assignment : 01 
*/
import java.util.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;
import java.math.*;
import java.security.*;


public class RSACryptographyModule {

	public static void main(String[] args) 
	{
		
		try
		{
			int userChoice=0;
			Scanner scanner = new Scanner(System.in);
			BigInteger ciphertext=BigInteger.ZERO;
			BigInteger message=BigInteger.ZERO;
			
			HashMap<Character, BigInteger> private_public_Params=generatePrivatePublicKeyPair();
			BigInteger decryptionExponent=private_public_Params.get('d');
			BigInteger encryptionExponent=private_public_Params.get('e');
			BigInteger primeNumber1=private_public_Params.get('p');
			BigInteger primeNumber2=private_public_Params.get('q');
			BigInteger compositeModulus=private_public_Params.get('n');
			
			System.out.println("\n------------------------------------------------");
			System.out.println("A1: RSA ENCRYPTION");
			System.out.println("------------------------------------------------\n");
			System.out.println("The first prime is p     : "+ primeNumber1);
			System.out.println("The second prime is q    : "+ primeNumber2);
			System.out.println("The composite modulus n  : "+ compositeModulus);
			System.out.println("The encryption exponent e: "+ encryptionExponent);
			System.out.println("The decryption exponent d: "+ decryptionExponent);
			System.out.println("------------------------------------------------");
			
		
			// Keep asking users for valid input
			do {
				
				System.out.println("\nPlease enter an option:" );
				System.out.println("1 to Encrypt:");
				System.out.println("2 to Decrypt:");
				System.out.println("0 to Exit:\t");
				userChoice=scanner.nextInt();
				System.out.println("Your option:" +userChoice);
				System.out.println("------------------------------------------------\n");
				
				
				if(userChoice==1)
				{
					//Generate Random Message 
					message=generateRandomMessage(primeNumber1);
					//message=new BigInteger("6");
					ciphertext=EncryptionModule(message,encryptionExponent, compositeModulus);	
				}
				else if(userChoice==2)
				{
					// If you select decryption before encryption, there will not be any ciphertext to decrypt
					// User will be keep asking for encryption first
					if(ciphertext.compareTo(BigInteger.ZERO)==0)
					{
						System.out.println("Please perform encryption before performing decryption");
						System.out.println("------------------------------------------------\n");
					}
					else
					{
						DecryptionModule(ciphertext,decryptionExponent,compositeModulus);
					}
				}
				else if(userChoice!=0)
				{
					System.out.println("Please enter a valid input");
				}
					
				
			 }while(userChoice!=0);
			
			System.out.println("Exits from the menu");
		}
		catch(Exception ex)
		{
			  System.out.println("Encryption occures in the main module:"+ex);
		}
	

	}// main


	// This function will generate Random Message
	private static BigInteger generateRandomMessage(BigInteger number)
	{	
		 BigInteger message=BigInteger.ZERO;
		  try
		  {
			  SecureRandom secureNumber = new SecureRandom();	
			   message = new BigInteger(number.bitLength(), secureNumber);	
		  }
	   catch(Exception ex)
		{
			  System.out.println("Encryption occures while generating random number:"+ex);
		}
		  
		  return message;
	}
	  
	  // This function will return the private and public key set
	  private static HashMap<Character,BigInteger> generatePrivatePublicKeyPair()
	  {
		
		  HashMap<Character,BigInteger> privatePublicKeyStore=new HashMap<Character,BigInteger>();
		  
		  
		  
		  //BigInteger primeNumber1=new BigInteger("11");
	      //BigInteger primeNumber2=new BigInteger("13");
		  
		 
		  try
		  {
			// Prime Numbers are private
			  BigInteger primeNumber1=new BigInteger("19211916981990472618936322908621863986876987146317321175477459636156953561475008733870517275438245830106443145241548501528064000686696553079813968930084003413592173929258239545538559059522893001415540383237712787805857248668921475503029012210091798624401493551321836739170290569343885146402734119714622761918874473987849224658821203492683692059569546468953937059529709368583742816455260753650612502430591087268113652659115398868234585603351162620007030560547611");
			  BigInteger primeNumber2=new BigInteger("49400957163547757452528775346560420645353827504469813702447095057241998403355821905395551250978714023163401985077729384422721713135644084394023796644398582673187943364713315617271802772949577464712104737208148338528834981720321532125957782517699692081175107563795482281654333294693930543491780359799856300841301804870312412567636723373557700882499622073341225199446003974972311496703259471182056856143760293363135470539860065760306974196552067736902898897585691");
			  
			  
			  // Composite Modulus
			  BigInteger compositeModulus=primeNumber1.multiply(primeNumber2);
			  
			  // Generate Fi(n)=(p-1) * (q-1)
			  BigInteger fi_n=(primeNumber1.subtract(BigInteger.ONE)).multiply(primeNumber2.subtract(BigInteger.ONE));
			  
			  //Encryption Exponent
			  BigInteger encryptionExponent=getEncryptionExponant(fi_n);
					  
			  // Get the public Key
			  BigInteger decryptionExponent = encryptionExponent.modInverse(fi_n);		
			  
			  //Store the values in to the hash map
			  privatePublicKeyStore.put('d', decryptionExponent);
			  privatePublicKeyStore.put('e', encryptionExponent);
			  privatePublicKeyStore.put('p', primeNumber1);
			  privatePublicKeyStore.put('q', primeNumber2);
			  privatePublicKeyStore.put('n', compositeModulus); 
			
		  }
		  catch(Exception ex)
		  {
			  System.out.println("Exception occurred while generating private key : " +ex);
		  }
		  
		  return privatePublicKeyStore;
	  }
	
	  
	  // This function returns Encryption Exponent
	  // ComapareTo Function in BigInteger Returns -1 (if < targetValue) or 0(If ==targetValue) or 1 (if > targetValue)	
     private static BigInteger getEncryptionExponant(BigInteger fi_N)
     {
    	 BigInteger exponent=BigInteger.ZERO;  
    	 try
    	 {
    		    	      
            BigInteger gcdValue=BigInteger.ZERO;
    		// gcdValue= exponent.gcd(fi_N);
    		 for (exponent = BigInteger.valueOf(2); exponent.compareTo(fi_N)==-1; exponent=exponent.add(BigInteger.ONE)) 
			   {    			    
    			  gcdValue=exponent.gcd(fi_N);    	
				  if (gcdValue.compareTo(BigInteger.valueOf(1))==0)
				  {
		                break;
		          }
		     }			   
    	 }
    	 catch(Exception ex)
    	 {
    		 System.out.println("Exception occurred while getiting exponent : "+ex);
    	 }
    
    	 return exponent;
     }
     
 	
	 // This function will encrypt data
	  private static BigInteger EncryptionModule(BigInteger message,BigInteger encryptionExponent, BigInteger compositeModulus)
	  {		  
		  BigInteger ciphertext=BigInteger.ZERO;
		  try
		  {
			 			  
			  ciphertext=message.modPow(encryptionExponent,compositeModulus);	  
			  
			  System.out.println("ENCRYPTION USING RSA :\n");
			  System.out.println("Plaintext (randomly generated) to be encrypted is m: "+message);
			  System.out.println("Ciphertext is c                                    : "+ciphertext);
			 
		  }
		  catch(Exception ex)
		  {
			  System.out.println("Exception occurred while encrypting data : "+ex);
		  }
		  
		  return ciphertext;
		  		 
	  }
	  
	  
	  // This function will decrypt data
	  private static void DecryptionModule(BigInteger ciphertext, BigInteger decryptionExponent, BigInteger compositeModulus)
	  {		 
		  BigInteger plaintext=BigInteger.ZERO;
		
		  try
		  { 			
			  plaintext=ciphertext.modPow(decryptionExponent,compositeModulus);
			  		
			  System.out.println("DECRYPTION USING RSA:\n");
			  System.out.println("Ciphertext to be decrypted is c: "+ ciphertext);
			  System.out.println("Decrypted plaintext is m       : "+ plaintext);
		  }
		  catch(Exception ex)
		  {
			  System.out.println("Exception occurred while decrypting data : "+ex);
		  }
		  		 
	  }
  
	  
}//class


