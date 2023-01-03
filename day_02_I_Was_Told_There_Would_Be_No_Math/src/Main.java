import java.io.*;
import java.nio.file.*;
import java.util.stream.Stream;

public class Main {

    //  load file with dimensions
    static File filename = new File(
            "C:\\Users\\bajdi\\OneDrive\\PROGRAMOWANIE\\Advent of Code\\2015\\Java\\day_02_I_Was_Told_There_Would_Be_No_Math\\src\\dimensions.txt"
    );

    public static int countLinesNew(File filename) throws IOException {
        InputStream is = new BufferedInputStream(new FileInputStream(filename));
        try {
            byte[] c = new byte[1024];

            int readChars = is.read(c);
            if (readChars == -1) {
                // bail out if nothing to read
                return 0;
            }

            // make it easy for the optimizer to tune this loop
            int count = 0;
            while (readChars == 1024) {
                for (int i = 0; i < 1024; ) {
                    if (c[i++] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            // count remaining characters
            while (readChars != -1) {
//                System.out.println(readChars);
                for (int i = 0; i < readChars; ++i) {
                    if (c[i] == '\n') {
                        ++count;
                    }
                }
                readChars = is.read(c);
            }

            return count == 0 ? 1 : count;
        } finally {
            is.close();
        }
    }


    public static void main(String[] args) throws IOException {
//        System.out.println("Total lines in file: " + countLinesNew(filename)+"\n");

        //  terms
        String example = "";
        int dimensions = 0;
        int length = 0, width = 0, height = 0;
        int first = 0, second = 0;
        int flag = 0;
        int edge = 0;
        int number = 0;

        //  gift terms
        int frontSurface = 0;
        int sideSurface = 0;
        int bottomSurface = 0;
        int totalSurface = 0;
        int extraPaper = 0;
        int giftPaper = 0;
        long totalPaper = 0;

        int giftRibbon = 0;
        long totalRibbon = 0;

        for (int f = 0; f < countLinesNew(filename); f++) {

//            if (f == 5) break;        // testing
            try (Stream<String> lines = Files.lines(Paths.get(filename.toURI()))) {
                example = lines.skip(f).findFirst().get();
//                System.out.println("\nDimensions: " + example);
            }

            //  input logic
            for (int i = 0; i < example.length(); i++) {
//                System.out.println("example.charAt(" + i + ") = " + example.charAt(i));

                if (example.charAt(i) != 'x') {
                    if (flag == 0) {
                        first = Integer.parseInt(String.valueOf(example.charAt(i)));
                    }
                    if (flag == 1) {
                        second = Integer.parseInt(String.valueOf(example.charAt(i)));
                    }
                    flag++;
                } else {
                    number = 0;

                    if (flag == 2) {
                        number = first * 10 + second;
                    } else if (flag == 1) {
                        number = first;
                    }

                    if (edge == 0) {
                        length = number;
                    } else if (edge == 1) {
                        width = number;
                    } else if (edge == 2) {
                        height = number;
                    }
                    edge++;
                    flag = 0;
                }

                if (i == example.length() - 1) {
                    if (flag == 2) {
                        number = first * 10 + second;
                    } else if (flag == 1) {
                        number = first;
                    }
                    if (edge == 2) {
                        height = number;
                    }

                    flag = 0;
                    edge = 0;
                    number = 0;
                }
            }


            //  data
//            System.out.println();
//            System.out.println("Dimensions for gift #" + (f + 1) + "\nLength = " + length +
//                    ", Width = " + width +
//                    ", Height = " + height);


            //  sufrace math
            frontSurface = height * length;
            sideSurface = height * width;
            bottomSurface = length * width;
            totalSurface = 2 * frontSurface + 2 * sideSurface + 2 * bottomSurface;

            //  extra paper logic
            if ((bottomSurface <= sideSurface) && (bottomSurface <= frontSurface)) {
                extraPaper = bottomSurface;
            } else if ((sideSurface <= bottomSurface) && (sideSurface <= frontSurface)) {
                extraPaper = sideSurface;
            } else if ((frontSurface <= sideSurface) && (frontSurface <= bottomSurface)) {
                extraPaper = frontSurface;
            }

            //  ribbon logic
            if ((2 * length + 2 * width) <= (2 * length + 2 * height) &&
                    (2 * length + 2 * width) <= (2 * width + 2 * height)) {
                giftRibbon = (2 * length + 2 * width);
            } else if ((2 * length + 2 * height) <= (2 * width + 2 * height) &&
                    (2 * length + 2 * height) <= (2 * width + 2 * length)) {
                giftRibbon = (2 * length + 2 * height);
            } else if ((2 * width + 2 * height) <= (2 * length + 2 * height) &&
                    (2 * width + 2 * height) <= (2 * length + 2 * width)) {
                giftRibbon = ((2 * width) + (2 * height));
            }
//            System.out.println("Ribbon = " + giftRibbon);

            dimensions = length * width * height;
//            System.out.println("dimensions = " + dimensions);

//            System.out.println("total ribbon = "+(giftRibbon+dimensions));

            giftPaper = totalSurface + extraPaper;
//            System.out.println("giftPaper = " + giftPaper);

            giftRibbon = giftRibbon + dimensions;
//            System.out.println("giftRibbon = " + giftRibbon);
/*
            System.out.println();
            System.out.println("Front = " + frontSurface+", Side = " + sideSurface+", Bottom = " + bottomSurface);
            System.out.println("Total = " + totalSurface+", Extra = " + extraPaper);
            System.out.println();
            System.out.println("Paper for single gift = " + giftPaper);
*/
            totalPaper = totalPaper + giftPaper;
            totalRibbon = totalRibbon + giftRibbon;


        }
        System.out.println();
        System.out.println("Total paper for all gifts = " + totalPaper);
        System.out.println("Total ribbon for all gifts = " + totalRibbon);
    }
}