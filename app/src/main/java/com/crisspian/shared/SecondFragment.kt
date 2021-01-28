package com.crisspian.shared

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.crisspian.shared.databinding.FragmentSecondBinding
import com.crisspian.shared.model.Task

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding
    private val viewModel: TaskViewModel by activityViewModels()
    private var idTask : Int = 0
    private var taskSelected : Task? = null

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.selectedItem().observe(viewLifecycleOwner, Observer {
            it?.let{
                binding.etTitle.setText(it.title)
                binding.etDescripcion.setText(it.taskDescription)
                binding.etDate.setText(it.date)
                binding.etPriority.setText(it.priority.toString())
                binding.cbStateNew.isChecked = it.state
                Log.d("ID", it.id.toString())
                idTask = it.id
                taskSelected = it

            }
        })

        binding.btnSave.setOnClickListener {
            saveData()
            viewModel.selected(null)
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }
    fun saveData() {
        val title = binding.etTitle.text.toString()
        val description = binding.etDescripcion.text.toString()
        val date = binding.etDate.text.toString()
        val priority = binding.etPriority.text.toString().toInt()
        val state = binding.cbStateNew.isChecked

        if (title.isEmpty() && description.isEmpty() && date.isEmpty()) {
            Toast.makeText(context, "Debes agregar datos", Toast.LENGTH_SHORT).show()
        } else {
            if(idTask == 0) {
                val newTask = Task(title = title,
                        taskDescription = description,
                        date = date,
                        priority = priority,
                        state = state)
                viewModel.insertTask(newTask)
            } else {
                val newTask = Task(
                        id = idTask,
                        title = title,
                        taskDescription = description,
                        date = date,
                        priority = priority,
                        state = state)
                viewModel.insertTask(newTask)

            }
        }

    }

}