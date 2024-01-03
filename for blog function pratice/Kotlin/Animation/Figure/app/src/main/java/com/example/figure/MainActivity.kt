package com.example.figure

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.figure.databinding.ActivityMainBinding
import com.example.figure.paint.FigureView

class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private lateinit var figureView: FigureView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        figureView = FigureView(this)

        binding.constraint.addView(figureView)

        clickCircle()
        clickSquare()
        clickTriangle()
    }

    private fun clickCircle(){
        binding.btnCircle.setOnClickListener {
            figureView.clickBtn(1)
            figureView.invalidate()
        }
    }

    private fun clickSquare(){
        binding.btnSquare.setOnClickListener {
            figureView.clickBtn(2)
            figureView.invalidate()
        }
    }

    private fun clickTriangle(){
        binding.btnTriangle.setOnClickListener {
            figureView.clickBtn(3)
            figureView.invalidate()

        }
    }
}