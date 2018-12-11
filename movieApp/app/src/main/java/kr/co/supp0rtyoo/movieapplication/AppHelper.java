package kr.co.supp0rtyoo.movieapplication;

import com.android.volley.RequestQueue;

public class AppHelper {

    public static RequestQueue requestQueue;

    private static String url = "boostcourse-appapi.connect.or.kr";
    private static int port = 10000;

    public static String getUrl() {
        return url;
    }

    public static int getPort() {
        return port;
    }
}
