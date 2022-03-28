package com.example.myapp.adapter

import android.graphics.Movie
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapp.databinding.FoodsLayoutBinding
import com.example.myapp.retrofit.Result

class FoodAdapters(private var result : List<Result>) : RecyclerView.Adapter<FoodAdapters.ViewHolder>(),
    Filterable {

    var foodFilter = result
    fun setData(result : List<Result>){
        this.result = result
        this.foodFilter = result
        notifyDataSetChanged()
    }
    fun setFilterData(){
        this.foodFilter = result
    }
    class ViewHolder(val binding : FoodsLayoutBinding) : RecyclerView.ViewHolder(binding.root)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  FoodsLayoutBinding.inflate(inflater,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = result[position]
        holder.binding.apply {
            Glide.with(holder.itemView.context).load(current.image).into(holder.binding.image)
            text.text = current.title
        }
    }

    override fun getItemCount(): Int {
        return result.size
    }

    override fun getFilter() : Filter {
        return object : Filter(){
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val filterResults = FilterResults()
                if(constraint.isNullOrEmpty()){
                    filterResults.count = foodFilter.size
                    filterResults.values = foodFilter
                }
                else{
                    var searchLetter = constraint.toString().lowercase()
                    val itemEx = ArrayList<Result>()
                    for(item in foodFilter){
                        if(item.title.lowercase().contains(searchLetter)){
                            itemEx.add(item)
                        }
                    }
                    filterResults.count = itemEx.size
                    filterResults.values = itemEx
                }
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                result = results?.values as ArrayList<Result>
                notifyDataSetChanged()
            }

        }
    }
        }
