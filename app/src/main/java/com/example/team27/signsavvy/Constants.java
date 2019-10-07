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
//        if(text.equals("Buy")) {
//            path = "android.resource://" + packageName + "/" + R.raw.;
//        }
        if (text.equals("house")) {
            path = "android.resource://" + packageName + "/" + R.raw.arizona;
        } else if (text.equals("fun")) {
            path = "android.resource://" + packageName + "/" + R.raw.california;
        } else if (text.equals("Hope")) {
            path = "android.resource://" + packageName + "/" + R.raw.colorado;
        } else if (text.equals("Arrive")) {
            path = "android.resource://" + packageName + "/" + R.raw.florida;
        } else if (text.equals("Really")) {
            path = "android.resource://" + packageName + "/" + R.raw.georgia;
        } else if (text.equals("Read")) {
            path = "android.resource://" + packageName + "/" + R.raw.hawaii;
        } else if (text.equals("Lip")) {
            path = "android.resource://" + packageName + "/" + R.raw.illinois;
        } else if (text.equals("Mouth")) {
            path = "android.resource://" + packageName + "/" + R.raw.indiana;
        } else if (text.equals("Some")) {
            path = "android.resource://" + packageName + "/" + R.raw.kansas;
        } else if (text.equals("Communicate")) {
            path = "android.resource://" + packageName + "/" + R.raw.louisiana;
        } else if (text.equals("Write")) {
            path = "android.resource://" + packageName + "/" + R.raw.massachusetts;
        } else if (text.equals("Create")) {
            path = "android.resouace://" + packageName + "/" + R.raw.michigan;
        } else if (text.equals("Pretend")) {
            path = "android.resource://" + packageName + "/" + R.raw.minnesota;
        } else if (text.equals("Sister")) {
            path = "android.resource://" + packageName + "/" + R.raw.nevada;
        } else if (text.equals("Man")) {
            path = "android.resource://" + packageName + "/" + R.raw.new_jersey;
        } else if (text.equals("One")) {
            path = "android.resource://" + packageName + "/" + R.raw.new_mexico;
        } else if (text.equals("Drive")) {
            path = "android.resource://" + packageName + "/" + R.raw.new_york;
        } else if (text.equals("Perfect")) {
            path = "android.resource://" + packageName + "/" + R.raw.ohio;
        } else if (text.equals("Mother")) {
            path = "android.resource://" + packageName + "/" + R.raw.pennsylvania;
        } else if (text.equals("SouthCarolina")) {
            path = "android.resource://" + packageName + "/" + R.raw.south_carolina;
        } else if (text.equals("Texas")) {
            path = "android.resource://" + packageName + "/" + R.raw.texas;
        } else if (text.equals("Utah")) {
            path = "android.resource://" + packageName + "/" + R.raw.utah;
        } else if (text.equals("Washington")) {
            path = "android.resource://" + packageName + "/" + R.raw.washington;
        } else if (text.equals("Wisconsin")) {
            path = "android.resource://" + packageName + "/" + R.raw.wisconsin;
        }
        return path;
    }

}
