package pro.gs.com.lessonappgspro;

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


        trans();



    }

    private void trans () {


        TextView btnView = (TextView) findViewById(R.id.btn);

        View view = findViewById(R.id.btn);

        Object obj= view;

//        Context context = getApplicationContext();

//        サブクラスからスーパークラスへの型変換は暗黙的に行われる
//        context = this;


//        スーパークラスからサブクラスへと型変換をするには明示的キャストが必要になる。
//        Activity act = (Activity) context;


        if (btnView != null) {
            // クリック時の処理
            final Context finalContext = this;
            btnView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //ここに処理を書く
                    // 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
//                Toast.makeText(getApplication(), "ボタンが押されました", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(getApplicationContext(), SubActivity.class);
                    startActivity(intent);

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


}
