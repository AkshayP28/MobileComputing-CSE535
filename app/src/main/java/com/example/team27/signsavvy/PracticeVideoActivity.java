package com.example.team27.signsavvy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import java.io.File;

import static com.example.team27.signsavvy.LoginActivity.INTENT_WORD;

public class PracticeVideoActivity extends AppCompatActivity {

    private VideoView videoview;
    private Button practiceButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice_video);
        videoview = findViewById(R.id.videoView);
        practiceButton = findViewById(R.id.button);
        final String sign = getIntent().getStringExtra("clicked_sign");
        play_video(sign);
        practiceButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PracticeVideoActivity.this, VideoActivity.class);
                intent.putExtra(INTENT_WORD, sign);
                File f = new File(Environment.getExternalStorageDirectory(), "signsavvy");
                if (!f.exists()) {
                    f.mkdirs();
                }

                startActivity(intent);
            }
        });
    }


    public void play_video(String text) {
        Log.e("Text is ", text);
        String path = Constants.getFilePath(text, getPackageName());
        //String path = "android://resource/" + getPackageName() + "/" + R.raw.house;
        if (!path.isEmpty()) {
            Uri uri = Uri.parse(path);
            videoview.setVideoURI(uri);
            videoview.start();
        }
    }

}
