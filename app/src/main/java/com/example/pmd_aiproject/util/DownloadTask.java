package com.example.pmd_aiproject.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class DownloadTask extends AsyncTask<Void, Void, Void> {
    private Context context;
    private Bitmap bitmap;
    private String fileName;
    private String directory;

    public DownloadTask(Context context, Bitmap bitmap, String fileName, String directory) {
        this.context = context;
        this.bitmap = bitmap;
        this.fileName = fileName;
        this.directory = directory;
        execute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            File path = new File(Environment.getExternalStorageDirectory(), this.directory);
            if (!path.exists()) {
                path.mkdirs();
            }
            File file = new File(path, this.fileName);
            OutputStream outputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(context, "Descarga finalizada", Toast.LENGTH_LONG).show();
    }
}