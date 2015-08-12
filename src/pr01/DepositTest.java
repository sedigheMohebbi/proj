package pr01;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Collections;
import java.util.List;

public class DepositTest {
    public static void main(String[] args) throws IOException {
        try {
            RandomAccessFile randomAccessFile = new RandomAccessFile(".writeToFile.txt", "rw");
            XmlParser xmlParser = new XmlParser();
            List list = xmlParser.parse();
            Collections.sort(list);
            Collections.reverse(list);
            for (Object aList : list) {
                randomAccessFile.writeBytes(aList.toString());
                randomAccessFile.writeBytes("\r\n");
            }
            randomAccessFile.read();
            randomAccessFile.seek(0);
            while (randomAccessFile.getFilePointer() < randomAccessFile.length()) { //Returns the current offset in this file.
                System.out.println(randomAccessFile.readLine());
            }

        } catch (IOException e) {
            e.printStackTrace();//

        }
    }
}
