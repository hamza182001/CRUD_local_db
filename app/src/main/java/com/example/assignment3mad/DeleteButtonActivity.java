package com.example.assignment3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class DeleteButtonActivity extends AppCompatActivity {
    TextInputEditText etId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_button);

    }
    public void btnDelete(View view) {
        try {
            etId=findViewById(R.id.etId);
            String recordId = etId.getText().toString().trim();

            if (recordId.isEmpty()) {
                Toast.makeText(this, "Please enter the record ID", Toast.LENGTH_SHORT).show();
                return;
            }
            RecordsDB db = new RecordsDB(this);
            db.open();
            int result = db.deleteRecord(recordId);
            db.close();

            if (result > 0) {
                Toast.makeText(this, "Record deleted successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to delete record", Toast.LENGTH_SHORT).show();
            }
            finish();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}