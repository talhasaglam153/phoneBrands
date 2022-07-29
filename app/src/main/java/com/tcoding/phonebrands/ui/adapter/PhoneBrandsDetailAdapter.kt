package com.tcoding.phonebrands.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tcoding.phonebrands.R
import com.tcoding.phonebrands.model.Phone
import com.tcoding.phonebrands.model.PhoneX

class PhoneBrandsDetailAdapter: RecyclerView.Adapter<PhoneBrandsDetailAdapter.DetailViewHolder>() {

    var phoneDetailList = ArrayList<PhoneX>()

    fun setUpdatesList(phoneDetailList: ArrayList<PhoneX>){
        this.phoneDetailList = phoneDetailList
        notifyDataSetChanged()
    }

    fun clearList() {
        phoneDetailList.clear()
        notifyDataSetChanged()
    }



    class DetailViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

        var tvPhoneName: TextView
        var ivPhoneDetailImage: ImageView

        init {
            tvPhoneName = itemView.findViewById(R.id.tvPhoneName)
            ivPhoneDetailImage = itemView.findViewById(R.id.ivPhoneDetailImage)
        }

        fun bindData(phoneX: PhoneX) {
            tvPhoneName.setText(phoneX.phone_name)

            Picasso.get()
                .load(phoneX.image)
                .into(ivPhoneDetailImage)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fragment_phone_detail_row, parent, false)
        return DetailViewHolder(view)
    }

    override fun onBindViewHolder(holder: DetailViewHolder, position: Int) {
        holder.bindData(phoneDetailList.get(position))
    }

    override fun getItemCount(): Int {
       return phoneDetailList.size
    }

}