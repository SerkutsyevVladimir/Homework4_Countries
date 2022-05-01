package com.example.countries.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countries.adapters.CountryAdapter
import com.example.countries.appDatabase
import com.example.countries.databinding.FragmentListBinding
import com.example.countries.extensions.addSpaceDecoration

class FragmentList : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = requireNotNull(_binding)

    private val countryDao by lazy {
        requireContext().appDatabase.countryDao()
    }

    private val adapter by lazy {
        CountryAdapter(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentListBinding.inflate(inflater, container, false)
            .also { _binding = it }
            .root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(view.context)

            val countries = countryDao.getAll().toList()

            recyclerView.adapter = adapter
            recyclerView.addSpaceDecoration(SPACE_SIZE)
            adapter.submitList(countries)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val SPACE_SIZE = 50

    }
}