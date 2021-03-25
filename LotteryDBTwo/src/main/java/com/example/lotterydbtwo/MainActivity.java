package com.example.lotterydbtwo;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


// Project Source: https://androidjson.com/sqlite-select-insert-update-delete-display/
// With minor modifications (Validation when updating a record, text field "location" instead of "phone number")

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase sqLiteDatabaseObj;
    EditText etName, etLocation;
    String nameHolder, locationHolder, SQLiteDataBaseQueryHolder;
    Button btEnterData, btDisplayData;
    Boolean etEmptyHold;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btEnterData = findViewById(R.id.btEnterData);
        btDisplayData = findViewById(R.id.btDisplayData);
        etName = findViewById(R.id.etName);
        etLocation = findViewById(R.id.etLocation);

        btEnterData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDataBaseBuild();
                SQLiteTableBuild();
                checkEditTextStatus();
                insertDataIntoSQLiteDatabase();
                emptyEditTextAfterDataInsert();
            }
        });

        btDisplayData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplaySQLiteDataActivity.class);
                startActivity(intent);
            }
        });
    }

    public void SQLiteDataBaseBuild() {
        // See: https://developer.android.com/reference/android/database/sqlite/SQLiteDatabase
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }

    public void SQLiteTableBuild() {
        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " + SQLiteHelper.TABLE_NAME + "("
                + SQLiteHelper.Table_Column_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + SQLiteHelper.Table_Column_1_Name + " TEXT,"
                + SQLiteHelper.Table_Column_2_Location + " TEXT"
                + ")");
    }

    public void checkEditTextStatus() {
        nameHolder = etName.getText().toString();
        locationHolder = etLocation.getText().toString();
        if (TextUtils.isEmpty(nameHolder) || TextUtils.isEmpty(locationHolder)) {
            etEmptyHold = false;

        } else {
            etEmptyHold = true;
        }
    }

    public void insertDataIntoSQLiteDatabase() {
        if (etEmptyHold == true) {
            SQLiteDataBaseQueryHolder = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (" + SQLiteHelper.Table_Column_1_Name + ", " +
                    SQLiteHelper.Table_Column_2_Location + ") VALUES('" + nameHolder + "', '" + locationHolder + "');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();
            Toast.makeText(MainActivity.this, "Data inserted successfully", Toast.LENGTH_LONG).show();

        } else {
            Toast.makeText(MainActivity.this, "Please fill all the required fields", Toast.LENGTH_LONG).show();
        }

    }

    public void emptyEditTextAfterDataInsert() {
        etName.getText().clear();
        etLocation.getText().clear();

    }

}