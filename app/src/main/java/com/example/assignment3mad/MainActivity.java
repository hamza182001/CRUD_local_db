package com.example.assignment3mad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class MainActivity extends AppCompatActivity {
    TextInputEditText etUsername,etPassword,etNewTitle,etNewDesc,etRecordID,etId;
    Button btnAdd,btnShow,btnEdit,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();




    }
    private void init(){
        etUsername=findViewById(R.id.etUsername);
        etPassword=findViewById(R.id.etPassword);
        btnAdd=findViewById(R.id.btnAddNote);
        btnEdit=findViewById(R.id.btnEditNote);
        btnDelete=findViewById(R.id.btnDeleteNote);
        btnShow=findViewById(R.id.btnShowNotes);

    }
    public void btnAdd(View view)
    {
        try {
            String username = etUsername.getText().toString().trim();
            String password = etPassword.getText().toString().trim();

            RecordsDB db = new RecordsDB(this);
            db.open();
            db.addNewRecord(username, password);
            db.close();
            Toast.makeText(MainActivity.this,"record added successfully",Toast.LENGTH_SHORT).show();
            etUsername.setText("");
            etPassword.setText("");
        }
        catch (SQLException message){
            Toast.makeText(MainActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
        }





    }

    public void btnShow(View v)
    {
        Intent intent = new Intent(MainActivity.this, ViewRecords.class);
        startActivity(intent);
    }
    public void btnMoveToEditScreen(View v){
        Intent intent=new Intent(MainActivity.this, EditButtonActivity.class);
        startActivity(intent);
    }
    public void btnMoveToDeleteScreen(View v){
        Intent intent=new Intent(MainActivity.this, DeleteButtonActivity.class);
        startActivity(intent);
    }

}