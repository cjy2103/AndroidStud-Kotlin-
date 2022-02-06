package com.example.storagepermissiontest

import android.content.SharedPreferences
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.example.storagepermissiontest.databinding.ActivityMainBinding
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!
    private var permissionState = false
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initBinding()
        
        initialize()

        storagePermissionCheck()

        clickStoragePermission()
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
        sharedPreferences = this.getSharedPreferences("StoragePermission", MODE_PRIVATE)
    }

    /**
     * @DESC: 저장소 권한 확인
     */
    private fun storagePermissionCheck(){
        val writePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
        val readPermission  = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)

        if(writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED){
            binding.tvState.text = "저장소 상태 : 권한 부여되지 않음";
        } else {
            binding.tvState.text = "저장소 상태 : 권한 부여됨"
        }
    }

    /**
     * @DESC: 저장소 권한 요청
     */
    private fun clickStoragePermission(){
        binding.btnStoragePermission.setOnClickListener{
            permissionState = sharedPreferences.getBoolean("PermissionState",true)

            if(permissionState){
                val writePermission = ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                val readPermission  = ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                if(writePermission != PackageManager.PERMISSION_GRANTED || readPermission != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(
                        arrayOf(android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            android.Manifest.permission.READ_EXTERNAL_STORAGE),2002)
                } else {
                    Toast.makeText(this,"이미 권한이 부여됨",Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this,"권한이 거절되어 있음",Toast.LENGTH_SHORT).show()
            }
        }
    }

    /**
     * @DESC: 권한요청 콜백
     */
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 2002){
            var checkResult = true

            for(result in grantResults){
                if(result != PackageManager.PERMISSION_GRANTED) {
                    checkResult = false
                    break
                }
            }

            if(checkResult){
                binding.tvState.text = "저장소 상태 : 권한 부여됨"
            } else {
                var editor = sharedPreferences.edit()
                editor.putBoolean("PermissionState",false)
                editor.apply()

                Toast.makeText(this,"권한부여거절",Toast.LENGTH_SHORT).show()
            }
        }

    }
}