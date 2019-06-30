package activity;

import android.database.DataSetObserver;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;


import com.addstudentdetailsproject.R;

import java.util.List;

import database.Databasehelper;
import model.StudentModel;

public class ShowAllStudent extends AppCompatActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.showall_student_details);
        recyclerView = findViewById(R.id.recycler_view);

        Databasehelper databasehelper = new Databasehelper(this);

        List<StudentModel> studentModels = databasehelper.getAllstudentModel();
        System.out.println("Number of students ="+studentModels.size());
        Adapter adapter = new Adapter(this, studentModels);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }
}
