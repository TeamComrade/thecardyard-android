package thecardyard.teamcomrade.github.com.thecardyard;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class DBCommunicate extends AsyncTask {


    private Context context;
    private String DbCall;
    public DBCommunicate(Context context, String dbCall){
        this.context = context;
        this.DbCall = dbCall;
        Log.d("DBCommunicate", "created");
    }

    protected void onPreExecute(){
    }

    @Override
    protected String doInBackground(Object[] objects){
        try{
            Log.d("DBCommunicate", "Executed");
            String username = "user";
            String password = "Password01";
            String database = "the_card_yard";
            String sql = "";

            String link = "http://172.16.176.21/w0414043/Scratch/scripts/SQLHandleScratch.php";
            String data = URLEncoder.encode("username", "UTF-8") + '=' + URLEncoder.encode(username, "UTF-8");

            data += "&" + URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
            data += "&" + URLEncoder.encode("db", "UTF-8") + '=' + URLEncoder.encode(database, "UTF-8");
            data += "&" + URLEncoder.encode("SQL", "UTF-8") + '=' + URLEncoder.encode(sql, "UTF-8");

            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new
                    InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null){
                sb.append(line);

            }

            Log.d("DBCommunicate", sb.toString());
            return sb.toString();
        } catch(Exception e){
            return "Exception: " + e.getMessage();
        }

    }
}
