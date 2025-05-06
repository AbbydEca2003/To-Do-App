package com.example.todoapp;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

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
        holder.taskDueDate.setText(task.getDuedate());
        String priority = task.getPriority();

        String priorityColor = "#00FF00"; // default to green (Low)

        if (priority.equals("Medium")) {
            priorityColor = "#FFFF00"; // yellow
        } else if (priority.equals("High")) {
            priorityColor = "#FF0000"; // red
        }

        holder.taskPriority.setBackgroundColor(Color.parseColor(priorityColor));

        holder.itemView.setOnClickListener(v->{
            Log.d("click", "clicked");
            boolean completionStatus = task.isCompleted();
            if(completionStatus == true){
                Toast.makeText(context, "Task Completed Undone", Toast.LENGTH_SHORT).show();
                task.setCompleted(false);
                completionStatus = false;
                holder.taskCompleted.setBackgroundColor(Color.TRANSPARENT);
            }else{
                Toast.makeText(context, "Task Completed", Toast.LENGTH_SHORT).show();
                task.setCompleted(true);
                completionStatus = true;
                holder.taskCompleted.setBackgroundColor(Color.parseColor("#b1fca4"));
            }


        });

        holder.itemView.setOnLongClickListener(v->{
            Toast.makeText(context, "Editable menu", Toast.LENGTH_SHORT).show();
            return true;
        });

    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class TaskViewHolder extends RecyclerView.ViewHolder{
        public TextView taskTitle;
        public TextView taskDescription;
        public LinearLayout taskPriority;
        public TextView taskDueDate;
        public LinearLayout taskCompleted;
        boolean isCompleted = false;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            this.taskTitle = itemView.findViewById(R.id.taskTitle);
            this.taskDescription = itemView.findViewById(R.id.taskDescription);
            this.taskPriority = itemView.findViewById(R.id.taskPriority);
            this.taskDueDate = itemView.findViewById(R.id.taskDueDate);
            this.taskCompleted = itemView.findViewById(R.id.taskCompleted);
        }
    }
}
