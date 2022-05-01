package com.example.countries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.countries.appDatabase
import com.example.countries.databinding.FragmentAddCountriesBinding
import com.example.countries.model.Country

class FragmentAddCountries : Fragment() {
    private var _binding: FragmentAddCountriesBinding? = null
    private val binding get() = requireNotNull(_binding) { "View was destroyed" }

    private val countryDao by lazy {
        requireContext().appDatabase.countryDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAddCountriesBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            buttonAdd.setOnClickListener {
                editTextCountryName.text?.takeIf { it.isNotEmpty() }
                    ?.let { country ->
                        countryDao.insertAll(Country(countryName = country.toString()))
                        editTextContainer.error = null
                        Toast.makeText(
                            requireContext(),
                            "Country added successfully",
                            Toast.LENGTH_LONG
                        ).show()
                    } ?: run {
                    editTextContainer.error = "Text field is empty. Please enter country name"
                }
                editTextCountryName.setText("")
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}