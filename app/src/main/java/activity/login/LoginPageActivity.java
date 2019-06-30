package activity.login;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.addstudentdetailsproject.R;

import activity.AddorShowAllActivity;
import database.DatabaseHandler;

public class LoginPageActivity extends AppCompatActivity {


    EditText user, pass;
   private Button login, not_reg;
    DatabaseHandler db;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login_page);

        setContentView(R.layout.activity_main_login_page);
        user =(EditText)findViewById(R.id.eduser);
        pass = (EditText)findViewById(R.id.edpass);
        login =(Button)findViewById(R.id.login);
        not_reg =(Button)findViewById(R.id.not_reg);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                db=new DatabaseHandler(LoginPageActivity.this);
                String username=user.getText().toString();
                String password= pass.getText().toString();

                String StoredPassword = (String) db.getregister(username);
                if(password.equals(StoredPassword)){

                    Toast.makeText(getApplicationContext(), StoredPassword+"Login Successfully", Toast.LENGTH_LONG).show();

                    Intent intent = new Intent(LoginPageActivity.this, AddorShowAllActivity.class);
                    startActivity(intent);

                }
                else{
                    Toast.makeText(getApplicationContext(), "Username/Password incorrect", Toast.LENGTH_LONG).show();
                    user.setText("");
                    pass.setText("");
                }


            }

        });

        not_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(LoginPageActivity.this, LoginRegisterActivity.class);
                startActivity(intent);
                // startActivity(new Intent(getApplicationContext(), LoginRegisterActivity.class));
            }

        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
            case R.id.action_settings:
                // search action
                Intent i=new Intent(LoginPageActivity.this,LoginRegisterActivity.class);
                startActivity(i);
                return true;
        }
        return false;

    }
}
