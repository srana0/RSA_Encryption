# RSA_Encryption
Please implement the key generation, encryption, and decryption algorithms of the RSA public key scheme either in C using GMP, Java using BigInteger, or Python using gmpy library for a 128-bit
security level. The large primes p and q are provided in the parameters section (on the last page), where the sizes of p and q are about 1538 bits. For convenience, these three algorithms are described
below. You are prohibited from using any RSA code available on the Internet or other sources and RSA APIs available in your programming language libraries.

KEY GENERATION
1.	Compute φ(n) = (p − 1)(q − 1)
2.	Randomly generate e s.t.    e · d mod φ(n) = 1
3.	Public-key p<sub>k</sub> = (e, n)
4.	Private-key s<sub>k</sub> = (d, p, q)

ENCRYPTION c ← E(p<sub>k</sub>, m)
1.	Randomly message m ϵ Z<sub>n</sub>
2.	Compute c = m<sup>e</sup> mod n
3.	Return c

DECRYPTION m' ← D(s<sub>k</sub>, c) 
1.	Compute m' = cd mod n
2.	Return m'

RSA Parameters
</br>
p =1921191698199047261893632290862186398687698714631732117547745963615695356147500873387
0517275438245830106443145241548501528064000686696553079813968930084003413592173929258
2395455385590595228930014155403832377127878058572486689214755030290122100917986244014
9355132183673917029056934388514640273411971462276191887447398784922465882120349268369
2059569546468953937059529709368583742816455260753650612502430591087268113652659115398
868234585603351162620007030560547611
</br> </br>
q = 4940095716354775745252877534656042064535382750446981370244709505724199840335582190539
5551250978714023163401985077729384422721713135644084394023796644398582673187943364713
3156172718027729495774647121047372081483385288349817203215321259577825176996920811751
0756379548228165433329469393054349178035979985630084130180487031241256763672337355770
0882499622073341225199446003974972311496703259471182056856143760293363135470539860065
760306974196552067736902898897585691

# Sample Ouput
Sample I/O:
The first prime is p =
The second prime is q =
The composite modulus n =
The encryption exponent e =
The decryption exponent d =
----------------------------
Please enter an option:
1 to Encrypt
2 to Decrypt
Your option:
----------------------------
Encryption:
Plaintext (randomly generate) to be encrypted is m =
Ciphertext is c =
----------------------------
Decryption:
Ciphertext to be decrypted is c =
Decrypted plaintext is m =
