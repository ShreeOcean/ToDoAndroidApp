package nic.ocean.todosqliteapp.model;

public class TodoListModel {

    private String rowId;
    private String todoTittle;
    private String todoMsg;
    private String datetime;

    public TodoListModel(String rowId, String todoTittle, String todoMsg, String datetime) {
        this.rowId = rowId;
        this.todoTittle = todoTittle;
        this.todoMsg = todoMsg;
        this.datetime = datetime;
    }

    public String getRowId() {
        return rowId;
    }

    public void setRowId(String rowId) {
        this.rowId = rowId;
    }

    public String getTodoTittle() {
        return todoTittle;
    }

    public void setTodoTittle(String todoTittle) {
        this.todoTittle = todoTittle;
    }

    public String getTodoMsg() {
        return todoMsg;
    }

    public void setTodoMsg(String todoMsg) {
        this.todoMsg = todoMsg;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
