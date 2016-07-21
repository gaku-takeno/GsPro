package pro.gs.com.GsProMemberList;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class ProfileEditActivity extends AppCompatActivity {

    private EditText nameView;
    private EditText messageView;
    private RadioGroup radioGroup;
    private Button submitView;

    private String name = "";
    private String message = "";
    private int sex = 0;
    private String sns_user_id;


    private View.OnClickListener submit_ClickListener = new View.OnClickListener(){
        public void onClick(View v) {
            submit_Click(v);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);


        submitView = (Button)findViewById(R.id.submit_btn);
        nameView = (EditText) findViewById(R.id.edit_name);
        messageView = (EditText) findViewById(R.id.edit_message);
        radioGroup = (RadioGroup) findViewById(R.id.radioSex);

    }

    /**
     * submit
     */
    private void  submit_Click (View v) {

        name = nameView.getText().toString();
        message = messageView.getText().toString();


        // チェックされているラジオボタンの ID を取得します
        RadioButton checkedRadioButton = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

        boolean checked = ((RadioButton) checkedRadioButton).isChecked();

        if (checked) {
            String sexId = (String) checkedRadioButton.getText();
            if (sexId.equals("man")) {
                sex = 1;//man
            } else {
                sex = 2;//woman
            }
        }

        
    }
}
