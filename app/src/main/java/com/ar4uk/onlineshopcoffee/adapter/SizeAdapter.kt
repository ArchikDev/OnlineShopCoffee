package com.ar4uk.onlineshopcoffee.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar4uk.onlineshopcoffee.Model.ItemsModel
import com.ar4uk.onlineshopcoffee.R
import com.ar4uk.onlineshopcoffee.databinding.ViewholderSizeBinding
import com.bumptech.glide.Glide

class SizeAdapter(val items: MutableList<String>): RecyclerView.Adapter<SizeAdapter.Viewholder>() {

    private var selectedPosition = -1
    private var lastSelectedPosition = -1
    private lateinit var context: Context

    inner class Viewholder(val binding: ViewholderSizeBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.Viewholder {
        context = parent.context

        val binding = ViewholderSizeBinding.inflate(LayoutInflater.from(context), parent, false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.Viewholder, position: Int) {
        holder.binding.root.setOnClickListener {
            lastSelectedPosition = selectedPosition
            selectedPosition = position
            notifyItemChanged(lastSelectedPosition)
            notifyItemChanged(selectedPosition)
        }

        if (selectedPosition == position) {
            holder.binding.imgSize.setBackgroundResource(R.drawable.orange_bg)
        } else {
            holder.binding.imgSize.setBackgroundResource(R.drawable.size_bg)
        }

        val imageSize = when(position) {
            0 -> 45.dpToPx(context)
            1 -> 50.dpToPx(context)
            2 -> 55.dpToPx(context)
            3 -> 65.dpToPx(context)
            else -> 70.dpToPx(context)
        }

        val layoutParams = holder.binding.imgSize.layoutParams
        layoutParams.width = imageSize
        layoutParams.height = imageSize

        holder.binding.imgSize.layoutParams = layoutParams

//        holder.binding.titleTxt.text = item.title
//        holder.binding.priceTxt.text = "$" + item.price.toString()
//        holder.binding.ratingBar.rating = item.rating.toFloat()
//        holder.binding.extraTxt.text = item.extra
//
//       Glide.with(holder.itemView.context)
//           .load(item.picUrl[0])
//           .into(holder.binding.pic)
//
//        holder.itemView.setOnClickListener {
//
//        }
    }

    private fun Int.dpToPx(context: Context): Int {
        return (this * context.resources.displayMetrics.density).toInt()
    }

    override fun getItemCount(): Int = items.size
}