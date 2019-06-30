package activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.addstudentdetailsproject.R;


public class AddorShowAllActivity extends AppCompatActivity {

    private Button btn1addnew,btn2showall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnew_or_showall_activit);

        btn1addnew = findViewById(R.id.btn_add_1);
        btn2showall = findViewById(R.id.btn_show_2);

        btn1addnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddorShowAllActivity.this,RegistrationPageActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btn2showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AddorShowAllActivity.this, ShowAllStudent.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
