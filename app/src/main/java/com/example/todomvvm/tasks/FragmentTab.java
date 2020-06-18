package com.example.todomvvm.tasks;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.todomvvm.R;

public class FragmentTab extends AppCompatActivity {
    TextView tab1,tab2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_tab);
        tab1 =findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab1.setBackgroundResource(R.drawable.btn_selector);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,new FragmentOne())
                .commit();
    }
    public void tabClickListener(View view){
        tab1.setBackgroundColor(Color.TRANSPARENT);
        tab2.setBackgroundColor(Color.TRANSPARENT);
        if (view.getId()==R.id.tab1){
            tab1.setBackgroundResource(R.drawable.btn_selector);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,new FragmentOne())
                    .commit();
        }
        else {
            tab2.setBackgroundResource(R.drawable.btn_selector);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container,new FragmentTwo())
                    .commit();
        }
    }
}
