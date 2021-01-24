package com.example.futureandroidnetwork.codelab2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.futureandroidnetwork.R
import com.example.futureandroidnetwork.codelab2.model.GithubRepo
import com.example.futureandroidnetwork.codelab2.model.GithubUser

class GithubAdapter(val items: List<GithubUser>, val listener: (GithubUser) -> Unit) :
    RecyclerView.Adapter<GithubAdapter.DataViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_github, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        holder.bind(items[position], listener)
    }

    override fun getItemCount(): Int = items.size

    inner class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val tvTitle = itemView.findViewById<TextView>(R.id.tv_title)
            val tvSubtitle = itemView.findViewById<TextView>(R.id.tv_subtitle)

        fun bind(item: GithubUser, listener: (GithubUser) -> Unit) {

            tvTitle.text = "${item.login}"
            tvSubtitle.text = "${item.url}"

            itemView.rootView.setOnClickListener {
                listener(item)
            }
        }
    }
}