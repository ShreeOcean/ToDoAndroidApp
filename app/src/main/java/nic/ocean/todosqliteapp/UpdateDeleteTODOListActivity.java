package nic.ocean.todosqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import nic.ocean.todosqliteapp.databinding.ActivityUpdateDeleteTodolistBinding;

public class UpdateDeleteTODOListActivity extends AppCompatActivity {

    ActivityUpdateDeleteTodolistBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUpdateDeleteTodolistBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIncomingIntend();
    }

    private void getIncomingIntend(){
        Log.d("TAG---> ", "getIncomingIntend: Check for the incoming intent");

        String todo_title = getIntent().getStringExtra("todo_tittle");
        String todo_body = getIntent().getStringExtra("todo_body");
        String todo_date_time = getIntent().getStringExtra("todo_date_time");
        String todo_item_row_id = getIntent().getStringExtra("todo_item_row_id");

        setData(todo_body,todo_title,todo_date_time,todo_item_row_id);
    }

    private void setData(String todo_body, String todo_title, String todo_date_time, String todo_item_row_id) {

        binding.etTodoMsgUpDel.setText(todo_body);
        binding.etTodoTitlleUpDel.setText(todo_title);
        binding.tvTodoDateTimeUpDel.setText(todo_date_time);
    }
}