package com.example.myapp.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp.adapter.FoodAdapters
import com.example.myapp.databinding.BurgerfragmentBinding
import com.example.myapp.databinding.FoodsLayoutBinding
import com.example.myapp.databinding.PizzafragmentBinding
import com.example.myapp.retrofit.Result
import com.example.myapp.retrofit.RetrofitService
import com.example.myapp.viewmodel.MainRepository
import com.example.myapp.viewmodel.MainViewModel
import com.example.myapp.viewmodel.MyViewModelFactory

class hotdogFragment : Fragment() {
    private lateinit var _binding: BurgerfragmentBinding
    private val binding get() = _binding
    private lateinit var adapter: FoodAdapters
    private lateinit var layoutManager: GridLayoutManager
    private lateinit var viewModel: MainViewModel
    private val retrofitService = RetrofitService.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = BurgerfragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel =
            ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
                MainViewModel::class.java
            )
        adapter = FoodAdapters(listOf())
        layoutManager = GridLayoutManager(this.context, 2)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = layoutManager
        }
        viewModel.hot_dog.observe(viewLifecycleOwner) {
            adapter.setData(it as ArrayList<Result>)
                Log.d("BurgerFragment", "list log: $it")
        }
        viewModel.getHotDog()
        binding.searchView.setOnQueryTextListener(object :  android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                adapter.filter.filter(query)
                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })
    }
}




