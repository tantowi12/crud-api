package com.tantowi.plugin.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.tantowi.plugin.R
import com.tantowi.plugin.detail_squad
import com.tantowi.plugin.edit_squad
import com.tantowi.plugin.models.Post
import kotlinx.android.synthetic.main.list_item.view.*

class PostAdapter (private var posts : List<Post>, private var context: Context) :
        RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_item, parent, false))
    }

    override fun getItemCount() = posts.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(posts[position],context)

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        fun bind(post : Post, context: Context) {
            itemView.tv_IdAdd.text = "Id :  " + post.id
            itemView.tv_SquadAdd.text = "Squad :  " + post.squads_name
            itemView.tv_DescAdd.text = "Description :  " + post.description

//            itemView.setOnClickListener {
//                context.startActivity(Intent(context, edit_squad::class.java).apply {
//                    putExtra("IS_NEW", false)
//                    putExtra("POSTID", post.id)
//                    putExtra("POSTSquad", post.squad)
//                    putExtra("POSTDESC", post.description)
//                })
//            }

            itemView.setOnClickListener {
                context.startActivity(Intent(context,detail_squad::class.java).apply {
                    putExtra("post" , post)
                })
            }
        }
    }
}