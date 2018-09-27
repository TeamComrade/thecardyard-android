package thecardyard.teamcomrade.github.com.thecardyard;
import java.io.File;

public interface Tesseract {

    void Setup();

    String executeOCR(File a);

    String executeOCR(String Filename);

    void destroyOCR();
}
