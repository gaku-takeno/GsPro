package pro.gs.com.lessonappgspro;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MySelf mySelf = new MySelf(this);
//        Human human1 = new Human();

        //アップキャスト
        Human human = mySelf;

        //ダウンキャスト
//        mySelf = (MySelf) human1;


        Object obj = new Object();
        human = (Human)obj;


        trans();



    }

    private void trans () {


        TextView btnView = (TextView) findViewById(R.id.btn);

        View view = findViewById(R.id.btn);

//        Object obj= view;



        if (btnView != null) {
            // クリック時の処理
            final Context finalContext = this;
            btnView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //ここに処理を書く
                    // 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
//                Toast.makeText(getApplication(), "ボタンが押されました", Toast.LENGTH_LONG).show();

                    moveActivty();



                }
            });
        }

        //以下のような書き方も出来る。
        // View.OnClickListener myClickListner = new View.OnClickListener() {
        //            public void onClick(View v) {
        //                //ここに処理を書く
        //            }
        //        };
        //        //
        // クリック時の処理
        //        greetingView.setOnClickListener(myClickListner);


    }


    private void moveActivty () {

        //http://individualmemo.blog104.fc2.com/blog-entry-41.html
        //アプリ全体の状態を持っていて、何から起動されたかどういう状態か、何にアクセスしようとしているか、といった情報を受け渡す
        Context context = getApplicationContext();

//        サブクラスからスーパークラスへの型変換は暗黙的に行われる
        context = this;


//        サブクラスからスーパークラスへとアップキャスト。暗黙的にキャスト
        Activity act = this;


        act = (Activity) context;

        MainActivity mainActivity = this;//キャストなし thisは元々 MainActivity

        mainActivity = (MainActivity) getApplicationContext();//ContextからMainActivityへダウンキャスト

//        final  MainActivity act = this;

        Context con = this;
        Intent intent = new Intent(getApplicationContext(), SubActivity.class);
        startActivity(intent);

    }


}
