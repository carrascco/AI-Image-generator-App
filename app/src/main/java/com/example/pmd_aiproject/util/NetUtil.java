package com.example.pmd_aiproject.util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
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
    @Nullable
    public static Response generateImagePet(String prompt) {
        Response r=null;
        try {
            URL url = new URL("https://api.openai.com/v1/images/generations");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.addRequestProperty("Authorization", "Bearer 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");
            conn.addRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            String jsonInputString = "{\"prompt\": \""+prompt+"\"}";
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }
            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            String json = "";
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                json += line;
            }
            reader.close();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            r = gson.fromJson(json, Response.class);
            // System.out.println(r);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return r;
    }


    public static Response generateImagePet(File file) {
        Response r=null;
        try {

            URL url = new URL("https://api.openai.com/v1/images/variations");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestProperty("Authorization","Bearer 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");

            String boundary = "*****";
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            DataOutputStream request = new DataOutputStream(conn.getOutputStream());



            request.writeBytes("--" + boundary + "\r\n");
            request.writeBytes("Content-Disposition: form-data; name=\"image\"; filename=\"" + file.getName() + "\"\r\n\r\n");
            request.write(FileUtils.readFileToByteArray(file));
            request.writeBytes("\r\n");

            request.writeBytes("--" + boundary + "--\r\n");
            request.flush();

            InputStream is = conn.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            String json = "";
            while ((line = reader.readLine()) != null) {
                //System.out.println(line);
                json += line;
            }
            reader.close();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            r = gson.fromJson(json, Response.class);
            // System.out.println(r);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return r;
    }


}
