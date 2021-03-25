package com.example.lotterydbtwo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditSingleRecordActivity extends AppCompatActivity {

    EditText etName, etLocation;
    Button btUpdate;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String idHolder;
    String name, location;
    Boolean etEmptyHold;
    String SQLiteDataBaseQueryHolder;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_record);
        etName = findViewById(R.id.etName);
        etLocation = findViewById(R.id.etLocation);
        btUpdate = findViewById(R.id.btUpdate);
        sqLiteHelper = new SQLiteHelper(this);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = etName.getText().toString();
                location = etLocation.getText().toString();
                openSQLiteDataBase();
                checkEditTextStatus();
                if (etEmptyHold == true) {
                    SQLiteDataBaseQueryHolder = "UPDATE " + SQLiteHelper.TABLE_NAME + " SET " +
                            SQLiteHelper.Table_Column_1_Name + " = '" + name + "' , " + SQLiteHelper.Table_Column_2_Location + " = '" + location + "' WHERE id = " + idHolder + "";
                    sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
                    sqLiteDatabase.close();
                    Toast.makeText(EditSingleRecordActivity.this, "Data updated successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(EditSingleRecordActivity.this, "Please fill all the required fields", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    public void checkEditTextStatus() {
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(location)) {
            etEmptyHold = false;
        } else {
            etEmptyHold = true;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showRecordInEditText();
    }

    public void showRecordInEditText() {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        idHolder = getIntent().getStringExtra("EditID");
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + idHolder + "", null);
        if (cursor.moveToFirst()) {
            do {
                etName.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                etLocation.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Location)));
            }
            while (cursor.moveToNext());
            cursor.close();
        }
    }

    public void openSQLiteDataBase() {
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }
}