package com.example.team27.signsavvy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.facebook.stetho.Stetho;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {
    public static String INTENT_ID = "INTENT_ID";
    public static String LAST_NAME = "LAST_NAME";
    public static String INTENT_WORD = "INTENT_WORD";
    public static String INTENT_SERVER_ADDRESS = "INTENT_SERVER_ADDRESS";

    @BindView(R.id.et_lastName)
    EditText et_lastName;

    @BindView(R.id.et_id)
    EditText et_id;

    @BindView(R.id.sp_words)
    Spinner sp_words;

    String[] spinnerWordsArray;
    String lastName;
    String id;
    String path;
    boolean firstTime = true;

    SharedPreferences sharedPreferences;
    long time_to_login;
    int PERMISSION_ALL = 1;

    String[] PERMISSIONS = {
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
            android.Manifest.permission.CAMERA
    };

    public static boolean hasPermissions(Context context, String... permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        Stetho.initializeWithDefaults(this);
        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }
        spinnerWordsArray = getResources().getStringArray(R.array.spinner_words);
        sp_words.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (et_lastName.getText().toString().isEmpty() || et_id.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Fill in the last name and asu id", Toast.LENGTH_LONG).show();
                } else {
                    lastName = et_lastName.getText().toString();
                    id = et_id.getText().toString();
                    String text = sp_words.getSelectedItem().toString();
                   Intent intent = new Intent(getApplicationContext(), PracticeVideoActivity.class);
                            intent.putExtra("clicked_sign", text);
                            startActivity(intent);


                }
            }
                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
    }

}
