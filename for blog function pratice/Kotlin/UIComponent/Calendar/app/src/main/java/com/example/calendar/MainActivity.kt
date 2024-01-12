package com.example.calendar

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.calendar.databinding.ActivityMainBinding
import com.example.calendar.room.Schedule
import com.example.calendar.room.ScheduleDao
import com.example.calendar.room.ScheduleDataBase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale


class MainActivity : AppCompatActivity() {

    private var mBinding : ActivityMainBinding? = null
    private val binding get() = mBinding!!

    private lateinit var scheduleDao: ScheduleDao

    private val scope = CoroutineScope(Dispatchers.Main)

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        init()

        val currentDate = binding.calendarView.date

        val dateFormat = SimpleDateFormat("yyyy년 M월 dd일", Locale.getDefault())

        val formattedDate = dateFormat.format(currentDate)

        binding.tvDate.text = formattedDate

        loadData()

        binding.calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            binding.tvDate.text = "${year}년 ${month+1}월 ${dayOfMonth}일"

            loadData()
        }

        binding.btnSave.setOnClickListener {
            saveSchedule()
        }

        binding.btnDelete.setOnClickListener {
            deleteSchedule()
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
                    binding.edtSchedule.setSelection(binding.edtSchedule.text.length)
                }
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
            binding.edtSchedule.setSelection(binding.edtSchedule.text.length)

        }

    }

    private fun deleteSchedule(){
        scope.launch {
            val date = binding.tvDate.text.toString()
            scheduleDao.deleteByDate(date)
            binding.edtSchedule.setText("")
        }
    }
}