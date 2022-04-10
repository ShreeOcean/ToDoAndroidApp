package nic.ocean.todosqliteapp.adaptor;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nic.ocean.todosqliteapp.R;
import nic.ocean.todosqliteapp.UpdateDeleteTODOListActivity;
import nic.ocean.todosqliteapp.databinding.CustomDialogCreateTodolistBinding;
import nic.ocean.todosqliteapp.databinding.CustomTodoListBinding;
import nic.ocean.todosqliteapp.interfaces.OnItemClickListener;
import nic.ocean.todosqliteapp.model.TodoListModel;

public class CustomListAdapterRecyclerView extends RecyclerView.Adapter<CustomListAdapterRecyclerView.ViewHolder>{

    CustomDialogCreateTodolistBinding customDialogCreateTodolistBinding;
//    private String[] localDataSet;
    private Context context;
    private List<TodoListModel> todoDataModelList;
    private OnItemClickListener listner;

    public CustomListAdapterRecyclerView(Context context, List<TodoListModel> todoDataModelList) {
        this.context = context;
        this.todoDataModelList = todoDataModelList;
    }

    public CustomListAdapterRecyclerView(Context context, List<TodoListModel> todoDataModelList, OnItemClickListener listner) {
        this.context = context;
        this.todoDataModelList = todoDataModelList;
        this.listner = listner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CustomTodoListBinding customTodoListBinding = CustomTodoListBinding.inflate(LayoutInflater.from(context), parent, false);
            return new ViewHolder(customTodoListBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TodoListModel data =  todoDataModelList.get(position);
        holder.customTodoListBinding.tvTodoTitlle.setText(data.getTodoTittle());
        holder.customTodoListBinding.tvTodoMsg.setText(data.getTodoMsg());
        holder.customTodoListBinding.tvTodoDateTime.setText(data.getDatetime());

        listner.onItemClick(todoDataModelList, position);
        holder.itemView.setOnClickListener(view -> {
            //Toast.makeText(context, "from onBindViewHolder() -->" + todoDataModelList.get(position), Toast.LENGTH_SHORT).show();

//            final Dialog dialog = new Dialog(context);
//            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
//            dialog.setContentView(R.layout.custom_dialog_create_todolist);
//            dialog.show();
//            Window window = dialog.getWindow();
//            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);

            Intent intent = new Intent(context, UpdateDeleteTODOListActivity.class);
            intent.putExtra("todo_tittle", data.getTodoTittle());
            intent.putExtra("todo_body", data.getTodoMsg());
            intent.putExtra("todo_date_time", data.getDatetime());
            intent.putExtra("todo_item_row_id", data.getRowId());
            context.startActivity(intent);

        });

    }

    //predefine method where it return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return todoDataModelList.size();
    }
    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder{
        CustomTodoListBinding customTodoListBinding;
        public ViewHolder(@NonNull CustomTodoListBinding itemView) {
            super(itemView.getRoot());
            customTodoListBinding = itemView;
            // Define click listener for the ViewHolder's View



        }

    }
}
