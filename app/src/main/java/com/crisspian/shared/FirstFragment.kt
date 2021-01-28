package com.crisspian.shared

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.crisspian.shared.databinding.FragmentFirstBinding

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



        viewModel.allTask.observe(viewLifecycleOwner, Observer {
            it?.let {
                adapter.update(it)
            }

          //  println(it)
          //  Log.d("lista ",it.toString())
        } )
        binding.fab.setOnClickListener{
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }
        adapter.selectesItem().observe(viewLifecycleOwner, Observer{
            it?.let{
                Log.d("Item Selected", it.title)
                viewModel.selected(it)
                findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)

            }
        })
        }

    }
