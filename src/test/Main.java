package test;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {


    public void testRandomAccessFile(String filename) {

        RandomAccessFile randomAccessFile = null;
        try {

            //Declare variables that we're going to write
            String line1 = "First line\n";
            String line2 = "Second line\n";

            //Create RandomAccessFile instance with read / write permissions
            randomAccessFile = new RandomAccessFile(filename, "rw");

            //Write two lines to the file
            randomAccessFile.writeBytes(line1);
            randomAccessFile.writeBytes(line2);

            //Place the file pointer at the end of the first line
            randomAccessFile.seek(line1.length());

            //Declare a buffer with the same length as the second line
            byte[] buffer = new byte[line2.length()];

            //Read data from the file
            randomAccessFile.read(buffer); //

            //Print out the buffer contents
            System.out.println(new String(buffer));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            try {

                if (randomAccessFile != null)
                    randomAccessFile.close();

            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Main().testRandomAccessFile("myFile.txt");
    }
}

