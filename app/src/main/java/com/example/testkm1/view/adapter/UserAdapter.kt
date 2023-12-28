package com.example.testkm1.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testkm1.data.response.DataItem
import com.example.testkm1.databinding.ItemUserBinding
import com.example.testkm1.view.SecondScreenActivity

class UserAdapter(private val context: Context) : PagingDataAdapter<DataItem, UserAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null){
            holder.bind(user)
            holder.itemView.setOnClickListener{
                val intent = Intent(holder.itemView.context, SecondScreenActivity::class.java)
                intent.putExtra("first_name", user.firstName)
                intent.putExtra("last_name", user.lastName)
                holder.itemView.context.startActivity(intent)
            }
        }
    }

    class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            binding.nameTextView.text = "${user.firstName} ${user.lastName}"
            binding.emailTextView.text = user.email
            Glide.with(binding.root.context)
                .load(user.avatar)
                .into(binding.circleImageView)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: DataItem,
                newItem: DataItem
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
