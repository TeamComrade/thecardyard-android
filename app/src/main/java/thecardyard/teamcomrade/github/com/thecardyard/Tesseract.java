package thecardyard.teamcomrade.github.com.thecardyard;
import android.content.Context;

import java.io.File;

public interface Tesseract {

    void Setup(Context context);

    String executeOCR(File a);

    String executeOCR(String Filename);

    void destroyOCR();
}
