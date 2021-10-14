package com.roostersoft.gallozafferano.adapter

import android.view.LayoutInflater
import android.widget.TextView
import com.roostersoft.gallozafferano.databinding.HeaderBinding
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.roostersoft.gallozafferano.R
import com.roostersoft.gallozafferano.databinding.CardViewBinding
import com.roostersoft.gallozafferano.model.RecipeWithIdAndImage
import retrofit2.http.Header
import java.lang.IllegalStateException

class RecipeAdapter(val title: String, val list: List<RecipeWithIdAndImage>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class HeaderViewHolder(binding: HeaderBinding): RecyclerView.ViewHolder(binding.root) {
        val title: TextView
        init {
            title = binding.txtIntestazione
        }
    }

    class CardViewHolder(binding: CardViewBinding): RecyclerView.ViewHolder(binding.root) {
        val image: ImageView
        val title: TextView
        init {
            image = binding.image
            title = binding.title
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when(position) {
            0 -> 0
            else -> 1
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            0 -> HeaderViewHolder(
                HeaderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            else -> CardViewHolder(
                CardViewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is HeaderViewHolder -> {
                holder.title.text = title
            }
            is CardViewHolder -> {
                val recipe = list[position-1]
                holder.title.text = recipe.title

                val circularProgressDrawable = CircularProgressDrawable(holder.image.context)
                circularProgressDrawable.strokeWidth = 5f
                circularProgressDrawable.centerRadius = 30f
                circularProgressDrawable.start()

                Glide.with(holder.image.context)
                    .load(recipe.imageUrl)
                    .placeholder(circularProgressDrawable)
                    .error(R.drawable.sad_face)
                    .centerCrop()
                    .into(holder.image)
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size + 1
    }
}