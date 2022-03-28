package com.example.myapp.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.R
import com.example.myapp.databinding.AdapterBinding
import com.example.myapp.retrofit.Result


class Adapter(private var result : List<Result>,private val openDetails : (result : Result) -> Unit) : RecyclerView.Adapter<Adapter.ViewHolder>() {


    class ViewHolder(val binding: AdapterBinding) : RecyclerView.ViewHolder(binding.root) {
    }
    fun setData(result: List<Result>){
        this.result = result
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = result[position]
        Glide.with(holder.itemView.context).load(current.image).into(holder.binding.image)
        fun bind(result: Result) {
            holder.binding.image.setOnClickListener { openDetails.invoke(result) }
        }
        holder.apply {
            holder.binding.text.text = current.title
            bind(current)
        }

    }

    override fun getItemCount(): Int {
        return result.size
    }
}