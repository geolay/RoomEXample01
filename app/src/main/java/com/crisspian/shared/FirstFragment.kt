package com.crisspian.shared

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.crisspian.shared.databinding.FragmentFirstBinding
import com.crisspian.shared.model.Task

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private lateinit var binding: FragmentFirstBinding
    private val viewModel : TaskViewModel by activityViewModels()


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // se instancia adapter y se pasa a RV
        val adapter = TaskAdapter()
        binding.rvTask.adapter = adapter
        binding.rvTask.layoutManager = LinearLayoutManager(context)

        val task = Task(1,
            "Mi primera Tarea 1",
            "tarea de prueba 1",
            "27-01-2020",
            2,
            false)

        val task2 = Task(2,
            "Mi primera Tarea 2",
            "tarea de prueba 2",
            "27-01-2020",
            1,
            false)

        val task3 = Task(3,
            "Mi primera Tarea 3",
            "tarea de prueba 3",
            "27-01-2020",
            1,
            true)

        viewModel.inserTask(task)
        viewModel.inserTask(task2)
        viewModel.inserTask(task3)

        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            adapter.update(it)

          //  println(it)
          //  Log.d("lista ",it.toString())
        } )

    }
}