package com.example.todomvvm.tasks;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomvvm.R;
import com.example.todomvvm.database.UserEntry;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {

    @NonNull
    private List<UserEntry> useer;
    final private ItemClickListener mItemClickListener;
    private Context mContext;
    //final private ItemClickListener clickListener;
    public UserAdapter(Context context, ItemClickListener listener) {
        mContext = context;
        mItemClickListener = listener;
    }
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflate the task_layout to a view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.user_layout, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserEntry taskEntry = useer.get(position);
        String username = taskEntry.getUsername();
        Log.d("yes","hello"+username);
        String address = taskEntry.getAddress();
        String email = taskEntry.getEmail();
        String phone = taskEntry.getPhone();
        int gender = taskEntry.getGender();

        //Set values
        holder.username.setText(username);
        holder.address.setText(address);
        holder.email.setText(email);
        holder.gender.setText(getgender(gender));
        holder.Phone.setText(phone);


    }

    public String getgender(int gender){
        String genders="";
        switch (gender){
            case 1:
                genders = "male";
                break;
            case 2:
                genders = "female";
                break;
            default:
                break;
        }
        return genders;
    }
    @Override
    public int getItemCount() {
        if (useer==null){
            return 0;
        }
        else{
            return useer.size();
        }

    }

    public interface ItemClickListener {
        void onItemClickListener(int itemId);
    }
    class UserViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView username;
        TextView address;
        TextView email,Phone,gender;
        public UserViewHolder(@NonNull View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.username);
            address = itemView.findViewById(R.id.address);
            email = itemView.findViewById(R.id.email);
            Phone = itemView.findViewById(R.id.phone);
            gender = itemView.findViewById(R.id.gender);
            itemView.setOnClickListener(this);
        }
        public void onClick(View view) {
            int elementId = useer.get(getAdapterPosition()).getId();
            mItemClickListener.onItemClickListener(elementId);
        }
    }
    public List<UserEntry> getUseer(){
        return useer;
    }
    public void setUseer(List<UserEntry> userEntries) {
        useer = userEntries;
        notifyDataSetChanged(); //list lae reflect garxa
    }






}
