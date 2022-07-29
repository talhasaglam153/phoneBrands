package com.tcoding.phonebrands.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.tcoding.phonebrands.R
import com.tcoding.phonebrands.databinding.FragmentPhoneBrandsBinding
import com.tcoding.phonebrands.ui.adapter.PhoneBrandsDetailAdapter
import com.tcoding.phonebrands.viewmodel.PhoneDetailViewModel


class PhoneBrandsFragment : Fragment() {

    lateinit var binding: FragmentPhoneBrandsBinding
    lateinit var brand_slug: String
    lateinit var phoneDetailAdapter: PhoneBrandsDetailAdapter

    private val args: PhoneBrandsFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhoneBrandsBinding.inflate(inflater, container, false)


        // Phone Yerine Data Göndermek lazım
        brand_slug = args.phoneData.brand_slug
        initRecyclerView()
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        initViewModel(brand_slug)
    }

    fun initViewModel(slug: String) {
        val viewModel = ViewModelProvider(requireActivity()).get(PhoneDetailViewModel::class.java)
        viewModel.getLiveDataPhoneDetail().observe(requireActivity(), Observer {
            phoneDetailAdapter.setUpdatesList(it.data.phones)
        })
        viewModel.callAPI(slug)
    }

    fun initRecyclerView() {
        phoneDetailAdapter = PhoneBrandsDetailAdapter()
        binding.rv.layoutManager = LinearLayoutManager(requireContext())
        binding.rv.setHasFixedSize(true)
        binding.rv.adapter = phoneDetailAdapter
    }

}