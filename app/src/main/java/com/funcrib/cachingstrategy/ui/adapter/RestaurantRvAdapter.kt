package com.funcrib.cachingstrategy.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.funcrib.cachingstrategy.R
import com.funcrib.cachingstrategy.data.domain.Restaurant

class RestaurantRvAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<Restaurant>() {

        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

    }
    private val differ = AsyncListDiffer(this, diffCallback)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.restaurant_item,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(differ.currentList[position])
            }

        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(list: List<Restaurant>) {
        differ.submitList(list)
    }

    class ViewHolder
    constructor(
        itemView: View,
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(item: Restaurant) = with(itemView) {

            val tvRestaurantName = itemView.findViewById<TextView>(R.id.restaurantName)
            val tvRestaurantType = itemView.findViewById<TextView>(R.id.tvType)
            val tvRestaurantPhone = itemView.findViewById<TextView>(R.id.tvPhoneNumber)
            val tvRestaurantAddress = itemView.findViewById<TextView>(R.id.tvAddress)
            val ivRestaurantLogo = itemView.findViewById<ImageView>(R.id.restaurantImage)

            tvRestaurantName.text = item.name
            tvRestaurantPhone.text = item.phone_number
            tvRestaurantType.text = item.type
            tvRestaurantAddress.text = item.address

            Glide.with(itemView)
                .load(item.logo)
                .into(ivRestaurantLogo)
        }
    }

    interface Interaction {
        fun onItemSelected(position: Int, item: Restaurant)
    }
}