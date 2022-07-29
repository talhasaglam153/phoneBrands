package com.tcoding.phonebrands.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.tcoding.phonebrands.R
import com.tcoding.phonebrands.databinding.FragmentPhoneBrandsBinding
import com.tcoding.phonebrands.ui.adapter.PhoneBrandsAdapter
import com.tcoding.phonebrands.viewmodel.PhoneViewModel

class PhoneBrandsFragment : Fragment() {

    private lateinit var phoneBrandsAdapter: PhoneBrandsAdapter
    private lateinit var binding: FragmentPhoneBrandsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhoneBrandsBinding.inflate(inflater, container, false)

      /*  val viewModel = ViewModelProvider(requireActivity()).get(PhoneViewModel::class.java)
        viewModel.getLiveDataList().observe(requireActivity(), Observer {
            binding.textView1.text = it.data[0].brand_name
        })
        viewModel.callAPI()*/
        initRecyclerView()


        return binding.root
    }

    override fun onResume() {
        super.onResume()

        initViewModel()

    }

    fun initRecyclerView() {
        phoneBrandsAdapter = PhoneBrandsAdapter()
        binding.rv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rv.setHasFixedSize(true)
        binding.rv.adapter = phoneBrandsAdapter
    }

    fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(PhoneViewModel::class.java)
        viewModel.getLiveDataList().observe(requireActivity(), Observer {
            phoneBrandsAdapter.setUpdatedList(it.data)
        })

        viewModel.callAPI()
    }




}