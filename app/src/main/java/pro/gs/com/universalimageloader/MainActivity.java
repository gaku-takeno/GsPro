package pro.gs.com.universalimageloader;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

public class MainActivity extends AppCompatActivity {


    //your image url
    private static String url = "http://stacktips.com/wp-content/uploads/2014/05/UniversalImageLoader-620x405.png";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setImageVerySimple();
//        setImageSimple();
//        setImage();

    }

    private void setImageVerySimple () {
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);

        ImageLoader loader = ImageLoader.getInstance();

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        loader.init(config.build());

        // loadImageを使う場合
        //Bitmapを取得出来る。
//        loader.loadImage(url, new SimpleImageLoadingListener() {
//
//            //filter加工したり、リサイズ処理をしたい場合は、ここで取り出したBitmap画像に対して行う。
//            @Override
//            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
//                imageView.setImageBitmap(loadedImage);
//            }
//        });

        // displayImageを使う場合
        //Bitmapは取得出来ない。表示のみ。
        loader.displayImage(url, imageView);
    }



    private void setImageSimple () {

        ImageLoader imageLoader = ImageLoader.getInstance();

        BitmapFactory.Options resizeOptions = new BitmapFactory.Options();
        resizeOptions.inSampleSize = 5; // decrease size 3 times
        resizeOptions.inScaled = true;

        // UNIVERSAL IMAGE LOADER SETUP
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .decodingOptions(resizeOptions).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();

        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        imageLoader.init(config.build());

        final ImageView imageView = (ImageView) findViewById(R.id.imageView);


//        imageLoader.displayImage(url, imageView, defaultOptions);

        imageLoader.displayImage(url, imageView, defaultOptions, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

                Resources res = getApplication().getResources();
                Bitmap loadingBitmap = BitmapFactory.decodeResource(res, R.drawable.loading);
                imageView.setImageBitmap(loadingBitmap);

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {}

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {



            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {}
        });


    }

    private void setImage () {

        UILApplication app = (UILApplication) getApplicationContext();
        app.setImageLoader();

        //initialize image view
        final ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);


        final ImageLoader imageLoader = app.getImageLoader();
        DisplayImageOptions defaultOptions = app.getDefaultOptions();

        //download and display image from url
//        imageLoaderConfig();
//        imageLoader.displayImage(url, imageView, defaultOptions);

        imageLoader.displayImage(url, imageView, defaultOptions, new SimpleImageLoadingListener() {
            @Override
            public void onLoadingStarted(String imageUri, View view) {

                Resources res = getApplication().getResources();
                Bitmap loadingBitmap = BitmapFactory.decodeResource(res, R.drawable.loading);
                imageView.setImageBitmap(loadingBitmap);
            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {}

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                imageView2.setImageBitmap(loadedImage);

            }
        }, new ImageLoadingProgressListener() {
            @Override
            public void onProgressUpdate(String imageUri, View view, int current, int total) {}
        });

    }


//    private void imageLoaderConfig () {
//
//        imageLoader = ImageLoader.getInstance();
//
//        BitmapFactory.Options resizeOptions = new BitmapFactory.Options();
//        resizeOptions.inSampleSize = 5; // decrease size 3 times
//        resizeOptions.inScaled = true;
//
//        // UNIVERSAL IMAGE LOADER SETUP
//        defaultOptions = new DisplayImageOptions.Builder()
//                .decodingOptions(resizeOptions).cacheInMemory(true)
//                .imageScaleType(ImageScaleType.EXACTLY)
//                .displayer(new FadeInBitmapDisplayer(300)).build();
//
//
//        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
//        config.defaultDisplayImageOptions(defaultOptions);
//        config.threadPriority(Thread.NORM_PRIORITY - 2);
//        config.denyCacheImageMultipleSizesInMemory();
//        config.memoryCache(new LruMemoryCache(50 * 1024 * 1024));
//        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
//        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
//        config.tasksProcessingOrder(QueueProcessingType.LIFO);
//        config.memoryCacheSize(50 * 1024 * 1024);
//
//        imageLoader.init(config.build());
//        // END - UNIVERSAL IMAGE LOADER SETUP
//    }



}
