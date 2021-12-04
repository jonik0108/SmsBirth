package com.abbasov.smsbirth.service

import android.annotation.SuppressLint
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.Handler
import android.os.IBinder
import android.telephony.SmsManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.abbasov.smsbirth.Db.AppDatabase
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.MainActivity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class BackSer:Service() {
    private lateinit var mHandler: Handler
    var runnable: Runnable? = null
    lateinit var appDatabase: AppDatabase
    var text=""
    var a=0
    var b=true
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        Toast.makeText(applicationContext, "start", Toast.LENGTH_SHORT).show()
        return super.onStartCommand(intent, flags, startId)
    }
    @SuppressLint("SimpleDateFormat")
    override fun onStart(intent: Intent?, startId: Int) {
        super.onStart(intent, startId)
        appDatabase = AppDatabase.getInstance(this)
        val list = ArrayList<Person>()
        val date= SimpleDateFormat("MMdd").format(Date())
        list.addAll(appDatabase.personDao().getAllPerson())

        mHandler = Handler()
        runnable = Runnable {

            for (person in list) {
                if (person.data==date && a==0){
                    text="Hello ${person.name}"
                    SmsManager.getDefault()
                        .sendTextMessage(
                            person.number.toString(),
                            null,
                            text,
                            null,
                            null
                        )

                }
            }
            a++
            if (b==false){
                Toast.makeText(applicationContext, "finish", Toast.LENGTH_SHORT).show()
            }else{
                mHandler.postDelayed(runnable!!, 1000)
            }
        
            }
        mHandler.postDelayed(runnable!!, 5000)
    }
    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    override fun onDestroy() {
        super.onDestroy()
        b=false

    }

}