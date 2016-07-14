package pro.gs.com.GsProMemberList;




import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.toolbox.ImageLoader;

/**
 * ロードした画像データをキャッシュ化するクラス
 * メモリキャッシュとディスクキャッシュをしてくれる。
 */
public class BitmapCache implements ImageLoader.ImageCache {
    private LruCache<String, Bitmap> mCache;


    public BitmapCache() {

        /*メモリーの最大容量を設定。
        単位はバイトで指定する。
        1024は1キロバイト
        10 * 1024 * 1024 は、10メガとなる。
        なぜ 1Kバイト」は「1024ビット」なのか？
        それは以下のサイトを参考にしてください。
        http://detail.chiebukuro.yahoo.co.jp/qa/question_detail/q1413226859
        */
        int maxSize = 40 * 1024 * 1024;
        mCache = new LruCache<String, Bitmap>(maxSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getRowBytes() * value.getHeight(); }
        };

    }

    /**
     * getBitmapは、ImageLoaderクラスが持つImageCacheインターフェイスの抽象メソッドである。
     * BitmapCacheクラス(当ファイル)にて、抽象メソッドgetBitmapを実装する必要がある。
     * @param url
     * @return
     */
    @Override
    public Bitmap getBitmap(String url) {
        return mCache.get(url);
    }

    /**
     * putBitmapは、ImageLoaderクラスが持つImageCacheインターフェイスの抽象メソッドである。
     * BitmapCacheクラス(当ファイル)にて、抽象メソッドputBitmapを実装する必要がある。
     * @param url
     * @return
     */
    @Override
    public void putBitmap(String url, Bitmap bitmap) {
        mCache.put(url, bitmap);
    }
}
