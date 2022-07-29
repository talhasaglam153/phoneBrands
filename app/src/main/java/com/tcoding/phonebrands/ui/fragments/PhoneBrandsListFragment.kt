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
import androidx.recyclerview.widget.GridLayoutManager
import com.tcoding.phonebrands.R
import com.tcoding.phonebrands.databinding.FragmentPhoneBrandsListBinding
import com.tcoding.phonebrands.model.Phone
import com.tcoding.phonebrands.ui.adapter.PhoneBrandsListAdapter
import com.tcoding.phonebrands.viewmodel.PhoneViewModel

class PhoneBrandsListFragment : Fragment() {

    private lateinit var phoneBrandsListAdapter: PhoneBrandsListAdapter
    private lateinit var binding: FragmentPhoneBrandsListBinding
    private lateinit var phone: Phone
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhoneBrandsListBinding.inflate(inflater, container, false)
        initRecyclerView()

        return binding.root
    }

    override fun onResume() {
        super.onResume()



        initViewModel()

    }


    fun initRecyclerView() {
        phoneBrandsListAdapter = PhoneBrandsListAdapter(::itemClick)
        binding.rv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rv.setHasFixedSize(true)
        binding.rv.adapter = phoneBrandsListAdapter
    }

    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(PhoneViewModel::class.java)
        viewModel.getLiveDataList().observe(requireActivity(), Observer {
            phoneBrandsListAdapter.setUpdatedList(it.data)
            phone = it
        })

        viewModel.callAPI()
    }

    fun itemClick(position: Int) {
        var action = PhoneBrandsListFragmentDirections.actionPhoneBrandsListFragmentToPhoneBrandsFragment(phone.data[position])
        findNavController().navigate(action)
    }



}