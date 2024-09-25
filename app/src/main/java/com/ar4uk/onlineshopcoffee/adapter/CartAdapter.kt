package com.ar4uk.onlineshopcoffee.adapter

import android.content.Context
import android.content.Intent
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ar4uk.onlineshopcoffee.Model.ItemsModel
import com.ar4uk.onlineshopcoffee.activity.DetailActivity
import com.ar4uk.onlineshopcoffee.databinding.ViewholderCartBinding
import com.ar4uk.onlineshopcoffee.databinding.ViewholderPopularBinding
import com.ar4uk.onlineshopcoffee.helper.ChangeNumberItemsListener
import com.ar4uk.onlineshopcoffee.helper.ManagmentCart
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions

class CartAdapter(
    private val listItemSelected: ArrayList<ItemsModel>,
    context: Context,
    var changeNumberItemsListener: ChangeNumberItemsListener? = null
): RecyclerView.Adapter<CartAdapter.Viewholder>() {

    class Viewholder(val binding: ViewholderCartBinding): RecyclerView.ViewHolder(binding.root)

    private val managmentCart = ManagmentCart(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.Viewholder {
        val binding = ViewholderCartBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return Viewholder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.Viewholder, position: Int) {
        val item = listItemSelected[position]

        holder.binding.titleTxt.text = item.title
        holder.binding.feeEachItemTxt.text = "$${item.price}"
        holder.binding.totalEachItemTxt.text = "$${Math.round(item.numberInCart * item.price)}"
        holder.binding.counter.text = item.numberInCart.toString()

       Glide.with(holder.itemView.context)
           .load(item.picUrl[0])
           .apply { RequestOptions().transform(CenterCrop()) }
           .into(holder.binding.imgCart)

        holder.binding.plus.setOnClickListener {
            managmentCart.plusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

        holder.binding.minus.setOnClickListener {
            managmentCart.minusItem(listItemSelected, position, object : ChangeNumberItemsListener {
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }
    }

    override fun getItemCount(): Int = listItemSelected.size
}