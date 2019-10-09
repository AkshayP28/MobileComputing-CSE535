package com.example.team27.signsavvy;

public class Constants {
    static final int REQUEST_VIDEO_CAPTURE = 1111;
    static final int REQUEST_CODE_UPLOAD = 2222;
    static final int RETURN_VIDEO_ACTIVITY_ABORT = 3333;
    static final int RETURN_VIDEO_ACTIVITY_SUCCESS = 4444;
    static final int VIDEO_REJECTED = 5555;
    static final int VIDEO_ACCEPTED = 6666;
    static final int REQUEST_SHOW_VIDEO = 7777;


    static String getFilePath(String text, String packageName) {
        String path = "";
        if (text.equals("Buy")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/6/6442.mp4";
        } else if (text.equals("House")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/23/23234.mp4";
        } else if (text.equals("Fun")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/22/22976.mp4";
        } else if (text.equals("Hope")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/22/22197.mp4";
        } else if (text.equals("Arrive")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/14/14210.mp4";
        } else if (text.equals("Really")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/24/24977.mp4";
        } else if (text.equals("Read")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/7/7042.mp4";
        } else if (text.equals("Lip")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/26/26085.mp4";
        } else if (text.equals("Mouth")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/22/22188.mp4";
        } else if (text.equals("Some")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/23/23931.mp4";
        } else if (text.equals("Communicate")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/22/22897.mp4";
        } else if (text.equals("Write")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/8/8441.mp4";
        } else if (text.equals("Create")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/22/22337.mp4";
        } else if (text.equals("Pretend")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/25/25901.mp4";
        } else if (text.equals("Sister")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/21/21587.mp4";
        } else if (text.equals("Man")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/21/21568.mp4";
        } else if (text.equals("One")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/11/11001.mp4";
        } else if (text.equals("Drive")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/23/23918.mp4";
        } else if (text.equals("Perfect")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/24/24791.mp4";
        } else if (text.equals("Mother")) {
            path = "https://www.signingsavvy.com/media/mp4-ld/21/21571.mp4";
        }
            return path;
        }

    }

