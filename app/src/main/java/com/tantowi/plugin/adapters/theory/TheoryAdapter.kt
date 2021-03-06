package com.tantowi.plugin.adapters.theory

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tantowi.plugin.R
import com.tantowi.plugin.models.Theory
import kotlinx.android.synthetic.main.list_item_theory.view.*

class TheoryAdapter (private var theories: List<Theory>, private var context : Context) : RecyclerView.Adapter<TheoryAdapter.MyHolder>(){


    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(theory : Theory, context: Context){
            itemView.tv_gathering_event.text = theory.gathering
            itemView.tv_description_event.text = theory.description
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(LayoutInflater.from(context).inflate(R.layout.list_item_theory, parent, false))
    }

    override fun getItemCount(): Int = theories.size

    override fun onBindViewHolder(holder: MyHolder, position: Int) = holder.bind(theories[position], context)
}