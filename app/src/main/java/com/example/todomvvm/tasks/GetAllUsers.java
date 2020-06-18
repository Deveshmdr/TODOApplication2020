package com.example.todomvvm.tasks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.todomvvm.R;
import com.example.todomvvm.addedittask.AddEditUserActivity;
import com.example.todomvvm.database.AppDatabase;
import com.example.todomvvm.database.UserEntry;

import java.util.List;

public class GetAllUsers extends AppCompatActivity implements UserAdapter.ItemClickListener {
    private UserAdapter uadapter ;
    AppDatabase database;
    LinearLayout container;
    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_all_users);
        database = AppDatabase.getInstance(getApplicationContext());
        //recycleview adapter to view users data

        mRecyclerView = findViewById(R.id.recyclerViewUsers);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        uadapter = new UserAdapter(this,this);
        mRecyclerView.setAdapter(uadapter);
        Resume();
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            // Called when a user swipes left or right on a ViewHolder
            @Override
            public void onSwiped(final RecyclerView.ViewHolder viewHolder, int swipeDir) {
                // Here is where you'll implement swipe to delete

                AppDatabase.databaseWriteExecutor.execute(new Runnable() {
                    @Override
                    public void run() {
                        int position = viewHolder.getAdapterPosition();
                        UserEntry user = uadapter.getUseer().get(position);
                        database.userDao().deleteUser(user);
                        retrieveuser();
                    }
                });
            }
        }).attachToRecyclerView(mRecyclerView);
    }
    protected void Resume() {
        //super.onResume();
        retrieveuser();
    }

    private void retrieveuser() {
        AppDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                final List<UserEntry> useer = database.userDao().loadAllUsers();
                Log.d("ok",""+useer.size());
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        uadapter.setUseer(useer);
                    }
                });
            }
        });
    }

    //for to update
    @Override
    public void onItemClickListener(int itemId) {
        Intent intent = new Intent(GetAllUsers.this, AddEditUserActivity.class);
        intent.putExtra(AddEditUserActivity.EXTRA_USER_ID,itemId);
        startActivity(intent);
    }
}
