package com.example.todomvvm.tasks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.todomvvm.R;
import com.example.todomvvm.addedittask.AddEditUserActivity;
import com.example.todomvvm.database.AppDatabase;

public class Register extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.home_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        switch (id){

            case R.id.menu2:
                Intent adduserintent = new Intent(Register.this, AddEditUserActivity.class);
                startActivity(adduserintent);
                break;
            case R.id.menu3:
                Intent useradapter = new Intent(Register.this, GetAllUsers.class);
                startActivity(useradapter);
                break;
            //startActivity(new Intent(MainActivity.this,get_all_Users.class));
            //break;
            case R.id.menu4:
                Toast.makeText(Register.this," Menu 4",Toast.LENGTH_SHORT).show();
                break;

            case R.id.menu6:
                Toast.makeText(Register.this," Menu 6",Toast.LENGTH_SHORT).show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
