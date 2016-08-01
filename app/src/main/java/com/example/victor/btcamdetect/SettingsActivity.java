package com.example.victor.btcamdetect;

import android.app.Activity;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;

/**
 * Created by victor.shkanov on 29.03.2016.
 */
public class SettingsActivity extends Activity {
    String[] mItemsName = {"email", "websocket", "sms"};
    final boolean[] mItemsChecked = {false, true, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        this.setListeners();
    }

    private void setListeners() {
        Button addOnMotionActionBtn = (Button) findViewById(R.id.addOnMotionActionBtn);
        addOnMotionActionBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {



                AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
                builder.setTitle("Выберите события")
                        .setMultiChoiceItems(mItemsName, mItemsChecked, new DialogInterface.OnMultiChoiceClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                                mItemsChecked[which] = isChecked;
                            }
                        })
                        .setPositiveButton("Сохранить", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Log.d("AlertDialog", Boolean.toString(mItemsChecked[0]));
                            }
                        })
                        .setNegativeButton("Отмена", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();

            }
        } );
    }

}
//
//public static class SettingsOnMotionDetectActionDialog extends DialogFragment {
//    int mNum;
//
//    static SettingsOnMotionDetectActionDialog newInstance(int num) {
//        SettingsOnMotionDetectActionDialog f = new SettingsOnMotionDetectActionDialog();
//
//        Bundle args = new Bundle();
//        args.putInt("num", num);
//        f.setArguments(args);
//
//        return f;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mNum = getArguments().getInt("num");
//    }
//
////    @Override
////    public View onCreateView()
//}
