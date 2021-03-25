package com.example.lotterydbtwo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class DisplaySQLiteDataActivity extends AppCompatActivity {
    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter;
    ListView listView;
    ArrayList<String> idArray;
    ArrayList<String> nameArray;
    ArrayList<String> locationArray;

    ArrayList<String> listViewClickItemArray = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_data);
        listView = findViewById(R.id.lvRecords);
        idArray = new ArrayList<>();
        nameArray = new ArrayList<>();
        locationArray = new ArrayList<>();
        sqLiteHelper = new SQLiteHelper(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(getApplicationContext(), ShowSingleRecordActivity.class);
                intent.putExtra("ListViewClickedItemValue", listViewClickItemArray.get(position));
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        showSQLiteDBData();
    }

    private void showSQLiteDBData() {
        sqLiteDatabase = sqLiteHelper.getWritableDatabase();
        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + "", null);
        idArray.clear();
        nameArray.clear();
        locationArray.clear();
        if (cursor.moveToFirst()) {
            do {
                idArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                // Inserting Column tvID into Array to Use at ListView Click Listener Method
                listViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
                nameArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_Name)));
                locationArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_Location)));
            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(DisplaySQLiteDataActivity.this,
                idArray,
                nameArray,
                locationArray
        );

        listView.setAdapter(listAdapter);
        cursor.close();
    }
}
