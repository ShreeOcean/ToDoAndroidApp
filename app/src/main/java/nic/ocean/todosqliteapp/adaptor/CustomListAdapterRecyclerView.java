package nic.ocean.todosqliteapp.adaptor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import nic.ocean.todosqliteapp.R;

public class CustomListAdapterRecyclerView extends RecyclerView.Adapter<CustomListAdapterRecyclerView.ViewHolder> {

    private String[] localDataSet;

    //predefine method where create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        //LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.custom_todo_list, parent, false);

        return new ViewHolder(view);
    }

    //predefine method where it replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        //holder.getTextView().setText(localDataSet[position]);

    }

    //predefine method where it return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {

        return localDataSet.length;
    }

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Define click listener for the ViewHolder's View
        }
    }
}
