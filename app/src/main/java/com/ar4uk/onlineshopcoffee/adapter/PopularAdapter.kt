package com.ar4uk.onlineshopcoffee.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar4uk.onlineshopcoffee.Model.ItemsModel
import com.ar4uk.onlineshopcoffee.activity.DetailActivity
import com.ar4uk.onlineshopcoffee.databinding.ViewholderPopularBinding
import com.bumptech.glide.Glide

class PopularAdapter(val items: MutableList<ItemsModel>): RecyclerView.Adapter<PopularAdapter.Viewholder>() {
    private lateinit var context: Context

    inner class Viewholder(val binding: ViewholderPopularBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularAdapter.Viewholder {
        context = parent.context

        val binding = ViewholderPopularBinding.inflate(LayoutInflater.from(context), parent, false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: PopularAdapter.Viewholder, position: Int) {
        val item = items[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.priceTxt.text = "$" + item.price.toString()
        holder.binding.ratingBar.rating = item.rating.toFloat()
        holder.binding.extraTxt.text = item.extra

       Glide.with(holder.itemView.context)
           .load(item.picUrl?.get(0))
           .into(holder.binding.pic)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, DetailActivity::class.java)

            intent.putExtra("object", item)

            holder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int = items.size
}