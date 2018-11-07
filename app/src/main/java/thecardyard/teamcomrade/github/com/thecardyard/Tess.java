package thecardyard.teamcomrade.github.com.thecardyard;
import android.content.Context;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.*;
import java.util.*;
/*
=== Tess-Two===

tess-two Import structure
import com.googlecode.tesseract.android.*;

Java Documentation for tess-two
https://rmtheis.github.io/tess-two/javadoc/index.html
*/

import com.googlecode.tesseract.android.TessBaseAPI;

public class Tess extends ActivityCompat implements Tesseract {

    private TessBaseAPI Tess = new TessBaseAPI();
    private Context Context;
    protected File sdCard;
    String Dir;
    protected File filedork;

    public void Setup(Context context){
        //folder = new File(Environment.getExternalStorageDirectory(), "tessdata");
        this.Context = context;
        this.sdCard = Environment.getExternalStorageDirectory();
        this.Dir = Context.getFilesDir().getAbsolutePath() + "/tessdata/";
//        if(!folder.exists()) {
//            folder.mkdir();
//        }
        copyFiletoExternalStorage(R.raw.eng, "eng.traineddata");

    }


    private void copyFiletoExternalStorage(int resourceId, String resourceName) {
        // https://stackoverflow.com/questions/8664468/copying-raw-file-into-sdcard
//        Log.w("Tesseract", "ResourceID: " + resourceId +" resourceName: " + resourceName);
//        String pathSDCard = folder.getAbsolutePath()+"/"+ resourceName;
//        try{
//            InputStream in = Context.getResources().openRawResource(resourceId);
//            FileOutputStream out = new FileOutputStream(pathSDCard);
//            byte[] buff = new byte[1024];
//            int read = 0;
//            try {
//                while ((read = in.read(buff)) > 0) {
//                    out.write(buff, 0, read);
//                }
//            } finally {
//                in.close();
//                out.close();
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//      }

                Log.i("Tess", "Setup::copyFile");
                InputStream in = this.Context.getResources().openRawResource(resourceId);


                String filename = this.Context.getResources().getResourceEntryName(resourceId);
                File folder = new File(Dir);
                File f = new File(filename);
                this.filedork = new File(Dir, filename + ".traineddata");
                //if (!this.filedork.exists()) {
                    try {


                        if(!folder.exists()){
                            folder.mkdir();
                        }

                        OutputStream out = new FileOutputStream(this.filedork);
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = in.read(buffer, 0, buffer.length)) != -1) {
                            out.write(buffer, 0, len);
                        }
                        in.close();
                        out.close();
                    } catch (FileNotFoundException e) {
                        Log.i("Tess", "Setup::copyFile - " + e.getMessage());
                    } catch (IOException e) {
                        Log.i("Tess", "Setup::copyFile - " + e.getMessage());
                    }
                    File[] files =folder.listFiles();
                    Log.d("Files", "Size: "+ files.length);
                    for (int i = 0; i < files.length; i++) {
                    Log.d("Files", "FileName:" + files[i].getName());
                     }
               }

    public String executeOCR(File a){
        //TODO Run this through various PSM's looking for best result
        boolean pass;

        //Attempt to initialize the Tesseract instance
        String datapath = Context.getFilesDir().getAbsolutePath();
        pass = Tess.init(datapath, "eng");
        Log.w("Tesseract", "Init: " + pass);

        if(!pass){
            return null;
        }

        Tess.setImage(a);
        String recognized = Tess.getUTF8Text();

        Log.d("Tess:Detect", recognized);
        return recognized;
    }
    public String executeOCR(String Filepath) {
        //TODO Implement Overloaded OCR for extendable implementation
        boolean pass;
        pass = Tess.init(Context.getFilesDir().getAbsolutePath(), "eng");
        Log.w("Tesseract", "Init: "+ pass);

        if(!pass){
            return null;
        }
        File file = new File(Filepath);
        Tess.setImage(file);

        return null;
    }

    /**
     * Free up all Memory used by Tesseract, This should be called when the App is being destroyed
     */
    public void destroyOCR(){
        Tess.end();
    }
}
