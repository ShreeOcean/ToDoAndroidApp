package nic.ocean.todosqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

import nic.ocean.todosqliteapp.databinding.ActivityMainBinding;
import nic.ocean.todosqliteapp.databinding.CustomDialogCreateTodolistBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mainBinding;
    CustomDialogCreateTodolistBinding customDialogCreateTodolistBinding;
    String formattedDate;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(mainBinding.getRoot());

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

        });
    }

    public void setCurrentDateTime(){
        Date currentTime = Calendar.getInstance().getTime();
        formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);

//        customDialogCreateTodolistBinding.tvTodoDateTime.setText(formattedDate);

        Log.d("TAG_currentTime", "setCurrentDateTime: " + currentTime.toString());
        Log.d("TAG_formattedDate", "setCurrentDateTime: " + formattedDate);
    }
}