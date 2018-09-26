package thecardyard.teamcomrade.github.com.thecardyard;
import android.content.res.Resources;
import android.renderscript.ScriptGroup;
import android.util.Log;

import java.io.File;
import java.io.InputStream;
/*
=== Tess-Two===

tess-two Import structure
import com.googlecode.tesseract.android.*;

Java Documentation for tess-two
https://rmtheis.github.io/tess-two/javadoc/index.html
*/

import com.googlecode.tesseract.android.TessBaseAPI;
public class Tess implements Tesseract {

    private TessBaseAPI Tess = new TessBaseAPI();

    public String executeOCR(File a){
        //TODO Run this through various PSM's looking for best result
        boolean pass;

        //Attempt to initialize the Tesseract instance
        pass = Tess.init("res/raw/", "eng");
        Log.w("Tesseract", "Init:" + pass);

        Tess.setImage(a);

        return null;
    }
    public String executeOCR(String Filename) {
        //TODO Implement Overloaded OCR for extendable implementationg
        return null;
    }

    /**
     * Free up all Memory used by Tesseract, This should be called when the App is being destroyed
     */
    public void destroyOCR(){
        Tess.end();
    }
}
