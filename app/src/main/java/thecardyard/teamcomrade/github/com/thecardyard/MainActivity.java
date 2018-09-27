package thecardyard.teamcomrade.github.com.thecardyard;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;

public class MainActivity extends Activity{
    Button button;
    ImageView imageview;
    static final int CAM_REQUEST = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        imageview = (ImageView) findViewById(R.id.image_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                if (camera_intent.resolveActivity(getPackageManager())!=null){
                    startActivityForResult(camera_intent,CAM_REQUEST);
                }
            }
        });
    }
    private File getFile(){
        File folder = new File(Environment.getExternalStorageDirectory(),"camera_app");

        if(!folder.exists()){
            folder.mkdir();
        }

        File image_file = new File(folder,"cam_image.jpg");


        return image_file;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            File img = getFile();
            String nw = img.getAbsolutePath();
            Log.v("Path", nw);
            String path = "/storage/emulated/0/camera_app/cam_image.jpg";
            imageview.setImageDrawable(Drawable.createFromPath(path));

        }
    }

}

