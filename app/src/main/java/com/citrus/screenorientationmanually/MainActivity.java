package com.citrus.screenorientationmanually;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button portraitButton;
    Button landscapeButton;

    private static final String ORIENT="orient";
    private static final String PREF_NAME="orientation_pref";
    private SharedPreferences.Editor prefEdit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences pefers=getSharedPreferences(PREF_NAME,MODE_PRIVATE);
        if (pefers.getBoolean(ORIENT,false)){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
        setContentView(R.layout.activity_main);

        prefEdit = getSharedPreferences(PREF_NAME,MODE_PRIVATE).edit();

        portraitButton=(Button) findViewById(R.id.portrait_button);
        landscapeButton=(Button) findViewById(R.id.landscape_button);

        portraitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefEdit.putBoolean(ORIENT,false).commit();
                restartActivity();
            }
        });
        landscapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefEdit.putBoolean(ORIENT,true).commit();
                restartActivity();
            }
        });
    }

    private void restartActivity(){
        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
