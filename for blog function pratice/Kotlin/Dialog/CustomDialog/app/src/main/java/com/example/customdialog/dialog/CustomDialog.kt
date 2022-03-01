package com.example.customdialog.dialog

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.customdialog.MainActivity
import com.example.customdialog.databinding.CustomDialogBinding

class CustomDialog : DialogFragment() {

    private var mBinding : CustomDialogBinding? = null
    private val binding get() = mBinding!!
    private lateinit var mContext : Context
    private lateinit var mActivity : Activity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
        mActivity = context as Activity
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return initBinding(inflater,container)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initFontSetting()

        dialogSetting()

        clickEvent()

    }

    /**
     * @DESC: 초기 바인딩
     */
    private fun initBinding(inflater: LayoutInflater, container: ViewGroup?): View {
        mBinding = CustomDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    /**
     * @DESC: 초기 폰트 세팅
     */
    private fun initFontSetting(){
        val tfMapleBold     = Typeface.createFromAsset(mContext.assets, "Maplestory Bold.ttf")
        val tfMapleLight    = Typeface.createFromAsset(mContext.assets, "Maplestory Light.ttf")

        binding.tvTitle.typeface = tfMapleBold
        binding.tvMessage.typeface = tfMapleLight
    }

    /**
     * @DESC: 다이얼로그 옵션 세팅
     */
    private fun dialogSetting(){
        isCancelable = false
        if(requireNotNull(dialog?.window !=null)){
            dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog?.window?.setDimAmount(0.2f)
        }
    }

    /**
     * @DESC: 클릭 이벤트 모음
     */
    private fun clickEvent(){
        clickClose()

        clickCancel()

        clickOK()
    }

    /**
     * @DESC: X 버튼 클릭
     */
    private fun clickClose(){
        binding.ibClose.setOnClickListener {
            dismiss()
        }
    }

    /**
     * @DESC: Cancel 클릭
     */
    private fun clickCancel(){
        binding.tvCancel.setOnClickListener {
            dismiss()
        }
    }

    /**
     * @DESC: OK 클릭
     */
    private fun clickOK(){
        binding.tvOk.setOnClickListener {
            dismiss()
            mActivity.finish()
        }
    }

}