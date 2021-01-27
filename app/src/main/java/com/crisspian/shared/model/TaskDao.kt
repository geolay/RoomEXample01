package com.crisspian.shared.model

import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun createTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllTask(taskList: List<Task>)

    @Update
    suspend fun updateTask(task: Task)

    @Delete
    suspend fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTask()

    @Query("SELECT * FROM task_table WHERE id = :mId")
    fun getTaskByID(mId: Int): androidx.lifecycle.LiveData<Task>

    @Query("SELECT * FROM task_table")
    fun getAllTask(): androidx.lifecycle.LiveData<List<Task>>

}