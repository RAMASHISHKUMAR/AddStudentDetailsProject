package database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;


import java.util.ArrayList;
import java.util.List;

import model.StudentModel;


public class Databasehelper extends SQLiteOpenHelper {
    public Databasehelper(Context context) {
        super(context, "Studentdata", null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table student(stdname text, stdphno INTEGER primary key, stdclg text, stdadd text )");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

      /*  db.execSQL("drop table if exists student");
        onCreate(db); */
    }

    public boolean inseartstd(StudentModel studentModel) {
        try {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("stdname", studentModel.getStdname());
            contentValues.put("stdphno", studentModel.getStdphno());
            contentValues.put("stdclg", studentModel.getStdclg());
            contentValues.put("stdadd", studentModel.getStdadd());

            long id = db.insert("student", null, contentValues);
            if (id == -1) {
                return false;
            } else {
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }

    }

    // showingdata

    public List<StudentModel> getAllstudentModel() {
        List<StudentModel> allStudentModels = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("select * from student", null);

        if (cursor.moveToFirst()) {
            do {
                StudentModel studentModel = new StudentModel();
                studentModel.setStdname(cursor.getString(cursor.getColumnIndex("stdname")));
                studentModel.setStdphno(cursor.getString(cursor.getColumnIndex("stdphno")));
                studentModel.setStdclg(cursor.getString(cursor.getColumnIndex("stdclg")));
                studentModel.setStdadd(cursor.getString(cursor.getColumnIndex("stdadd")));

                allStudentModels.add(studentModel);

            } while (cursor.moveToNext());
        }
        db.close();
        return allStudentModels;
    }

    public int Update_Details(StudentModel studentModel){
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("stdname",studentModel.getStdname());
        //  contentValues.put("stdphno",studentModel.getStdphno());
        contentValues.put("stdclg",studentModel.getStdclg());
        contentValues.put("stdadd",studentModel.getStdadd());

        int updated = db.update("student",contentValues,"stdphno=?",new String[]{String.valueOf(studentModel.getStdphno())});
        return updated;
    }

    public int delete_Details(StudentModel studentModel){
        SQLiteDatabase db = getWritableDatabase();
        int delete = db.delete("student","stdphno=?",new String[]{String.valueOf(studentModel.getStdphno())});
        db.close();
        return delete;
    }
}