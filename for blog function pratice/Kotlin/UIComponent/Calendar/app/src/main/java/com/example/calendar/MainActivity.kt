package com.example.calendar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.text.set
import androidx.lifecycle.viewModelScope
import com.example.calendar.data.Data
import com.example.calendar.databinding.ActivityMainBinding
import com.example.calendar.room.Schedule
import com.example.calendar.room.ScheduleDao
import com.example.calendar.room.ScheduleDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var scheduleDao: ScheduleDao
    private lateinit var schedule: Flow<Schedule>

    private val scope = CoroutineScope(Dispatchers.Main)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()


        val date = binding.calendarView.date + 86400000

        

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            binding.tvDate.text = "${year}년 ${month+1}월 ${dayOfMonth}일"
            Log.v("22222222222","22222222222")

            //TODO : 데이터 들고오기
            loadData()
        }

        binding.btnSave.setOnClickListener {
            saveSchedule()
        }

        binding.btnDelete.setOnClickListener {
            binding.calendarView.date = date
        }

    }

    private fun init(){
        val database = ScheduleDataBase.getInstance(this)
        scheduleDao = database.scheduleDao()
        
    }

    private fun loadData(){
        val date = binding.tvDate.text.toString()
        scope.launch {
            scheduleDao.loadByDate(date).collect {
                binding.edtSchedule.setText("")
                it?.let {
                    binding.edtSchedule.setText(it.description)
                }
                Log.v("라앙ㄹ","111212121")

            }

        }
    }

    private fun saveSchedule(){
        val date = binding.tvDate.text.toString()
        val title = date
        val describe = binding.edtSchedule.text.toString()

        val schedule = Schedule(title, date, describe)

        scope.launch {
            scheduleDao.insertSchedule(schedule)
        }

    }
}