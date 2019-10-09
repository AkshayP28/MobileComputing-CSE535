package com.example.team27.signsavvy;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Environment;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

import static com.example.team27.signsavvy.LoginActivity.INTENT_ID;
import static com.example.team27.signsavvy.LoginActivity.INTENT_SERVER_ADDRESS;
import static com.example.team27.signsavvy.LoginActivity.INTENT_TIME_WATCHED;
import static com.example.team27.signsavvy.LoginActivity.INTENT_WORD;
import static com.example.team27.signsavvy.LoginActivity.LAST_NAME;

public class VideoActivity extends Activity implements SurfaceHolder.Callback {

    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private SurfaceView mSurfaceView;
    private SurfaceHolder mHolder;
    private Button mToggleButton;
    private Button uploadButton;
    private TextView tv_timer;
    String returnfile = "";
    VideoActivity activity;
    String word;
    private boolean mInitSuccesful;
    SharedPreferences sharedPreferences;
    CountDownTimer timer;
    CountDownTimer time;
    long time_watched;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        activity = this;
        // we shall take the video in portrait orientation
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        if (getIntent().hasExtra(INTENT_WORD)) {
            word = getIntent().getStringExtra(INTENT_WORD);
        }
        if (getIntent().hasExtra(INTENT_TIME_WATCHED)) {
            time_watched = getIntent().getLongExtra(INTENT_TIME_WATCHED, 0);
        }
        mSurfaceView = (SurfaceView) findViewById(R.id.sv_camera);
        mHolder = mSurfaceView.getHolder();
        mHolder.addCallback(this);
        tv_timer = (TextView) findViewById(R.id.tv_timer);
        mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
        sharedPreferences = this.getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);

        mToggleButton = (Button) findViewById(R.id.bt_start);
        uploadButton = findViewById(R.id.upload);
        uploadButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onUploadClicked();
            }
        });
        time = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {
                int a = (int) (l / 1000);

            }

            @Override
            public void onFinish() {
                mMediaRecorder.stop();
                mMediaRecorder.reset();
                if (time != null) {
                    time.cancel();
                }
//            @TODO - siddarth - returnfile has file path
            }
        };
        mToggleButton.setOnClickListener(new OnClickListener() {
            @Override
            // toggle video recording
            public void onClick(final View v) {
                timer = new CountDownTimer(5000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        int a = (int) (millisUntilFinished / 1000);
                        tv_timer.setText(a + " ");
                        ((Button) v).setEnabled(false);
                    }

                    public void onFinish() {
                        tv_timer.setVisibility(View.GONE);
                        ((Button) v).setText("Stop Recording");
                        ((Button) v).setEnabled(true);
                        mMediaRecorder.start();
                        time.start();
                    }
                };
                if (((Button) v).getText().toString().equals("Start Recording")) {
                    timer.start();
                } else if (((Button) v).getText().toString().equals("Stop Recording")) {
                    mMediaRecorder.stop();
                    mMediaRecorder.reset();
                    ((Button) v).setText("Start Recording");
                    if (time != null) {
                        time.cancel();
                    }



                }
            }
        });
    }

    private void onUploadClicked() {
            String server_ip = getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE).getString(INTENT_SERVER_ADDRESS, "10.211.17.171");
            String group_id = getSharedPreferences(this.getPackageName(), Context.MODE_PRIVATE).getString("group_id", "27");
            // http://10.218.107.121/cse535/upload_video.php
            RequestParams params = new RequestParams();
            if (returnfile.equals("")) {
                return;
            }
            File f = new File(returnfile);
            if (!f.exists()) {
                Log.e("TAG", "File not found");
                return;
            }
            // Open the file pointed by the file Path and pass it here
            try {
                params.put("uploaded_file", f);
            } catch (FileNotFoundException e) {
                Log.e("ERROR", "file not found");
                return;
            }
            params.put("group_id", group_id);
            params.put("id", "1215148025");
            params.put("accept", 1);
            AsyncHttpClient client = new AsyncHttpClient();
            String url = "http://34.219.189.217/upload_video.php";
            //String url = "http://" + server_ip + "/cse535/upload_video.php";
            client.post(url, params, new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, byte[] bytes) {
                    // handle success response
                    Log.e("msg success", statusCode + "");
                    if (statusCode == 200) {
                        Toast.makeText(VideoActivity.this, "Successfully uploaded file", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(VideoActivity.this, "Failed to upload file", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, byte[] bytes, Throwable throwable) {
                    // handle failure response
                    Log.e("msg fail", statusCode + "");
                    Toast.makeText(VideoActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();

                }

            });

    }

    /* Init the MediaRecorder, the order the methods are called is vital to
     * its correct functioning */
    boolean fileCreated = false;

    private void initRecorder(Surface surface) throws IOException {
        // It is very important to unlock the camera before doing setCamera
        // or it will results in a black preview

        if (mCamera == null) {
            mCamera = Camera.open(1);
            mCamera.setDisplayOrientation(90);
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
            mCamera.unlock();

        }

        if (mMediaRecorder == null)
            mMediaRecorder = new MediaRecorder();
        mMediaRecorder.setPreviewDisplay(surface);
        mMediaRecorder.setCamera(mCamera);
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.DEFAULT);
        int i = 0;
        SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss", Locale.US);
        String format = s.format(new Date());
        File file = new File(Environment.getExternalStorageDirectory().getPath() + "/signsavvy/"
                + sharedPreferences.getString(INTENT_ID, "0000") + word + "_PRACTICE_" + "_0_" + sharedPreferences.getString(LAST_NAME, "0000") + ".mp4");
        //just to be safe
//        GESTURE_PRACTICE_(Practice Number) _USERLASTNAME.mp4
        while (file.exists()) {
            i++;
            file = new File(Environment.getExternalStorageDirectory().getPath() + "/signsavvy/"
                    + sharedPreferences.getString(INTENT_ID, "0000") + word + "_PRACTICE_" + i + "_" + sharedPreferences.getString(LAST_NAME, "0000") + ".mp4");
        }

        if (file.createNewFile()) {
            fileCreated = true;
            Log.e("file path", file.getPath());
            returnfile = file.getPath();
        }


        mMediaRecorder.setOutputFile(file.getPath());
        // No limit. Check the space on disk!
        mMediaRecorder.setMaxDuration(5000);
        mMediaRecorder.setVideoSize(320, 240);
        mMediaRecorder.setOnInfoListener(new MediaRecorder.OnInfoListener() {
            @Override
            public void onInfo(MediaRecorder mediaRecorder, int i, int i1) {
                if (i == MediaRecorder.MEDIA_RECORDER_INFO_MAX_DURATION_REACHED) {    //finish after max duration has been reached
                    mMediaRecorder.stop();
                    mMediaRecorder.reset();
                    if (time != null) {
                        time.cancel();
                    }
//                    activity.setResult(Constants.RETURN_VIDEO_ACTIVITY_SUCCESS, returnIntent);
//                    activity.finish();
                }

            }
        });

        //mMediaRecorder.setVideoFrameRate(30);
        mMediaRecorder.setOrientationHint(270);
        //mMediaRecorder.setVideoSize(640, 480);
        mMediaRecorder.setVideoFrameRate(30); //might be auto-determined due to lighting
        mMediaRecorder.setVideoEncodingBitRate(3000000);
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.H264);// MPEG_4_SP
        //mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);


        //mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.DEFAULT);
        try {

            mMediaRecorder.prepare();
        } catch (IllegalStateException e) {
            // This is thrown if the previous calls are not called with the
            // proper order
            e.printStackTrace();
        }

        mInitSuccesful = true;
    }


    @Override
    public void onBackPressed() {
        if (timer != null)
            timer.cancel();
        if (time != null)
            time.cancel();

        super.onBackPressed();
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        try {
            if (!mInitSuccesful)
                initRecorder(mHolder.getSurface());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        //shutdown();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

}