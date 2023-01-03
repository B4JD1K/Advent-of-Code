import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

public class Main {     //public class MD5 {
    public static String getMd5(String input) {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        System.out.println("part one");
        boolean doingOne = true;

        String input = "ckczppom";      // original input
        String inputNew = "ckczppom";
        String output = getMd5(input);

        System.out.println("Input = " + input);
        System.out.println("HASHED = " + output);
        System.out.println();

        int i = 1;
        while (doingOne) {
            inputNew = input;
            inputNew += i;
            output = getMd5(inputNew);

//            System.out.println("input =" + inputNew);
//            System.out.println("output =" + output);
//            System.out.print(i + ", ");

            if (Objects.equals(output.charAt(0), '0') &&
                    Objects.equals(output.charAt(1), '0') &&
                    Objects.equals(output.charAt(2), '0') &&
                    Objects.equals(output.charAt(3), '0') &&
                    Objects.equals(output.charAt(4), '0') &&
                    Objects.equals(output.charAt(5), '0')) {
                doingOne = false;
            }
            i++;
        }

        System.out.println("Input = " + input);
        System.out.println("InputNew = " + inputNew);

        System.out.println("i=" + i);
        output = getMd5(inputNew);
        System.out.println("Input = " + input);
        System.out.println("Output = " + output);

//
//// PART TWO           3938039
//
//
//        Input = ckczppom
//        InputNew = ckczppom3938038
//        i=3938039
//        Input = ckczppom
//        Output = 00000028023e3b4729684757f8dc3fbf
//
//        Input = ckczppom
//        InputNew = ckczppom40268533
//        i=40268534
//        Input = ckczppom
//        Output = 000000a69ca8a244d132533e5db0440c
//
//
//
//        System.out.println("\n\npart two");
//        boolean doingTwo = true;
//
//        String outputTWO = getMd5(input);
//
//        i = 1;
//
//        while (doingTwo) {
//            inputNew = input;
//            inputNew += i;
//            outputTWO = getMd5(inputNew);
//
//            if (Objects.equals(outputTWO.charAt(0), '0') &&
//                    Objects.equals(outputTWO.charAt(1), '0') &&
//                    Objects.equals(outputTWO.charAt(2), '0') &&
//                    Objects.equals(outputTWO.charAt(3), '0') &&
//                    Objects.equals(outputTWO.charAt(4), '0') &&
//                    Objects.equals(outputTWO.charAt(5), '0')) {
//                //ADDED LINE 60 TO FIND six zeroes AT BEGINNING
        //          BUT CODE IS NOT WORKING ????
//
//
//                    !Objects.equals(output.charAt(6), '1') &&
//                    !Objects.equals(output.charAt(6), '2') &&
//                    !Objects.equals(output.charAt(6), '3') &&
//                    !Objects.equals(output.charAt(6), '4') &&
//                    !Objects.equals(output.charAt(6), '5') &&
//                    !Objects.equals(output.charAt(6), '6') &&
//                    !Objects.equals(output.charAt(6), '7') &&
//                    !Objects.equals(output.charAt(6), '8') &&
//                    !Objects.equals(output.charAt(6), '9') &&
//                    !Objects.equals(output.charAt(6), '0')
//                //WITH THIS IS ALSO NOT WORKING, CANNOT FIND GOOD SOLUTION
//
//                doingTwo = false;
//            }
//            i++;
//        }
//        System.out.println("Input = " + input);
//        System.out.println("InputNew = " + inputNew);
//
//        System.out.println("i=" + i);
//        outputTWO = getMd5(inputNew);
//        System.out.println("Input = " + input);
//        System.out.println("Output = " + outputTWO);
    }
}       //}