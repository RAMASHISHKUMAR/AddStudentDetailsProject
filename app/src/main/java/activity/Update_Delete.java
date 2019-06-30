package activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
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

public class Update_Delete extends AppCompatActivity {

    private EditText updateEdtText_name,updateEdtText_mobno,updateEdtText_clg,updateEdtText_add;
    private Button btn1Update, btn2Delete;

    Databasehelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update__delete_activity);

        updateEdtText_name = findViewById(R.id.updateEdtText_name);
        updateEdtText_mobno = findViewById(R.id.updateEdtText_mobno);
        updateEdtText_clg = findViewById(R.id.updateEdtText_clg);
        updateEdtText_add = findViewById(R.id.updateEdtText_add);

        btn1Update = findViewById(R.id.btn1Update);
        btn2Delete = findViewById(R.id.btn2Delete);

        db = new Databasehelper(this);

        /* Showing data on update_delete_form */

        Bundle bundle = getIntent().getExtras();

        String  name = bundle.getString("Name");
        String  mobileno = bundle.getString("MobileNo");
        String  collegename = bundle.getString("Clgname");
        String  stdaddress = bundle.getString("StudentAddress");


        updateEdtText_name.setText(name);
        updateEdtText_mobno.setText(mobileno);
        updateEdtText_clg.setText(collegename);
        updateEdtText_add.setText(stdaddress);

        /* Update button operation */

        btn1Update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String  sudentnames = updateEdtText_name.getText().toString();
                String  studentphoneno = updateEdtText_mobno.getText().toString();
                String  studentclgname = updateEdtText_clg.getText().toString();
                String  studentaddress = updateEdtText_add.getText().toString();

                if (TextUtils.isEmpty(sudentnames)){
                    Toast.makeText(Update_Delete.this, "Enter Student Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(studentphoneno)){
                    Toast.makeText(Update_Delete.this, "Enter Student Phone no", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(studentclgname)){
                    Toast.makeText(Update_Delete.this, "Enter clg Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(studentaddress)){
                    Toast.makeText(Update_Delete.this, "Enter Student Address", Toast.LENGTH_SHORT).show();
                    return;
                }

                StudentModel studentModel = new StudentModel();
                studentModel.setStdname(sudentnames);
                studentModel.setStdphno(studentphoneno);
                studentModel.setStdclg(studentclgname);
                studentModel.setStdadd(studentaddress);

                int updated = db.Update_Details(studentModel);

                if (updated>0){
                    Toast.makeText(Update_Delete.this, "Data Updated ", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(Update_Delete.this, ShowAllStudent.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(Update_Delete.this, "Not Updated", Toast.LENGTH_SHORT).show();
                }

                finish();
            }

        });

        /* Delete operation Perform */

        btn2Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                alertMessage();

            }
        });
    }

    public void alertMessage(){
        DialogInterface.OnClickListener dialogClickListner = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                StudentModel studentModel = new StudentModel();
                studentModel.setStdphno(updateEdtText_mobno.getText().toString());

                int delete = db.delete_Details(studentModel);

                dialog.dismiss();
                switch(which){
                    case DialogInterface.BUTTON_POSITIVE:
                        // Yes button clicked
                        Toast.makeText(Update_Delete.this, "Yes Clicked",
                                Toast.LENGTH_LONG).show();
                        if (delete > 0){

                            Toast.makeText(Update_Delete.this, "Data Deleted Successfully", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(Update_Delete.this,ShowAllStudent.class);
                            startActivity(intent);
                        }
                        else{
                            Toast.makeText(Update_Delete.this, "Not Deleted", Toast.LENGTH_SHORT).show();
                        }

                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        // No button clicked
                        // do nothing
                        dialog.dismiss();
                        Toast.makeText(Update_Delete.this, "No Clicked",
                                Toast.LENGTH_LONG).show();
                        break;

                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete?")
                .setPositiveButton("Yes", dialogClickListner)
                .setNegativeButton("No", dialogClickListner).show();
    }

}
