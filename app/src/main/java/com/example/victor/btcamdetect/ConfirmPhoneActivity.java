package com.example.victor.btcamdetect;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.victor.btcamdetect.network.SiteApi;


/**
 * Created by victor.shkanov on 01.08.2016.
 */
public class ConfirmPhoneActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_phone);

        Intent intent = new Intent("com.google.zxing.client.android.SCAN");
        intent.putExtra("SCAN_MODE", "QR_CODE_MODE");
        startActivityForResult(intent, 0);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                String content = intent.getStringExtra("SCAN_RESULT");
                String format = intent.getStringExtra("SCAN_RESULT_FORMAT");

                try {
                    SiteApi.phoneConfirm(content, getBaseContext());
                } catch(Exception e) {
                    Log.i("get", Log.getStackTraceString(e));
                }
                // Handle successful scan

            } else if (resultCode == RESULT_CANCELED) {
                // Handle cancel
                Log.i("App","Scan unsuccessful");
            }
        }
    }
}
