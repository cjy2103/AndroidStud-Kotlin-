package com.example.listviewitemsearch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.ArrayAdapter
import com.example.listviewitemsearch.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var list : ArrayList<String>
    private lateinit var listCopy : ArrayList<String>
    private lateinit var adapter : ArrayAdapter<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()

        initialize()

        listAdd()

        listConnetion()

        wordInput();

    }

    /**
     * @DESC: 초기 바인딩
     */
    private fun initBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * @DESC: 초기화
     */
    private fun initialize(){
        list = ArrayList()
        listCopy = ArrayList()
    }

    /**
     * @DESC: 리스트 뷰 아이템 추가
     */
    private fun listAdd(){
        list.add("사과")
        list.add("바나나")
        list.add("키위")
        list.add("수박")
        list.add("망고")
        list.add("사과")
        list.add("바나나")
        list.add("키위")
        list.add("수박")
        list.add("망고")
        list.add("사과")
        list.add("바나나")
        list.add("키위")
        list.add("수박")
        list.add("망고")
    }

    /**
     * @DESC: 어댑터 연결
     */
    private fun listConnetion(){
        listCopy.addAll(list)
        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,list)
        binding.listItem.adapter = adapter
    }

    /**
     * @DESC: 단어입력
     */
    private fun wordInput(){
        binding.edtInput.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {
                val str = binding.edtInput.text.toString()
                filterWord(str)
            }

            override fun afterTextChanged(p0: Editable?) {
            }

        })
    }

    /**
     * @DESC: 리스트 필터
     */
    private fun filterWord(str: String) {
        list.clear()

        if(str.isEmpty()){
            list.addAll(listCopy)
        } else {
            for(word in listCopy){
                if(word.contains(str)) {
                    list.add(word)
                }
            }
        }

        adapter.notifyDataSetChanged()

    }

}