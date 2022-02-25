package nic.ocean.todosqliteapp.utility;

import android.content.Context;
import android.widget.Toast;

public class Utility {

    public static void showShortToast(Context context, String msg){
        Toast.makeText(context,  msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(Context context, String msg){
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
