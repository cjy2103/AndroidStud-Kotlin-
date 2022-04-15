package com.example.customrecyclerview.adapter

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.customrecyclerview.model.MyListItem
import com.example.customrecyclerviewdetail.R
import com.example.customrecyclerviewdetail.databinding.RecyclerViewListBinding
import kotlin.properties.Delegates

class CustomRecyclerAdapter() : RecyclerView.Adapter<CustomRecyclerAdapter.ViewHolder>() {

    private lateinit var context: Context
    private lateinit var activity : Activity
    private lateinit var myListItem: ArrayList<MyListItem>
    private lateinit var searchIndex: ArrayList<Int>
    private var size by Delegates.notNull<Int>()
    private lateinit var word: String

    constructor(context: Context, activity: Activity, myListItem: ArrayList<MyListItem>, size: Int, word : String) : this(){
        this.context = context
        this.activity = activity
        this.myListItem = myListItem
        this.size = size
        this.word = word
    }

    constructor(context: Context, activity: Activity, myListItem: ArrayList<MyListItem>, searchIndex: ArrayList<Int>, size: Int, word: String) :
    this(){
        this.context = context
        this.activity = activity
        this.myListItem = myListItem
        this.searchIndex = searchIndex
        this.size = size
        this.word = word
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if(word == ""){
            wordEmpty(holder, position)
        } else {
            notWordEmpty(holder, position)
        }
    }

    override fun getItemCount(): Int {
        return size
    }

    /**
     * @DESC : EditText 가 비었을 때
     */
    private fun wordEmpty(holder: ViewHolder, position: Int){
        val imagePath = Uri.parse(
            "android.resource://" + activity.packageName + "/"
                    + myListItem[position].list[0].uri)
        Glide.with(context).load(imagePath).into(holder.binding.ivArt)
        holder.binding.tvTitle.text = myListItem[position].list[0].title
        holder.binding.tvDescribe.text = myListItem[position].list[0].describe
    }

    /**
     * @DESC: EditText에 단어가 있을 때
     */
    private fun notWordEmpty(holder : ViewHolder, position: Int){
        val imagePath = Uri.parse(
            "android.resource://" + activity.packageName + "/"
                    + myListItem[searchIndex[position]].list[0].uri)
        Glide.with(context).load(imagePath).into(holder.binding.ivArt)
        holder.binding.tvTitle.text = myListItem[searchIndex[position]].list[0].title
        holder.binding.tvDescribe.text = myListItem[searchIndex[position]].list[0].describe
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var mBinding: RecyclerViewListBinding? = null
        val binding get() = mBinding!!
        private fun initBinding() {
            mBinding = RecyclerViewListBinding.bind(itemView)
        }

        init {
            initBinding()
        }
    }
}