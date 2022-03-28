package com.example.myapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.myapp.data.DataSource
import com.example.myapp.data.optionsData
import com.example.myapp.databinding.AdapterBinding
import com.example.myapp.databinding.OptionsBinding

class optionsAdapter(var options : List<optionsData>) : RecyclerView.Adapter<optionsAdapter.ViewHolder>() {


    fun setData(options: List<optionsData>){
        this.options = options
        notifyDataSetChanged()
    }
    class ViewHolder(val binding: OptionsBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): optionsAdapter.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = OptionsBinding.inflate(inflater,parent,false)
        return optionsAdapter.ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: optionsAdapter.ViewHolder, position: Int) {
        val current = options[position]
        holder.binding.txt.text = current.name
        holder.binding.image.setImageResource(current.image)
        holder.binding.image.setOnClickListener { view ->
            view.findNavController().navigate(current.navigation)
        }

    }

    override fun getItemCount(): Int {
        return options.size
    }
}