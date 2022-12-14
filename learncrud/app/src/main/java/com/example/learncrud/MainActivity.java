package com.example.learncrud;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks;
    Button btnAddData, btnViewAll, btnUpdate, btnDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editName = findViewById(R.id.editName);
        editSurname = findViewById(R.id.editSurname);
        editMarks = findViewById(R.id.editMarks);

        btnAddData = findViewById(R.id.btnAdd);
        btnViewAll = findViewById(R.id.btnView);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnDelete);
        addData();
        updateData();
        deleteData();
        viewAll();
    }

    public void addData() {
        btnAddData.setOnClickListener(view -> {
            boolean isInserted = myDb.insertData(
                    editName.getText().toString(),
                    editSurname.getText().toString(),
                    editMarks.getText().toString()
            );
            if (isInserted) {
                Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void viewAll() {
        btnViewAll.setOnClickListener(view -> {
            Cursor res = myDb.getAllData();
            if (res.getCount() == 0) {
                showMessage("Error", "Nothing found");
                return;
            }
            StringBuffer buffer = new StringBuffer();
            while (res.moveToNext()) {
                buffer.append("ID: ").append(res.getString(0)).append("\n");
                buffer.append("NAME: ").append(res.getString(1)).append("\n");
                buffer.append("SURNAME: ").append(res.getString(2)).append("\n");
                buffer.append("MARKS: ").append(res.getString(3)).append("\n\n");
            }
            showMessage("Data", buffer.toString());
        });
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }

    public void updateData() {
        btnUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(intent);
        });
    }

    public void deleteData() {
        btnDelete.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, UpdateActivity.class);
            startActivity(intent);
        });
    }
}