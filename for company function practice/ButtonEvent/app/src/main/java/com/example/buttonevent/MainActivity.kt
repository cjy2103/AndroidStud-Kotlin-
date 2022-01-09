package com.example.buttonevent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.buttonevent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        txtChange();
        imgChagne();

    }

    /**
     * @DESC: findViewByID를 이용한 텍스트 변경
     */
    fun txtChange(){
        var count = 0;
        var txtStr    = findViewById<TextView>(R.id.txt_str)
        var btnChange = findViewById<Button>(R.id.btn_change)

        btnChange.setOnClickListener {
            if(count==0) {
                var tempStr = "Change"
                txtStr.setText(tempStr)
                count++
            } else{
                var tempStr = "Hello World!"
                txtStr.setText(tempStr)
                count--
            }
        }
    }

    /**
     * @DESC:ViewBinding을 이용한 이미지뷰 변경
     */
    fun imgChagne(){
        var count = 0
        binding.btnImgChange.setOnClickListener {
            if(count==0) {
                binding.imgKakao.setImageResource(R.drawable.img_chun2)
                count++
            } else{
                binding.imgKakao.setImageResource(R.drawable.img_chun)
                count--
            }
        }
    }
}