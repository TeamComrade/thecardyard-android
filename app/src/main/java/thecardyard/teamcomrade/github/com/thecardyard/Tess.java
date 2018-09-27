package thecardyard.teamcomrade.github.com.thecardyard;
import android.content.res.Resources;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;
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

    public void Setup(){

        copyFiletoExternalStorage(R.raw.eng,"eng.traineddata");
    }

    private void copyFiletoExternalStorage(int resourceId, String resourceName){
        // https://stackoverflow.com/questions/8664468/copying-raw-file-into-sdcard
        String pathSDCard = Environment.getExternalStorageDirectory() + "/Android/data/" + resourceName;
        try{
            InputStream in = Resources.getSystem().openRawResource(resourceId);
            FileOutputStream out = null;
            out = new FileOutputStream(pathSDCard);
            byte[] buff = new byte[1024];
            int read = 0;
            try {
                while ((read = in.read(buff)) > 0) {
                    out.write(buff, 0, read);
                }
            } finally {
                in.close();
                out.close();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
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
        //TODO Implement Overloaded OCR for extendable implementation
        return null;
    }

    /**
     * Free up all Memory used by Tesseract, This should be called when the App is being destroyed
     */
    public void destroyOCR(){
        Tess.end();
    }
}
