package com.example.bluemarket.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bluemarket.R


class CatAdapter(val catList: List<String>) : RecyclerView.Adapter<CatAdapter.CatViewHolder>() {

    var onCatItemClickListener: OnCatItemClickListener ?= null

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CatViewHolder {
        return CatViewHolder(LayoutInflater.from(p0.context).inflate(R.layout.cat_item, p0, false))
    }

    override fun onBindViewHolder(p0: CatViewHolder, p1: Int) {
        p0.txtCatName.text = catList[p1]
        p0.itemView.setOnClickListener {
            onCatItemClickListener?.onCatItemClick(catList[p1])
        }
    }

    fun setCatListener(onCatItemClickListener: OnCatItemClickListener) {
        this.onCatItemClickListener = onCatItemClickListener
    }

    override fun getItemCount(): Int = catList.size

    class CatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val txtCatName = itemView.findViewById<TextView>(R.id.txt_cat_name)
    }

    interface OnCatItemClickListener {
        fun onCatItemClick(catName: String)
    }
}



