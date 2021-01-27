package com.crisspian.shared

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.crisspian.shared.model.Task
import com.crisspian.shared.model.TaskDataBase
import com.crisspian.shared.model.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(application: Application): AndroidViewModel(application) {

private val repository: TaskRepository
    val allTask : LiveData<List<Task>>

    init{
        val taskDao = TaskDataBase.getDataBase(application).getTaskDao()
        repository = TaskRepository(taskDao)
        allTask = repository.listAllTask
    }

    fun  inserTask(task: Task)= viewModelScope.launch {
        repository.createTask(task)
    }

    fun deleteAll()= viewModelScope.launch {
        repository.deleteAllTask()
    }
}