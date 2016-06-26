package pro.gs.com.quizgs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TopActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);

        TextView startView = (TextView) findViewById(R.id.start);

        if (startView != null) {
            startView.setOnClickListener(
                    new View.OnClickListener() {
                        public void onClick(View v) {
                            //ここに処理を書く
                            Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                            startActivity(intent);

                        }
                    }
            );
        }

    }
}
