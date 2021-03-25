package com.example.lotterydbtwo;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ShowSingleRecordActivity extends AppCompatActivity {

    String idHolder;
    TextView tvID, tvName, tvLocation;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    Button btDelete, btEdit;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);
        tvID = findViewById(R.id.tvID);
        tvName = findViewById(R.id.tvName);
        tvLocation = findViewById(R.id.tvLocation);
        btDelete = findViewById(R.id.btDelete);
        btEdit = findViewById(R.id.btEdit);
        sqLiteHelper = new SQLiteHelper(this);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSQLiteDataBase();
                SQLiteDataBaseQueryHolder = "DELETE FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + idHolder + "";
                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);
                sqLiteDatabase.close();
                Toast.makeText(ShowSingleRecordActivity.this, "Data deleted successfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        btEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), EditSingleRecordActivity.class);
                intent.putExtra("EditID", idHolder);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showSingleRecordInTextView();
    }

    public void showSingleRecordInTextView() {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        idHolder = getIntent().getStringExtra("ListViewClickedItemValue");
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + idHolder + "", null);
        if (cursor.moveToFirst()) {
            do {
                tvID.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                tvName.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                tvLocation.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Location)));
            }
            while (cursor.moveToNext());
            cursor.close();
        }
    }

    public void openSQLiteDataBase() {
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);
    }
}