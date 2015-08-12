package pr01;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * Created by Dotin School1 on 8/10/2015.
 */
public class WriteToFile {
    public void write(List list) throws FileNotFoundException {
        RandomAccessFile file = new RandomAccessFile(".writeToFile.txt", "wr");

    }
}
