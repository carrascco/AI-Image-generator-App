package com.example.pmd_aiproject.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class NetUtil {
    public static String getURLText(String url) throws Exception {
        URL website = new URL(url);
        URLConnection connection = website.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        connection.getInputStream()));

        StringBuilder response = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            response.append(inputLine);

        in.close();

        return response.toString();
    }

    public static Bitmap getURLBitmap(String url) throws Exception{
        URL website = new URL(url);
        URLConnection connection = website.openConnection();

        Bitmap bmp=    BitmapFactory.decodeStream(connection.getInputStream());
        return bmp;
    }


}
