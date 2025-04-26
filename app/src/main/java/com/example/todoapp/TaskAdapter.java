package com.example.todoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private ArrayList<Task> taskList;
    private Context context;
    public TaskAdapter(ArrayList<Task> taskList, Context context){
        this.taskList = taskList;
        this.context = context;
    }
    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_task_card, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = this.taskList.get(position);
        holder.taskTitle.setText(task.getName());
        holder.taskDescription.setText(task.getDescription());

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder{
        public TextView taskTitle;
        public TextView taskDescription;
        public LinearLayout taskPriority;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.taskTitle = itemView.findViewById(R.id.taskTitle);
            this.taskDescription = itemView.findViewById(R.id.taskDescription);
            this.taskPriority = itemView.findViewById(R.id.taskPriority);

        }
    }
}
