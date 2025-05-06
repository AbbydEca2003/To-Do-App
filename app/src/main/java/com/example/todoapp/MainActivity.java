package com.example.todoapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton floatingButton;
    private ArrayList<Task> taskList;
    private RecyclerView recycleView;
    private TaskAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));

        floatingButton = findViewById(R.id.floatingButton);
        dbHelper = new DatabaseHelper(this);
        taskList = dbHelper.getAllTask();

        adapter = new TaskAdapter(taskList, this);
        recycleView.setAdapter(adapter);

        floatingButton.setOnClickListener(v -> addNewTask());
    }

    private void addNewTask() {
        View DialogView = LayoutInflater.from(this).inflate(R.layout.activity_add_new_task, null);

        EditText taskTitle = DialogView.findViewById(R.id.taskTitleInput);
        EditText taskDescription = DialogView.findViewById(R.id.taskDescriptionInput);
        DatePicker taskDueDate = DialogView.findViewById(R.id.taskDueDateInput);
        Spinner taskPriority = DialogView.findViewById(R.id.taskPriorityInput);

        new AlertDialog.Builder(this)
                .setView(DialogView)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("New Task", (dialog, which) -> {
                    String name = taskTitle.getText().toString().trim();
                    String description = taskDescription.getText().toString().trim();
                    int day = taskDueDate.getDayOfMonth();
                    int month = taskDueDate.getMonth() + 1;
                    int year = taskDueDate.getYear();
                    String dueDate = day + "/" + month + "/" + year;
                    String priority = taskPriority.getSelectedItem().toString();

                    if (TextUtils.isEmpty(name) || TextUtils.isEmpty(description)) {
                        Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    Task task = new Task(name, description, priority, dueDate);
                    long id = dbHelper.insertTask(task);

                    if (id != -1) {
                        task.setId((int) id);
                        taskList.add(0, task);
                        adapter.notifyItemInserted(0);
                        recycleView.scrollToPosition(0);
                        Toast.makeText(this, "Task Added", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Failed to add task", Toast.LENGTH_SHORT).show();
                    }
                })
                .show();
    }
}
