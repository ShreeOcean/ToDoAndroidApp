package nic.ocean.todosqliteapp.interfaces;

import java.util.List;

import nic.ocean.todosqliteapp.model.TodoListModel;

public interface OnItemClickListener {

    void onItemClick(List<TodoListModel> todoListModelList, int position);

}
