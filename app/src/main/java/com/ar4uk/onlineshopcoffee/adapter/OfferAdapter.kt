package com.ar4uk.onlineshopcoffee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar4uk.onlineshopcoffee.Model.CategoryModel
import com.ar4uk.onlineshopcoffee.Model.ItemsModel
import com.ar4uk.onlineshopcoffee.R
import com.ar4uk.onlineshopcoffee.databinding.ViewholderCategoryBinding
import com.ar4uk.onlineshopcoffee.databinding.ViewholderOfferBinding
import com.ar4uk.onlineshopcoffee.databinding.ViewholderPopularBinding
import com.bumptech.glide.Glide

class OfferAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<OfferAdapter.Viewholder>() {
    private lateinit var context: Context

    inner class Viewholder(val binding: ViewholderOfferBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferAdapter.Viewholder {
        context = parent.context

        val binding = ViewholderOfferBinding.inflate(LayoutInflater.from(context), parent, false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: OfferAdapter.Viewholder, position: Int) {
        val item = items[position]

        holder.binding.titleOffer.text = item.title
        holder.binding.priceOffer.text = "$" + item.price.toString()

       Glide.with(holder.itemView.context)
           .load(item.picUrl[0])
           .into(holder.binding.picOffer)

        holder.itemView.setOnClickListener {

        }
    }

    override fun getItemCount(): Int = items.size
}