package pro.gs.com.cameragalleryimage;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import static android.util.Log.v;

public class MainActivity extends AppCompatActivity {

    private Resources resource;


    private ImageView my_iconView;
    private MyUtil myutil;


    private Bitmap bm_icon;
    private ImageUtil imageUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resource = getResources();

        myutil = new MyUtil();
        imageUtil = new ImageUtil(this);

        my_iconView  =  (ImageView)findViewById(R.id.edit_icon);
        if (my_iconView != null) {
            my_iconView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    commonImageTrans();
                }
            });
        }






    }




    protected void commonImageTrans() {

        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        CharSequence[] addrdata;
        addrdata = new CharSequence[2];

        addrdata[0] = resource.getString(R.string.library);
        addrdata[1] = resource.getString(R.string.camera);

//        String txt = (String) addrdata[2];


        // タイトルを設定
//        	alertDialogBuilder.setTitle("画像を変更します");
        //alertDialogBuilder.setMessage("ここにメッセージ");
        // 表示項目とリスナの設定
        alertDialogBuilder.setItems(addrdata,
                new android.content.DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface arg0, int position) {
                        // TODO 自動生成されたメソッド・スタブ

                        // * 画像ライブラリボタンのClick押下時
                        // * 標準カメラアプリを起動する
                        if (position == 0) {
                            libraryApp_Click();
                        } else if (position == 1) {
                            cameraApp_Click();
                        }
                    }
                });

        // ダイアログを表示
        alertDialogBuilder.create().show();

    }



    /*
    * 画像ライブラリボタンのClick押下時
    */
    protected void libraryApp_Click(){

        // インテント設定
        Intent intent = new Intent(Intent.ACTION_PICK);
        // とりあえずストレージ内の全イメージ画像を対象
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        // ギャラリー表示
        startActivityForResult(intent, Constants.REQUEST_PICK_LIB);


    }

    /*
     * カメラボタンのClick押下時
     */
    protected void cameraApp_Click(){

        Uri photoUri = imageUtil.getPhotoUri();
        imageUtil.setPhotoUri(photoUri);
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
        startActivityForResult(intent, Constants.REQUEST_PICK_CAM);

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {

            //ライブラリから取得した写真を反映させる
            if (requestCode == Constants.REQUEST_PICK_LIB) {


                if(data != null && data.getData() != null){

                    int image_size_normal = myutil.changePxtoPd(this, Constants.IMAGE_SIZE_NORMAL);
                    imageUtil.setPhotoUri(data.getData());
                    bm_icon = imageUtil.loadBitmap(image_size_normal,image_size_normal);
                    if (imageUtil.getOrientationGallery(data.getData()) == 6 ) {
                        bm_icon = myutil.rotateImg(bm_icon, 90);
                    }
                    my_iconView.setImageBitmap(bm_icon);
                }


            } else if (requestCode == Constants.REQUEST_PICK_CAM) {
            //カメラで撮影した画像を反映させる

                int image_size_normal = myutil.changePxtoPd(this, Constants.IMAGE_SIZE_NORMAL);
                bm_icon= imageUtil.loadBitmap(image_size_normal,image_size_normal);
                if (imageUtil.getOrientationCamera(imageUtil.getPath()) == 6 ) {
                    bm_icon = myutil.rotateImg(bm_icon, 90);
                }
                my_iconView.setImageBitmap(bm_icon);
            }

        }
    }
}
