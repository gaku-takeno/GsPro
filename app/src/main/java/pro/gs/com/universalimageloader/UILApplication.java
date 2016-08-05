package pro.gs.com.universalimageloader;

import android.app.Application;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.StrictMode;
import android.provider.SyncStateContract;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.LruMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

/**
 * Created by takenogaku on 2016/07/27.
 */
public class UILApplication extends Application {

    private DisplayImageOptions defaultOptions;
    private ImageLoader imageLoader;


    public void onCreate() {
        super.onCreate();

    }

    public void setImageLoader() {

        imageLoader = ImageLoader.getInstance();

        BitmapFactory.Options resizeOptions = new BitmapFactory.Options();
        resizeOptions.inSampleSize = 5; // decrease size 3 times
        resizeOptions.inScaled = true;

        // UNIVERSAL IMAGE LOADER SETUP
        defaultOptions = new DisplayImageOptions.Builder()
                .decodingOptions(resizeOptions).cacheInMemory(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();


        ImageLoaderConfiguration.Builder config = new ImageLoaderConfiguration.Builder(this);
        config.defaultDisplayImageOptions(defaultOptions);
        config.threadPriority(Thread.NORM_PRIORITY - 2);
        config.denyCacheImageMultipleSizesInMemory();
        config.memoryCache(new LruMemoryCache(50 * 1024 * 1024));
        config.diskCacheFileNameGenerator(new Md5FileNameGenerator());
        config.diskCacheSize(50 * 1024 * 1024); // 50 MiB
        config.tasksProcessingOrder(QueueProcessingType.LIFO);
        config.memoryCacheSize(50 * 1024 * 1024);

        imageLoader.init(config.build());
        // END - UNIVERSAL IMAGE LOADER SETUP

    }


    public ImageLoader getImageLoader () {
        return this.imageLoader;
    }

    public DisplayImageOptions getDefaultOptions () {
        return this.defaultOptions;
    }
}
