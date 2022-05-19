package com.test.com.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.com.R
import com.test.com.databinding.ProductItemBinding
import com.test.com.models.ProductsItem

class RecyclerviewAdapter(val context: Context) :
    ListAdapter<ProductsItem, RecyclerviewAdapter.MyViewHolder>(diffCallBack) {

    private var listener: OnItemClickedListener? = null

    class MyViewHolder(val binding: ProductItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ProductItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = getItem(position)
        Glide.with(holder.itemView).load(product.photoUrl).placeholder(R.drawable.ic_baseline_downloading_24).into(holder.binding.imgProduct)
        holder.binding.txtProductId.setText(context.getString(R.string.id) + "\n" + product.id)
        holder.binding.txtProductName.setText(context.getString(R.string.name) + "\n" + product.name)
        holder.binding.txtDescription.setText(context.getString(R.string.description) + "\n" + product.description)
        holder.binding.txtProductPrice.setText(context.getString(R.string.price) + "\n" + product.price)
        holder.itemView.setOnClickListener {
            listener?.onItemClicked(product)
        }
    }


    companion object {
        val diffCallBack = object : DiffUtil.ItemCallback<ProductsItem>() {
            override fun areItemsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
                return oldItem == newItem

            }

            override fun areContentsTheSame(oldItem: ProductsItem, newItem: ProductsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

    interface OnItemClickedListener {
        fun onItemClicked(productsItem: ProductsItem)
    }

    fun setOnItemClickedListener(onItemClickedListener: OnItemClickedListener) {
        this.listener = onItemClickedListener
    }
}