package com.example.spotlight.network.Util;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

public class FileUtils {
    public static String getPath(Context context, Uri uri) {
        try {
            String[] projection = {MediaStore.Images.Media.DATA};
            Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);
            if (cursor != null) {
                int columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                String path = cursor.getString(columnIndex);
                cursor.close();
                Log.d("FileUtils", path);
                return path;
            }
        } catch (Exception e) {
            Log.d("FileUtils", e.getMessage());
        }
        return null;
    }
}