package com.crisspian.shared.model

import androidx.lifecycle.LiveData

class TaskRepository(private val taskDao: TaskDao) {

    // este val contiene todos los datos de la BBDD
    val listAllTask: LiveData<List<Task>> = taskDao.getAllTask()


    suspend fun insertTask(task: Task) {
        taskDao.createTask(task)
    }

    // funcion crea tarea BBDD
  //  suspend fun createTask(task: Task){
  //      taskDao.createTask(task)
  //  }

    // funcion elimina UNA tarea BBDD
    suspend fun deleteTask(task: Task){
        taskDao.deleteTask(task)
    }

    // funcion elimina TODAS las tareas BBDD
    suspend fun deleteAllTask(){
        taskDao.deleteAllTask()
    }

}