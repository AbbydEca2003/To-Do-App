package com.example.todoapp;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

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
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView = findViewById(R.id.recycleView);
        recycleView.setLayoutManager(new LinearLayoutManager(this));
        floatingButton = findViewById(R.id.floatingButton);

        taskList = new ArrayList<>();
        loadSampleData();

        adapter = new TaskAdapter(taskList, this);
        recycleView.setAdapter(adapter);

        floatingButton.setOnClickListener(v->{
           addNewTask();
        });
    }

    private void addNewTask(){
        View DialogView = LayoutInflater.from(this).inflate(R.layout.activity_add_new_task, null);

        EditText taskTitle = findViewById(R.id.taskTitleInput);
        EditText taskDescription = findViewById(R.id.taskDescriptionInput);
        DatePicker taskDueDate = findViewById(R.id.taskDueDateInput);
        Spinner taskPriority = findViewById(R.id.taskPriorityInput);

        new AlertDialog.Builder(this)
                .setView(DialogView)
                .setNegativeButton("cancil", null)
                .setPositiveButton("New Task", null)
                .show();
    }

    private void loadSampleData(){
        taskList.clear();
        taskList.add(new Task("Math Homework", "Complete exercise 1.1", "High", "1234", true));
        taskList.add(new Task("Science Homework", "Complete experiment 2", "Low", "1234", false));
    }
}
