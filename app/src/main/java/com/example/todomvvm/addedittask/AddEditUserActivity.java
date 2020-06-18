package com.example.todomvvm.addedittask;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.todomvvm.R;
import com.example.todomvvm.database.AppDatabase;
import com.example.todomvvm.database.TaskEntry;
import com.example.todomvvm.database.UserEntry;

public class AddEditUserActivity  extends AppCompatActivity {

    public static final String EXTRA_USER_ID = "extraUserId";
    public static final String INSTANCE_USER_ID = "instanceUserId";
    EditText mEditText;
    RadioGroup mRadioGroup;
    Button mButton;
    EditText Username, Email, Password, Address,PhoneNumber;
    RadioGroup Gender;
    public static final int male = 1;
    public static final int female = 2;
    Button Register,Cancel;
    private static final int DEFAULT_USER_ID = -1;
    private int mUserId = DEFAULT_USER_ID;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initViews();

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_USER_ID)) {
            mUserId = savedInstanceState.getInt(INSTANCE_USER_ID, DEFAULT_USER_ID);
        }

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra(EXTRA_USER_ID)){
            Register.setText(R.string.update_button);
        }
        if (mUserId == DEFAULT_USER_ID) {
            // populate the UI
            mUserId = intent.getIntExtra(EXTRA_USER_ID,DEFAULT_USER_ID);
            AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    final UserEntry entry = AppDatabase.getInstance(getApplicationContext()).userDao().loaduserbyid(mUserId);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            populateUI(entry);
                        }
                    });
                }
            });
        }
    }
    private void initViews() {
        Username = findViewById(R.id.Username);
        Password = findViewById(R.id.Password);
        Email = findViewById(R.id.Email);
        Address = findViewById(R.id.Address);
        PhoneNumber=findViewById(R.id.Phone);
        //Gender = findViewById(R.id.Gender);
        Gender = findViewById(R.id.Gender);
        Register = findViewById(R.id.register);
        Cancel = findViewById(R.id.Cancel);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onRegisterButtonClicked();
            }
        });
    }
    public void onRegisterButtonClicked() {
        // Not yet implemented
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String email = Email.getText().toString();
        String address = Address.getText().toString();
        String phone = PhoneNumber.getText().toString();
        int gender = getgenderFromViews();
        final UserEntry todouser = new UserEntry(username, password, email,address,phone,gender);
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                if (mUserId == DEFAULT_USER_ID) {
                    AppDatabase.getInstance(getApplicationContext()).userDao().insertUser(todouser);
                }
                else {
                    todouser.setId(mUserId);
                    AppDatabase.getInstance(getApplicationContext()).userDao().update(todouser);
                }
            }
        });
        Toast toast = Toast.makeText(getApplicationContext(),
                "List Updated",
                Toast.LENGTH_SHORT);
        toast.show();
        finish();
    }
    public int getgenderFromViews() {
        int gender = 1;
        int checkedId = ((RadioGroup) findViewById(R.id.Gender)).getCheckedRadioButtonId();
        switch (checkedId) {
            case R.id.Male:
                gender = male;
                break;
            case R.id.Female:
                gender = female;
        }
        return gender;
    }
    public void setgenderFromViews(int gender) {
        switch (gender) {
            case male:
                ((RadioGroup) findViewById(R.id.Gender)).check(R.id.Male);
                break;
            case female:
                ((RadioGroup) findViewById(R.id.Gender)).check(R.id.Female);

        }
    }

    //for to retreive data in the form while updating
    private void populateUI(UserEntry user) {
        if(user == null){
            return;
        }else {
            Username.setText(user.getUsername());
            Password.setText(user.getPassword());
            Email.setText(user.getEmail());
            Address.setText(user.getAddress());
            PhoneNumber.setText(user.getPhone());
            setgenderFromViews(user.getGender());
        }
    }

}
