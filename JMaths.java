/*
 * Copyright (C) 2016 Pi Developers
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @author Sahid Almas
 */



import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Scanner;

public class JMaths {
    
    private static final String ALGO = "AES";
    private static final byte[] keyValue =
            new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't',
                    'S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };

    private static ScannerHelper sScannerHelper = new ScannerHelper(" : ");

    public static void main(String[] args) {
        String JMATHS = "       ____  ___      __  __        \n      / /  |/  /___ _/ /_/ /_  _____\n __  / / /|_/ / __ `/ __/ __ \\\\/ ___/\n/ /_/ / /  / / /_/ / /_/ / / (__  ) \n\\\\____/_/  /_/\\\\__,_/\\\\__/_/ /_/____/ ";
        System.out.println(JMATHS);
        System.out.println("              By Pi-Dev");
        System.out.println("=======================================");

        System.out.println("Would you like to do ?");
        int cursor = sScannerHelper.askAsInt(" 1) Generate Fibonacci Series :\n" +
                " 2) Check Even or odd : \n" +
                " 3) Check prime no : \n" +
                " 4) Encrypt text : \n" +
                " 5) Decrypt text :");
        if (cursor == 1) {
            fibonacci();
        }else if (cursor == 2) {
            checkEvenOdd();
        }else if (cursor == 3) {
            checkPrimeNo();
        }else if (cursor == 4) {
            encrypt();
        }else if (cursor == 5) {
            decrypt();
        }
        else {
            System.out.println("Wrong Input");
        }


    }

    private static void decrypt() {
        String text = sScannerHelper.askAsString("Enter your text to decrypt ");
        try {
            System.out.println(decrypt(text));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void encrypt() {
        String text = sScannerHelper.askAsString("Enter your text to encrypt ");
        try {
            System.out.println(encrypt(text));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static String encrypt(String Data) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.ENCRYPT_MODE, key);
        byte[] encVal = c.doFinal(Data.getBytes());
        return new BASE64Encoder().encode(encVal);
    }

    public static String decrypt(String encryptedData) throws Exception {
        Key key = generateKey();
        Cipher c = Cipher.getInstance(ALGO);
        c.init(Cipher.DECRYPT_MODE, key);
        byte[] decordedValue = new BASE64Decoder().decodeBuffer(encryptedData);
        byte[] decValue = c.doFinal(decordedValue);
        return new String(decValue);
    }
    private static Key generateKey() throws Exception {
        return new SecretKeySpec(keyValue, ALGO);
    }

    private static void checkPrimeNo() {
        int no = sScannerHelper.askAsInt("Enter your no");
        int i, m, flag = 0;
        m = no / 2;
        for (i = 2; i <= m; i++) {
            if (no % i == 0) {
                System.out.println("Number is not prime");
                flag = 1;
                break;
            }
        }
        if (flag == 0)
            System.out.println("Number is prime");

    }


    private static void checkEvenOdd() {
        int no = sScannerHelper.askAsInt("Enter your no");

        boolean even = no%2==0;
        if (even) {
            System.out.println(" No. '"+no+"' is even");
        }
        else {
            System.out.println(" No. '"+no+"' is odd");
        }
    }

    private static void fibonacci() {
        int no = sScannerHelper.askAsInt(" How many terms to generate");
        StringBuilder stringBuilder = new StringBuilder();
        String result = "";
        for (int i = 0 ; i < no ; i++) {

            stringBuilder.append(String.valueOf(fibonacci(i))).append(",");

        }
        if (stringBuilder.toString().endsWith(",")) {
            result = stringBuilder.toString().substring(0,stringBuilder.length()-1);
        }
        System.out.println("The no.s are " +result);
    }

    private static int fibonacci(int no) {
        if (no == 0 || no == 1) {
            return no;
        }else {
            return fibonacci(no - 1) + fibonacci(no - 2);
        }
    }
    private static class ScannerHelper {
        private Scanner mScanner;
        private String mSeparator;
        public ScannerHelper(String Separator) {
            this.mSeparator = Separator;
        }
        private void rebuildScanner() {
            mScanner = new Scanner(System.in);
        }
        public long askAsLong(String questions) {
            long result = 0;
            try {
                String q = askAsString(questions);
                result = Integer.parseInt(q);
            }catch (Exception e) {
                System.out.println("Your input is not Long . Try to enter again");
                askAsInt(questions);

            }
            return result;
        }

        public int askAsInt(String questions) {
            int result = 0;
            try {
                String q = askAsString(questions);
                result = Integer.parseInt(q);
            }catch (Exception e) {
                System.out.println("Your input is not Integer. Try to enter again");
                askAsInt(questions);

            }
            return result;
        }
        public String askAsString(String questions) {
            System.out.println(questions + mSeparator + " ");
            rebuildScanner();
            String object = mScanner.next();
            mScanner = null;
            return object;
        }
    }
}

