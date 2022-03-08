package com.example.customlistview.listView

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.bumptech.glide.Glide
import com.example.customlistviewdetail.databinding.CustomListviewBinding

class ListViewAdapter : BaseAdapter() {
    private val listViewItems : ArrayList<ListViewItem> = ArrayList()

    /**
     * @DESC: Adapter에 사용되는 데이터의 개수
     */
    override fun getCount(): Int {
        return listViewItems.size
    }

    /**
     * @DESC: 저장한 위치에 있는 데이터
     */
    override fun getItem(position: Int): Any {
        return listViewItems[position]
    }

    /**
     * @DESC: 지정한 위치에 있는 데이터와 관계된 아이템의 ID를 리턴
     */
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    /**
     * @DESC: position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴
     */
    override fun getView(position: Int, view: View?, viewGroup: ViewGroup?): View {
        val context = viewGroup!!.context
        val binding: CustomListviewBinding?
        var convertView = view

        if(view == null){
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            binding = CustomListviewBinding.inflate(inflater, viewGroup, false)
            convertView = binding.root
        } else {
            binding = CustomListviewBinding.bind(convertView!!)
        }

        val listViewItem = listViewItems[position]

        val image = Uri.parse("android.resource://" + context.packageName + "/" + listViewItem.image)

        Glide.with(context).load(image).into(binding.imageView)
//        binding.imageView.setImageDrawable(listViewItem.image)
        binding.tvTitle.text = listViewItem.title
        binding.tvDescribe.text = listViewItem.describe

        return convertView
    }

    /**
     * @DESC: 아이템 테이터 추가
     */
    fun addItem(parmImage : String, parmTitle : String, parmDescribe : String
              , parmYouTubeLink : String){
        val item = ListViewItem()

        item.image          = parmImage
        item.title          = parmTitle
        item.describe       = parmDescribe
        item.youtubeLink    = parmYouTubeLink

        listViewItems.add(item)
    }

    /**
     * @DESC: 아이템 초기화
     */
    fun clearItem(){
        listViewItems.clear()
    }
}