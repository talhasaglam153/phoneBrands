package com.tcoding.phonebrands.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.squareup.picasso.Picasso
import com.tcoding.phonebrands.R
import com.tcoding.phonebrands.databinding.FragmentPhoneFeatureBinding
import com.tcoding.phonebrands.model.phonedetail.Feature
import com.tcoding.phonebrands.viewmodel.PhoneFeatureViewModel


class PhoneFeatureFragment : Fragment() {

    lateinit var binding: FragmentPhoneFeatureBinding
    private val args: PhoneFeatureFragmentArgs by navArgs()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPhoneFeatureBinding.inflate(inflater, container, false)

        initViewModel()
        return binding.root
    }


    fun initViewModel() {

        val viewModel =  ViewModelProvider(requireActivity()).get(PhoneFeatureViewModel::class.java)

        viewModel.clearData()
        viewModel.getLiveDataList().observe(requireActivity(), Observer {
            initView(it)
        })

        viewModel.getLiveDataBoolean().observe(requireActivity(), Observer {

            if(it) {
                binding.progressBar.visibility = View.GONE
                binding.view.visibility = View.VISIBLE

            }
            else {
                binding.view.visibility = View.GONE
                binding.progressBar.visibility = View.VISIBLE
            }

        })

        viewModel.callAPI(args.phoneX.slug)
    }

    fun initView(feature: Feature) {
        Picasso.get()
            .load(feature.data.phone_images?.get(0))
            .into(binding.ivPhoneImage)

        binding.tvReleaseDate.text = feature.data.release_date

        binding.tvStorage.text = feature.data.storage

        binding.tvPhoneName.text = feature.data.phone_name

        binding.tvOnKamera.text = feature.data.specifications[7].specs[0].`val`[0]

        binding.tvMainCamera.text = feature.data.specifications[6].specs[0].`val`[0]

        binding.tvMemory.text = feature.data.specifications[5].specs[1].`val`[0]

        if(feature.data.specifications[11].specs.size > 1) {
            binding.tvBattery.text = feature.data.specifications[11].specs[0].`val`[0]+"\n"+feature.data.specifications[11].specs[1].key +":" + feature.data.specifications[11].specs[1].`val`[0]
        }else {
            binding.tvBattery.text = feature.data.specifications[11].specs[0].`val`[0]
        }



        binding.tvResolution.text = feature.data.specifications[3].specs[2].`val`[0]



    }


}