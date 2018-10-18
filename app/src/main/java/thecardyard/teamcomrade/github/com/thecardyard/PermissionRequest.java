package thecardyard.teamcomrade.github.com.thecardyard;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.logging.Logger;

public class PermissionRequest extends ActivityCompat {
    public static void requestPermission(Activity act) {
        int status = ActivityCompat.checkSelfPermission(act, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (status != PackageManager.PERMISSION_GRANTED) {
            Log.e("PermissionRequest","permission not granted");
            ActivityCompat.requestPermissions(act, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 4322);
        }
    }

}
