package com.example.countries.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.databinding.ItemCountryBinding
import com.example.countries.model.Country

class CountryAdapter(
    context: Context
) : ListAdapter<Country, RecyclerView.ViewHolder>(DIFF_UTIL) {

    private val layoutInflater = LayoutInflater.from(context)

    private val context = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        return CountryViewHolder(
            binding = ItemCountryBinding.inflate(
                layoutInflater,
                parent,
                false
            ), context
        )
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val countryVH = holder as CountryViewHolder
        val country = getItem(position) as Country
        countryVH.bind(country)
    }


    companion object {
        private val DIFF_UTIL = object : DiffUtil.ItemCallback<Country>() {
            override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
                return oldItem == newItem
            }
        }
    }

}