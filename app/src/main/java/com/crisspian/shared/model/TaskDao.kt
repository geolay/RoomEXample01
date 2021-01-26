package com.crisspian.shared.model

import androidx.room.*

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllTask(taskList: List<Task>)

    @Update
    fun updateTask(task: Task)

    @Delete
    fun deleteTask(task: Task)

    @Query("DELETE FROM task_table")
    fun deleteAllTask()

    @Query("SELECT * FROM task_table WHERE id = :mId")
    fun getTaskByID(mId: Int): Task

    @Query("SELECT * FROM task_table")
    fun getAllTask(): List<Task>

}