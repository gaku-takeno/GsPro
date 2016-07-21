package pro.gs.com.cameragalleryimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import static android.util.Log.v;

/**
 * Created by takenogaku on 2016/07/19.
 */
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
        bitmapOrg=null;
        return bitmap;
    }


}
