package com.example.team27.signsavvy;

public class Constants {
    static final int REQUEST_VIDEO_CAPTURE = 1111;
    static final int REQUEST_CODE_UPLOAD = 2222;
    static final int RETURN_VIDEO_ACTIVITY_ABORT = 3333;
    static final int RETURN_VIDEO_ACTIVITY_SUCCESS = 4444;
    static final int VIDEO_REJECTED = 5555;
    static final int VIDEO_ACCEPTED = 6666;
    static final int REQUEST_SHOW_VIDEO = 7777;
    static String email;
    static String userId;

    static String getFilePath(String text, String packageName) {
        String path = "";
        if (text.equals("Buy")) {
            path = "android.resource://" + packageName + "/" + R.raw.texas;
        } else if (text.equals("House")) {
            path = "android.resource://" + packageName + "/" + R.raw.ohio;
        } else if (text.equals("Fun")) {
            path = "android.resource://" + packageName + "/" + R.raw.fun;
        } else if (text.equals("Hope")) {
            path = "android.resource://" + packageName + "/" + R.raw.hope;
        } else if (text.equals("Arrive")) {
            path = "android.resource://" + packageName + "/" + R.raw.arrive;
        } else if (text.equals("Really")) {
            path = "android.resource://" + packageName + "/" + R.raw.really;
        } else if (text.equals("Read")) {
            path = "android.resource://" + packageName + "/" + R.raw.read;
        } else if (text.equals("Lip")) {
            path = "android.resource://" + packageName + "/" + R.raw.lip;
        } else if (text.equals("Mouth")) {
            path = "android.resource://" + packageName + "/" + R.raw.mouth;
        } else if (text.equals("Some")) {
            path = "android.resource://" + packageName + "/" + R.raw.some;
        } else if (text.equals("Communicate")) {
            path = "android.resource://" + packageName + "/" + R.raw.communicate;
        } else if (text.equals("Write")) {
            path = "android.resource://" + packageName + "/" + R.raw.write;
        } else if (text.equals("Create")) {
            path = "android.resouace://" + packageName + "/" + R.raw.create;
        } else if (text.equals("Pretend")) {
            path = "android.resource://" + packageName + "/" + R.raw.pretend;
        } else if (text.equals("Sister")) {
            path = "android.resource://" + packageName + "/" + R.raw.sister;
        } else if (text.equals("Man")) {
            path = "android.resource://" + packageName + "/" + R.raw.man;
        } else if (text.equals("One")) {
            path = "android.resource://" + packageName + "/" + R.raw.one;
        } else if (text.equals("Drive")) {
            path = "android.resource://" + packageName + "/" + R.raw.drive;
        } else if (text.equals("Perfect")) {
            path = "android.resource://" + packageName + "/" + R.raw.perfect;
        } else if (text.equals("Mother")) {
            path = "android.resource://" + packageName + "/" + R.raw.mother;
        }
            return path;
        }

    }

