package nic.ocean.todosqliteapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import nic.ocean.todosqliteapp.adaptor.CustomListAdapterRecyclerView;
import nic.ocean.todosqliteapp.databinding.ActivityMainBinding;
import nic.ocean.todosqliteapp.databinding.CustomDialogCreateTodolistBinding;
import nic.ocean.todosqliteapp.db.DatabaseSQLite;
import nic.ocean.todosqliteapp.interfaces.OnItemClickListener;
import nic.ocean.todosqliteapp.model.TodoListModel;
import nic.ocean.todosqliteapp.utility.Utility;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding mainBinding;
    CustomDialogCreateTodolistBinding customDialogCreateTodolistBinding;
    String formattedDate;
    public DatabaseSQLite dbSqLite;
    Cursor cursor;
    String currentDateandTime;
    private int clickedPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

        dbSqLite = new DatabaseSQLite(this);
        dbSqLite.openDatabase();
        loadDataInListView();

        StaggeredGridLayoutManager linearLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mainBinding.recyclerView.setLayoutManager(linearLayoutManager);

        //floating action button code for on click event
        mainBinding.floatActBtnCreateTodoList.setOnClickListener(view -> {

            customDialogCreateTodolistBinding = CustomDialogCreateTodolistBinding.inflate(getLayoutInflater());
            //TODO: code to open dialog
            Dialog dialog = new Dialog(this);
            dialog.setContentView(customDialogCreateTodolistBinding.getRoot());
            dialog.setCancelable(false);
            dialog.show();
            Window window = dialog.getWindow();
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            customDialogCreateTodolistBinding.btnSaveToDoList.setOnClickListener(view1 -> {
                setCurrentDateTime();
                dbSqLite.insertData(MainActivity.this, customDialogCreateTodolistBinding.etTodoTitlle.getText().toString(),
                                        customDialogCreateTodolistBinding.etTodoMsg.getText().toString(),
                                        customDialogCreateTodolistBinding.tvTodoDateTime.getText().toString());

                loadDataInListView();
                dialog.dismiss();
            });
            customDialogCreateTodolistBinding.btnCloseDialog.setOnClickListener(view1 -> {
                dialog.dismiss();
            });

        });
    }

        private void loadDataInListView() {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            mainBinding.recyclerView.setLayoutManager(linearLayoutManager);
            // MyRecyclerView Class
            CustomListAdapterRecyclerView adapter = new CustomListAdapterRecyclerView(this, getAllToDOList(), new OnItemClickListener() {
                @Override
                public void onItemClick(List<TodoListModel> todoListModelList, int position) {

                }
            });

            mainBinding.recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();

        }

//        CustomListAdapterRecyclerView adapter = new CustomListAdapterRecyclerView(this, getAllToDOList(), new OnItemClickListener() {
//            @Override
//            public void onItemClick(List<TodoListModel> todoListModelList, int position) {
//              //  Utility.showLongToast(MainActivity.this, todoListModelList.get(position).getTodoTittle() + " "  + todoListModelList.get(position).getTodoMsg() +" "+ todoListModelList.get(position).getDatetime());
//            }
//        });
//        mainBinding.recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//    }

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
                list.add(todoListModel);
            }while (cursor.moveToNext());

        }
        return list;
    }


    public void setCurrentDateTime(){
        //Date currentTime = Calendar.getInstance().getTime();
       // formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
//        customDialogCreateTodolistBinding.tvTodoDateTime.setText(formattedDate);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH:mm:ss", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());
        customDialogCreateTodolistBinding.tvTodoDateTime.setText(currentDateandTime);
//        Date currentTime = Calendar.getInstance().getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd__HH:mm:ss", Locale.getDefault());
//        currentDateandTime = sdf.format(new Date());
//        Log.d("TAG_currentTime", "setCurrentDateTime: " + currentTime.toString());
//        Log.d("TAG_formattedDate", "setCurrentDateTime: " + formattedDate);
        Log.d("TAG_currentDateandTime", "setCurrentDateTime_____SimpleDateFormat: " + currentDateandTime);

    }

}