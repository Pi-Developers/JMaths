/*
 * Copyright (C) 2015 Pi Developers
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
import java.util.Scanner;

public class JMaths {

    private static ScannerHelper sScannerHelper = new ScannerHelper(" : ");

    public static void main(String[] args) {
        String JMATHS = "       ____  ___      __  __        \n      / /  |/  /___ _/ /_/ /_  _____\n __  / / /|_/ / __ `/ __/ __ \\\\/ ___/\n/ /_/ / /  / / /_/ / /_/ / / (__  ) \n\\\\____/_/  /_/\\\\__,_/\\\\__/_/ /_/____/ ";
        System.out.println(JMATHS);
        System.out.println("              By Pi-Dev");
        System.out.println("=======================================");

        System.out.println("Would you like to do ?");
        int cursor = sScannerHelper.askAsInt(" 1) Generate Fibonacci Series ");
        if (cursor == 1) {
            fibonacci();
        }else { 
            System.out.println("Wrong Input");
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
