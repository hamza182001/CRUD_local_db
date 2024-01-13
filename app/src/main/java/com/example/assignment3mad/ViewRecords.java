package com.example.assignment3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.database.SQLException;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ViewRecords extends AppCompatActivity {
    TextView tvViewPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_records);
        tvViewPassword=findViewById(R.id.ShowRecords);
        RecordsDB database=new RecordsDB(this);
        try {
            database.open();
            tvViewPassword.setText(database.getAllRecords());
            database.close();
        }
         catch (SQLException message){
            Toast.makeText(ViewRecords.this, message.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}