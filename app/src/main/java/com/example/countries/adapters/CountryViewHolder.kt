package com.example.countries.adapters

import android.content.Context
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.countries.appDatabase
import com.example.countries.databinding.ItemCountryBinding
import com.example.countries.model.Country

class CountryViewHolder(
    private val binding: ItemCountryBinding,
    private val context: Context
) : RecyclerView.ViewHolder(binding.root) {

    private val countryDao by lazy {
        context.appDatabase.countryDao()
    }

    fun bind(country: Country) {
        binding.textView.text = country.countryName
        binding.root.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete")
                .setMessage("Are you sure you want to delete this country from the list?")
                .setPositiveButton(android.R.string.ok) { dialog, buttonId ->
                    countryDao.delete(country)

                }
                .setNegativeButton(android.R.string.cancel, null)
                .show()
        }
    }
}