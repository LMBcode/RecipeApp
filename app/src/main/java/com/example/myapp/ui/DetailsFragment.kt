package com.example.myapp.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.myapp.databinding.DetailsfragmentBinding
import kotlin.math.roundToInt


const val IMAGE = "extra_menu_image"
const val TITLE = "extra_menu_title"
const val SUMMARY = "extra_menu_summary"
const val PRICE = "extra_menu_price"
class DetailsFragment : Fragment() {
    private lateinit var _binding : DetailsfragmentBinding
    private val binding get() = _binding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DetailsfragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        populateDetails()
    }

    private fun populateDetails(){
        arguments?.getString(IMAGE)?.let{  image ->
            Glide.with(this)
                .load(image)
                .centerCrop()
                .into(binding.image)
        }
        binding.apply {
            text.text = arguments?.getString(TITLE,"")
            summary.text = arguments?.getString(SUMMARY,"")
            price.text= arguments?.getDouble(PRICE,0.0)?.roundToInt()?.div(100.0)?.times(5.0).toString()
        }
    }
}