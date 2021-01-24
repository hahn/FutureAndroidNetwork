package com.example.futureandroidnetwork.codelab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.futureandroidnetwork.R
import com.example.futureandroidnetwork.codelab2.model.GithubRepo

class GithubRepoAdapter(private val items: List<GithubRepo>) : RecyclerView.Adapter<GithubRepoAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_github, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class DataViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(item: GithubRepo) = with(itemView) {
            val description = if (item.description.isNullOrEmpty()) {
                item.fullname
            } else {
                item.description
            }

            findViewById<TextView>(R.id.tv_title).text = "${item.name}"
            findViewById<TextView>(R.id.tv_subtitle).text = "$description"
        }
    }
}