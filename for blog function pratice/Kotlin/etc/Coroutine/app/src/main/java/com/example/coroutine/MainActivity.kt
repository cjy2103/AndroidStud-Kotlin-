package com.example.coroutine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.coroutine.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity(), CoroutineScope by MainScope() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding()

        executeTask()

    }

    private fun viewBinding(){
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun executeTask(){
        binding.btnExecute.setOnClickListener {
//            val coroutineScope = CoroutineScope(Dispatchers.Main)

            val backgroundWork = suspend {
                delay(2000)
                "Coroutine Work"
            }

//            coroutineScope.launch(Dispatchers.IO){
//                val result = backgroundWork()
//                withContext(Dispatchers.Main){
//                    binding.tvTest.text = result
//                }
//            }

//            launch(Dispatchers.IO){
//                val result = backgroundWork()
//                withContext(Dispatchers.Main){
//                    binding.tvTest.text = result
//                }
//            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        cancel() // 코루틴 스코프 종료
    }
}