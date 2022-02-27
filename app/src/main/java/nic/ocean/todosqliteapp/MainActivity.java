package nic.ocean.todosqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nic.ocean.todosqliteapp.adaptor.CustomListAdapterRecyclerView;
import nic.ocean.todosqliteapp.databinding.ActivityMainBinding;
import nic.ocean.todosqliteapp.databinding.CustomDialogCreateTodolistBinding;
import nic.ocean.todosqliteapp.db.DatabaseSQLite;
import nic.ocean.todosqliteapp.interfaces.OnItemClickListener;
import nic.ocean.todosqliteapp.model.TodoListModel;
import nic.ocean.todosqliteapp.utility.Utility;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    CustomDialogCreateTodolistBinding customDialogCreateTodolistBinding;
    String formattedDate;
    private DatabaseSQLite dbSqLite;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mainBinding.recyclerView.setLayoutManager(linearLayoutManager);

        //floating action button code for on click event
        mainBinding.floatActBtnCreateTodoList.setOnClickListener(view -> {

            setCurrentDateTime();
            customDialogCreateTodolistBinding = CustomDialogCreateTodolistBinding.inflate(getLayoutInflater());
            //TODO: code to open dialog
            Dialog dialog = new Dialog(this);
            dialog.setContentView(customDialogCreateTodolistBinding.getRoot());
            dialog.setCancelable(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            customDialogCreateTodolistBinding.tvTodoDateTime.setText(formattedDate);
            customDialogCreateTodolistBinding.btnSaveToDoList.setOnClickListener(view1 -> {
                dbSqLite.insertData(MainActivity.this, customDialogCreateTodolistBinding.etTodoTitlle.getText().toString(),
                                    customDialogCreateTodolistBinding.etTodoMsg.getText().toString(),
                                    customDialogCreateTodolistBinding.tvTodoDateTime.getText().toString());
                dialog.dismiss();
            });

        });

        dbSqLite = new DatabaseSQLite(this);
        dbSqLite.openDatabase();

        CustomListAdapterRecyclerView adapter = new CustomListAdapterRecyclerView(this, getAllToDOList(), new OnItemClickListener() {
            @Override
            public void onItemClick(List<TodoListModel> todoListModelList, int position) {
                Utility.showLongToast(MainActivity.this, todoListModelList.get(position).getTodoTittle() + " "  + todoListModelList.get(position).getTodoMsg() +" "+ todoListModelList.get(position).getDatetime());
            }
        });
        mainBinding.recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private List<TodoListModel> getAllToDOList() {

        List<TodoListModel> list =new ArrayList<>();
        cursor = dbSqLite.getAllData();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            do {

                TodoListModel todoListModel  = new TodoListModel();
                todoListModel.setRowId(cursor.getString(0));
                todoListModel.setTodoTittle(cursor.getString(1));
                todoListModel.setTodoMsg(cursor.getString(2));
                todoListModel.setDatetime(cursor.getString(3));

            }while (cursor.moveToNext());
        }
        return list;
    }


    public void setCurrentDateTime(){
        Date currentTime = Calendar.getInstance().getTime();
        formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

//        customDialogCreateTodolistBinding.tvTodoDateTime.setText(formattedDate);

        Log.d("TAG_currentTime", "setCurrentDateTime: " + currentTime.toString());
        Log.d("TAG_formattedDate", "setCurrentDateTime: " + formattedDate);
    }
}