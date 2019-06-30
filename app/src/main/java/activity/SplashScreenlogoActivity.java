package activity;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.addstudentdetailsproject.R;

import activity.login.LoginPageActivity;


public class SplashScreenlogoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_logo_activity);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent i = new Intent(SplashScreenlogoActivity.this,LoginPageActivity.class);
                startActivity(i);

                finish();
            }
        },5000);

    }
}
