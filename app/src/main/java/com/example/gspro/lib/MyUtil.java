package com.example.gspro.lib;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static android.util.Log.v;

public class MyUtil {


    public int changePxtoPd (Context context, int px) {

        // Convert the dips to pixels
        float scale = context.getResources().getDisplayMetrics().density;
        int mGestureThreshold = (int) (px * scale + 0.5f);
        return mGestureThreshold;

    }



    /*
    * 画像を回転させる
    */
    public Bitmap rotateImg(Bitmap bitmapOrg , int angle) {

        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();
        Bitmap bitmap;
        //縦長の場合
        if (width > height) {
            // createa matrix for the manipulation
            Matrix matrix = new Matrix();
            // rotate the Bitmap
            matrix.postRotate(angle);
            // recreate the new Bitmap
            bitmap = Bitmap.createBitmap(bitmapOrg, 0, 0,width, height, matrix, true);
            v("orienta",":"+width+":"+height);

        } else {
            v("orienta","::"+width+":"+height);
            bitmap = bitmapOrg;
        }

        return bitmap;
    }


    public byte[] changeBitmapToByte (Bitmap bm) {

        byte[] _bArray = null;
        try {
            ByteArrayOutputStream baoStream = new ByteArrayOutputStream();
            bm.compress(Bitmap.CompressFormat.JPEG, 90, baoStream);

            baoStream.flush();
            _bArray = baoStream.toByteArray();
            baoStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return _bArray;

    }



}