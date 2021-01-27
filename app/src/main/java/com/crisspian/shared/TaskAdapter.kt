package com.crisspian.shared

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.crisspian.shared.databinding.TaskItemBinding
import com.crisspian.shared.model.Task

class TaskAdapter: RecyclerView.Adapter<TaskAdapter.TaskVH>() {
    private var mListTask = listOf<Task>()

    // acà pasan los datos
    fun update(listTask: List<Task>){
        mListTask = listTask
        notifyDataSetChanged()
    }

    class   TaskVH(private val binding: TaskItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(task: Task){
            binding.textViewTltle.text = task.title
            binding.textViewDescription.text = task.taskDescription
            binding.textViewDate.text = task.date
            binding.checkBoxState.isChecked = task.state
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskVH{
        return TaskVH(TaskItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TaskVH, position: Int){
        val task = mListTask[position]
        holder.bind(task)
    }

    override fun getItemCount(): Int = mListTask.size
}