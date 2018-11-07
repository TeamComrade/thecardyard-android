package thecardyard.teamcomrade.github.com.thecardyard;

import android.Manifest;
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
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends Activity{
    Button button;
    ImageView imageview;
    TextView textView;
    static final int CAM_REQUEST = 1;
    private DBCommunicate db;
    private String dbCall = "";
    Activity act = this;
    private Context c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = this;
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        imageview = findViewById(R.id.image_view);
        textView = findViewById(R.id.tessdata);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestwritePerms();
                Intent Picture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (Picture.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(Picture, CAM_REQUEST);
                }
                createNew(c);
                db.execute();
            }
        });
    }
    private void createNew(Context context){
        db = new DBCommunicate(context, dbCall);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            File f = new File(this.getCacheDir(),"image");
            try{
                f.createNewFile();
                FileOutputStream fos = new FileOutputStream(f);
                ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                imageBitmap.compress(Bitmap.CompressFormat.PNG, 0, bytes);
                fos.write(bytes.toByteArray());
                fos.flush();
                fos.close();
                Tess ts = new Tess();
                ts.Setup(this);
                textView.setText(ts.executeOCR(f));
                //ts.executeOCR(f);
                imageview.setImageBitmap(imageBitmap);
            }catch(IOException | NullPointerException e){
                Log.v("Errors",e.toString());
            }



        }
    }
    public void RequestwritePerms(){
        PermissionRequest.requestPermission(this.act);
    }

}

