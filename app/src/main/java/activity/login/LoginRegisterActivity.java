package activity.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.addstudentdetailsproject.R;

import database.DatabaseHandler;
import model.RegisterData;


public class LoginRegisterActivity extends AppCompatActivity {

    EditText first, last, email, mobile, pass, confpass;
    Button save, cancel;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_register);


        first= (EditText)findViewById(R.id.editfirstname);
        last =(EditText)findViewById(R.id.editlastname);
        email=(EditText)findViewById(R.id.editemail);
        mobile =(EditText)findViewById(R.id.editmobileno);
        pass=(EditText)findViewById(R.id.editpassword);
        confpass=(EditText)findViewById(R.id.editconformpassword);

        save=(Button)findViewById(R.id.btnsave);
        cancel=(Button)findViewById(R.id.btncancel);

        Save();
    }

    public void Save(){

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String edfirst = first.getText().toString();
                String edlast = last.getText().toString();
                String edemail = email.getText().toString();
                String edmobile = mobile.getText().toString();
                String edpass = pass.getText().toString();
                String edConf = confpass.getText().toString();

                if(edConf.equals(edpass) ){

                    db = new DatabaseHandler(LoginRegisterActivity.this);
                    RegisterData reg = new RegisterData();

                    reg.setFirst_name(edfirst);
                    reg.setLast_name(edlast);
                    reg.setEmail_id(edemail);
                    reg.setMobile_number(edmobile);
                    reg.setPassword(edpass);
                    db.addregister(reg);

                    Toast.makeText(getApplicationContext(), "Registered", Toast.LENGTH_LONG).show();
                    //startActivity(new Intent(getApplicationContext(),LoginPageActivity.class));
                    Intent intent =new Intent(LoginRegisterActivity.this, LoginPageActivity.class);
                    startActivity(intent);
                }
                else{

                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_LONG).show();
                    pass.setText("");
                    confpass.setText("");
                }
            }

        });
    }
}
