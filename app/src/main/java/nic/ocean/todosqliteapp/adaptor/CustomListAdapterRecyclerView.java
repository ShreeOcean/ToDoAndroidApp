package nic.ocean.todosqliteapp.adaptor;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nic.ocean.todosqliteapp.R;
import nic.ocean.todosqliteapp.databinding.CustomDialogCreateTodolistBinding;
import nic.ocean.todosqliteapp.databinding.CustomTodoListBinding;
import nic.ocean.todosqliteapp.interfaces.OnItemClickListener;
import nic.ocean.todosqliteapp.model.TodoListModel;

public class CustomListAdapterRecyclerView extends RecyclerView.Adapter<CustomListAdapterRecyclerView.ViewHolder> {

    ///CustomDialogCreateTodolistBinding customDialogCreateTodolistBinding;

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

    //predefine method where create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        //LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
//        View view = LayoutInflater.from(parent.getContext())
//                .inflate(R.layout.custom_todo_list, parent, false);

        CustomTodoListBinding customTodoListBinding = CustomTodoListBinding.inflate(LayoutInflater.from(context), parent, false);
        return new ViewHolder(customTodoListBinding);

        //return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_todo_list, parent, false));
    }

    //predefine method where it replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //holder.getTextView().setText(localDataSet[position]);
        TodoListModel data =  todoDataModelList.get(position);
        holder.customTodoListBinding.tvTodoTitlle.setText(data.getTodoTittle());
        holder.customTodoListBinding.tvTodoMsg.setText(data.getTodoMsg());
        holder.customTodoListBinding.tvTodoDateTime.setText(data.getDatetime());


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
    public class ViewHolder extends RecyclerView.ViewHolder {

        CustomTodoListBinding customTodoListBinding;

        public ViewHolder(@NonNull CustomTodoListBinding itemView) {
            super(itemView.getRoot());
            customTodoListBinding = itemView;
            // Define click listener for the ViewHolder's View
        }
    }
}
