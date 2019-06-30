package activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.addstudentdetailsproject.R;

import database.Databasehelper;
import model.StudentModel;


public class RegistrationPageActivity extends AppCompatActivity {

    private EditText edt1_name, edt2_mobno, edt3_clg, edt4_add;
    private Button btn_submit1;

    Databasehelper db;
    private String name;
    private String mobno;
    private String clg;
    private String add;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regestration_page_activity);

        edt1_name = findViewById(R.id.edt1_name);
        edt2_mobno = findViewById(R.id.edt2_mobno);
        edt3_clg = findViewById(R.id.edt3_clg);
        edt4_add = findViewById(R.id.edt4_add);

        db = new Databasehelper(this);

        btn_submit1 = findViewById(R.id.btn_submit1);

        btn_submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name = edt1_name.getText().toString();
                mobno = edt2_mobno.getText().toString();
                clg = edt3_clg.getText().toString();
                add = edt4_add.getText().toString();

                boolean isValid = validateInputs();

                if (isValid) {
                    saveToDatabase();
                }
            }
        });
    }

    private void saveToDatabase() {
        StudentModel studentModel = new StudentModel();
        studentModel.setStdname(name);
        studentModel.setStdphno(mobno);
        studentModel.setStdclg(clg);
        studentModel.setStdadd(add);

        boolean saved = db.inseartstd(studentModel);
        if (saved) {
            Toast.makeText(RegistrationPageActivity.this, " Data sucessfullly Saved", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(RegistrationPageActivity.this, AddorShowAllActivity.class);
            startActivity(intent);finish();

        } else {
            Toast.makeText(RegistrationPageActivity.this, "invalid", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validateInputs() {
        boolean isValid = true;
        if (TextUtils.isEmpty(name)) {
            Toast.makeText(RegistrationPageActivity.this, "enter your name", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (TextUtils.isEmpty(mobno)) {
            Toast.makeText(RegistrationPageActivity.this, "enter your mobno", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (TextUtils.isEmpty(clg)) {
            Toast.makeText(RegistrationPageActivity.this, "enter your clg name", Toast.LENGTH_SHORT).show();
            isValid = false;
        }

        if (TextUtils.isEmpty(add)) {
            Toast.makeText(RegistrationPageActivity.this, "enter your address", Toast.LENGTH_SHORT).show();
            isValid = false;
        }
        return isValid;
    }
}
