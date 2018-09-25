package thecardyard.teamcomrade.github.com.thecardyard;
import java.io.File;
/*
=== Tess-Two===

tess-two Import structure
import com.googlecode.tesseract.android.*;

Java Documentation for tess-two
https://rmtheis.github.io/tess-two/javadoc/index.html
*/

import com.googlecode.tesseract.android.TessBaseAPI;
public class Tess implements Tesseract {
    public String executeOCR(File a){
        //TODO Run this through various PSM's looking for best result
        TessBaseAPI Tesseract = new TessBaseAPI();
        
        return null;
    }
    public String executeOCR(String Filename) {
        //TODO Implement Overloaded OCR for extendable implementation
        return null;
    }
}
