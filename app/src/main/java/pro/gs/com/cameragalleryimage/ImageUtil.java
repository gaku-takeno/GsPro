package pro.gs.com.cameragalleryimage;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.DisplayMetrics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUtil {

    private Activity context;

    private Uri photoUri;
    private String path;


    public ImageUtil (Activity context) {
        this.context = context;
    }


    /**
     * @param viewWidth
     * @param viewHeight
     * @return
     */
    protected Bitmap loadBitmap(int viewWidth, int viewHeight) {

        try {

            // Uriから画像を読み込みBitmapを作成
            Bitmap originalBitmap = null;

            BitmapFactory.Options options = new BitmapFactory.Options();

            options.inSampleSize = 3;

            // ARGBでそれぞれ0～127段階の色を使用（メモリ対策）
//        options.inPreferredConfig = Config.ARGB_4444;
            // システムメモリ上に再利用性の無いオブジェクトがある場合に勝手に解放（メモリ対策）
            options.inPurgeable = true;
            // 現在の表示メトリクスの取得
            DisplayMetrics dm = context.getResources().getDisplayMetrics();
            // ビットマップのサイズを現在の表示メトリクスに合わせる（メモリ対策）
            options.inDensity = dm.densityDpi;

            AssetFileDescriptor fileDescriptor =null;
            try {
                fileDescriptor = context.getContentResolver().openAssetFileDescriptor( photoUri, "r");
            } catch (FileNotFoundException e) {
                // TODO 自動生成された catch ブロック
                e.printStackTrace();
            }

            originalBitmap
                    = BitmapFactory.decodeFileDescriptor(
                    fileDescriptor.getFileDescriptor(), null, options);

//            BitmapFactory.Options imageOptions;
//            imageOptions = new BitmapFactory.Options();


            // MediaStoreから回転情報を取得
            final int orientation;
            Cursor cursor = MediaStore.Images.Media.query(context.getContentResolver(), photoUri, new String[] {
                    MediaStore.Images.ImageColumns.ORIENTATION
            });
            if (cursor != null) {
                cursor.moveToFirst();
                orientation = cursor.getInt(0);
            } else {
                orientation = 0;
            }

            final int originalWidth = originalBitmap.getWidth();
            final int originalHeight = originalBitmap.getHeight();

            // 縮小割合を計算
            final float scale;
            if (orientation == 90 || orientation == 270) {
                scale = Math.min((float)viewWidth / originalHeight, (float)viewHeight / originalWidth);

            } else {
                scale = Math.min((float)viewWidth / originalWidth, (float)viewHeight / originalHeight);

            }

            // 変換行列の作成
            final Matrix matrix = new Matrix();
            if (orientation != 0) {
                matrix.postRotate(orientation);
            }
            if (scale < 1.0f) {
                matrix.postScale(scale, scale);

            }

            Bitmap newbm = Bitmap.createBitmap(originalBitmap, 0, 0, originalWidth, originalHeight, matrix,true);

            // 行列によって変換されたBitmapを返す
            return newbm;

        } catch( OutOfMemoryError e ) {
            return null;
        }

    }



//    /*
//    * 画像ライブラリボタンのClick押下時
//    */
//    protected void libraryApp_Click(){
//
//        // インテント設定
//        Intent intent = new Intent(Intent.ACTION_PICK);
//        // とりあえずストレージ内の全イメージ画像を対象
//        intent.setType("image/*");
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        // ギャラリー表示
//        context.startActivityForResult(intent, Constants.REQUEST_PICK_LIB);
//
//
//    }
//
//    /*
//     * カメラボタンのClick押下時
//     */
//    protected void cameraApp_Click(){
//
//        this.photoUri = getPhotoUri();
//        Intent intent = new Intent();
//        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
//        intent.addCategory(Intent.CATEGORY_DEFAULT);
//        intent.putExtra(MediaStore.EXTRA_OUTPUT, this.photoUri);
//        context.startActivityForResult(intent, Constants.REQUEST_PICK_CAM);
//
//    }

    protected void setPhotoUri (Uri uri) {
        this.photoUri = uri;
    }


    /**
     * 画像のディレクトリパスを取得する
     *
     * @return
     */
    private String getDirPath() {
        String dirPath = "";
        File photoDir = null;
        File extStorageDir = Environment.getExternalStorageDirectory();
        if (extStorageDir.canWrite()) {
            photoDir = new File(extStorageDir.getPath() + "/" + context.getPackageName());
        }
        if (photoDir != null) {
            if (!photoDir.exists()) {
                photoDir.mkdirs();
            }
            if (photoDir.canWrite()) {
                dirPath = photoDir.getPath();
            }
        }
        return dirPath;
    }

    /**
     * 画像のUriを取得する
     *
     * @return
     */
    Uri getPhotoUri() {

        long currentTimeMillis = System.currentTimeMillis();
        Date today = new Date(currentTimeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
        String title = dateFormat.format(today);
        String dirPath = getDirPath();
        String fileName = "samplecameraintent_" + title + ".jpg";
        path = dirPath + "/" + fileName;
        File file = new File(path);
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, title);
        values.put(MediaStore.Images.Media.DISPLAY_NAME, fileName);
        values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg");
        values.put(MediaStore.Images.Media.DATA, path);
        values.put(MediaStore.Images.Media.DATE_TAKEN, currentTimeMillis);
        if (file.exists()) {
            values.put(MediaStore.Images.Media.SIZE, file.length());
        }
        Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);
        return uri;

    }


    protected String getPath () {
        return this.path;
    }



    /**
     * 画像の回転後のマトリクスを取得
     *
     * @return matrix 回転後のマトリクス
     */
    protected int getOrientationCamera(String path){

        ExifInterface exifInterface = null;
        int orientation = 0;

        try {
            exifInterface = new ExifInterface(path);

            if (exifInterface != null) {
                // 画像の向きを取得
                orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            }

        } catch (IOException e) {
            e.printStackTrace();

        }

        return  orientation;
    }



    protected int getOrientationGallery(Uri uri) {


        // MediaSt/oreから回転情報を取得
        final int orientation;
        Cursor cursor = MediaStore.Images.Media.query(context.getContentResolver(), uri, new String[] {
                MediaStore.Images.ImageColumns.ORIENTATION
        });
        if (cursor != null) {
            cursor.moveToFirst();
            orientation = cursor.getInt(0);
        } else {
            orientation = 0;
        }

        if (orientation == 90 || orientation == 270) {
            return 6;
        } else {
            return 1;
        }
    }


}
