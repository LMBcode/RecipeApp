package com.example.myapp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.R
import com.example.myapp.adapter.Adapter
import com.example.myapp.adapter.optionsAdapter
import com.example.myapp.data.optionsData
import com.example.myapp.databinding.FragmentHomeBinding
import com.example.myapp.retrofit.Result
import com.example.myapp.retrofit.RetrofitService
import com.example.myapp.viewmodel.MainRepository
import com.example.myapp.viewmodel.MainViewModel
import com.example.myapp.viewmodel.MyViewModelFactory


class HomeFragment : Fragment() {
    private lateinit var _binding : FragmentHomeBinding
    private val binding get() = _binding
    private lateinit var adapter : Adapter
    private lateinit var VeganAdapter : Adapter
    private lateinit var optionsAdapter : optionsAdapter
    private lateinit var layoutManager1: LinearLayoutManager
    private lateinit var layoutManager2: LinearLayoutManager
    private lateinit var viewModel : MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    private lateinit var layoutManager: LinearLayoutManager

    val list : List<optionsData> =
        listOf(
            optionsData(
                "Burger",
                R.drawable.burger,
                R.id.action_homeFragment_to_burgerFragment
            ),
            optionsData(
                "Pizza",
                R.drawable.pizza,
                R.id.action_homeFragment_to_pizzaFragment
            ),
            optionsData(
                "Hot Dog",
                R.drawable.hotdog,
                R.id.action_homeFragment_to_hotdogFragment
            ),
            optionsData(
                "Pasta",
                R.drawable.pasta,
                R.id.action_homeFragment_to_pastaFragment
            )

        )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)
        adapter = Adapter(listOf()) { details -> getArgs(details) }
        VeganAdapter = Adapter(listOf()) { details -> getArgs(details) }
        optionsAdapter = optionsAdapter(listOf())


        layoutManager1 = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        layoutManager2 = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)
        layoutManager = LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL,false)


        viewModel.recipes.observe(viewLifecycleOwner , Observer {
            adapter.setData(it)
            Log.d("HomeFragment", "list log: $it")
        })
        viewModel.veganFood.observe(viewLifecycleOwner) {
            VeganAdapter.setData(it)
            Log.d("HomeFragment2", "list log: $it")
        }
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView1.adapter = optionsAdapter
            recyclerView2.adapter = VeganAdapter

            recyclerView.layoutManager = layoutManager
            recyclerView1.layoutManager = layoutManager1
            recyclerView2.layoutManager = layoutManager2
        }
        viewModel.getAllRecipe()
        viewModel.getVeganFood()
        optionsAdapter.setData(list)
    }
    fun getArgs(result : Result){
        val args = Bundle()
        args.putSerializable(TITLE,result.title)
        args.putSerializable(SUMMARY,result.summary)
        args.putSerializable(IMAGE,result.image)
        args.putSerializable(PRICE,result.price)
        findNavController().navigate(R.id.detailsFragment,args)
    }

}