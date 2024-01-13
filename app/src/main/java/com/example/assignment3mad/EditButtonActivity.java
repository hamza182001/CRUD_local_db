package com.example.assignment3mad;

import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.assignment3mad.R;
import com.example.assignment3mad.RecordsDB;
import com.google.android.material.textfield.TextInputEditText;

public class EditButtonActivity extends AppCompatActivity {
    TextInputEditText etRecordID, etNewTitle, etNewDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_button);

        etRecordID = findViewById(R.id.etRecordId);
        etNewTitle = findViewById(R.id.etNewTitle);
        etNewDesc = findViewById(R.id.etNewDesc);
    }

    public void btnEdit(View view) {
        try {
            String recordId = etRecordID.getText().toString().trim();
            String newUsername = etNewTitle.getText().toString().trim();
            String newPassword = etNewDesc.getText().toString().trim();

            if (recordId.isEmpty() || newUsername.isEmpty() || newPassword.isEmpty()) {
                Toast.makeText(this, "Please fill in all the fields", Toast.LENGTH_SHORT).show();
                return;
            }

            RecordsDB db = new RecordsDB(this);
            db.open();
            int result = db.editRecord(recordId, newUsername, newPassword);
            db.close();

            if (result > 0) {
                Toast.makeText(this, "Record edited successfully", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to edit record", Toast.LENGTH_SHORT).show();
            }

            finish();
        } catch (SQLException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
