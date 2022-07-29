package com.tcoding.phonebrands.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tcoding.phonebrands.R
import com.tcoding.phonebrands.model.Data

class PhoneBrandsAdapter: RecyclerView.Adapter<PhoneBrandsAdapter.BrandsViewHolder>() {

    var phoneBrandsList =  ArrayList<Data>()

    @SuppressLint("NotifyDataSetChanged")
    fun setUpdatedList(phoneBrandsList: ArrayList<Data>) {
        this.phoneBrandsList = phoneBrandsList
        notifyDataSetChanged()
    }


    class BrandsViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var tvBrand: TextView

        init {
            tvBrand = itemView.findViewById(R.id.tvBrand)
        }

        fun bindData(data: Data) {
            tvBrand.setText(data.brand_name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        var  view = LayoutInflater.from(parent.context).inflate(R.layout.phone_brands_row, parent, false)
        return BrandsViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        holder.bindData(phoneBrandsList.get(position))
    }

    override fun getItemCount(): Int {
        return phoneBrandsList.size
    }
}