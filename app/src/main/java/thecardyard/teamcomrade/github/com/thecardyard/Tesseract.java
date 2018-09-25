package thecardyard.teamcomrade.github.com.thecardyard;
import java.io.File;

public interface Tesseract {

    String executeOCR(File a);

    String executeOCR(String Filename);

    void destroyOCR();
}
