package pro.gs.com.gspro;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import pro.gs.com.gspro.R;

public class MainActivity extends AppCompatActivity {

    //型がTextViewであるnameViewという名前の変数を宣言している。
    //この時点では、nameViewには何も入っていない。つまりNULL
    private TextView greetingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //viewオブジェクトを取り出して、メンバー変数に格納する。
        greetingView = (TextView) findViewById(R.id.greeting);
        TextView greetingView2 = greetingView;

        greetingView.setText("wここをクリックして挨拶する!");

        greetingView.setText("クリックして自分を創ってみる!");

        //アクション（インテントに画面遷移の処理）を実装
        setActions();

    }


    private void setActions(){

        // クリック時の処理
        greetingView.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                intentTrans();
            }
        });

        //以下のような書き方も出来る。
//        View.OnClickListener myClickListner = new View.OnClickListener() {
//            public void onClick(View v) {
//                intentTrans();
//            }
//        };
//        // クリック時の処理
//        greetingView.setOnClickListener(myClickListner);


    }

    private void intentTrans () {


        EditText firstNameView = (EditText) findViewById(R.id.firstname);
        EditText familyNameView = (EditText) findViewById(R.id.familyname);

        EditText ageView = (EditText) findViewById(R.id.age);

        String firstname = firstNameView.getText().toString();
        String familyname = familyNameView.getText().toString();
        String ageTxt = ageView.getText().toString();
        int age = Integer.parseInt(ageTxt);

        //1:男 0:女
        int sex = 1;

        // ラジオグループのオブジェクトを取得
        RadioGroup rg = (RadioGroup)findViewById(R.id.radiogroup_sex);
        // チェックされているラジオボタンの ID を取得
        RadioButton radio_male = (RadioButton)findViewById(R.id.radio_male);
//        RadioButton radio_female = (RadioButton)findViewById(R.id.radio_female);
        if(radio_male.isChecked()) {
            sex = 1;
        } else {
            sex = 0;
        }

        Log.d("hello_age",age+"");
        if (!firstname.equals("") && !familyname.equals("")) {

            Intent intent = new Intent(this, SubActivity.class);
            intent.putExtra("first_name", firstname);
            intent.putExtra("family_name", familyname);
            intent.putExtra("sex",sex);
            intent.putExtra("age",age);
            startActivity(intent);

        } else {

            // 第3引数は、表示期間（LENGTH_SHORT、または、LENGTH_LONG）
            Toast.makeText(this, "名前と苗字を入れてください。", Toast.LENGTH_LONG).show();

        }


    }


}
