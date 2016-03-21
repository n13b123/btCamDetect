package com.example.victor.btcamdetect;

import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.victor.btcamdetect.network.VicWebSocket;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button connectSocketBtn = (Button) findViewById(R.id.connectSocketBtn);
        Button sendToSocketBtn = (Button) findViewById(R.id.sendToSocketBtn);
        Button cameraBtn = (Button) findViewById(R.id.cameraBtn);
        Button cvBtn = (Button) findViewById(R.id.cvBtn);
        Button motionDetectBtn = (Button) findViewById(R.id.motionDetectBtn);

        connectSocketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // VicWebSocket ws = new VicWebSocket();
                VicWebSocket.connectWebSocket();
            }
        });

        sendToSocketBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)findViewById(R.id.editText);
                editText.setText("....");
                //VicWebSocket ws = new VicWebSocket();
                VicWebSocket.sendWebSocket(editText.getText().toString());
            }
        });

        cameraBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("cameraBtn", "press");
                Intent intent = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(intent);
            }
        });

        cvBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("openCVBtn", "press");
                Intent intent = new Intent(MainActivity.this, OpenCVActivity.class);
                startActivity(intent);
            }
        });

        motionDetectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("motionDetectBtn", "press");
                Intent intent = new Intent(MainActivity.this, MotionDetectActivity.class);
                startActivity(intent);
            }
        });

    }

    public void qwe2(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        TextView myText1 = (TextView) findViewById(R.id.myText1);
        myText1.setText("Start");

        StringBuilder result = new StringBuilder();


        try {
            URL url = new URL("http://site.victor.t.avenija.ru/q.php");
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line;
            while((line = reader.readLine()) != null) {
                result.append(line);
            }

            urlConnection.disconnect();
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        try {
            JSONObject jObject = new JSONObject(result.toString());

            TextView socketDbg = (TextView) findViewById(R.id.socketDbg);
            socketDbg.setText(jObject.getString("first"));

            JSONArray jArray = jObject.getJSONArray("second");

            //ListView myListView = (ListView)findViewById(R.id.listView1);
            final ArrayList<String> second_items = new ArrayList<String>();
            final ArrayAdapter<String> aa;
            //aa = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, jArray);

            //myListView.setAdapter(aa);

//            for (int i=0; i < jArray.length(); i++) {
//                try {
//                    JSONObject oneObject = jArray.getJSONObject(i);
//                    // Pulling items from the array
//                    String oneObjectsItem = oneObject.getString("STRINGNAMEinTHEarray");
//
//                } catch (JSONException e) {
//                    // Oops
//                }
//            }
        } catch(Exception e){

        }


    }


}
