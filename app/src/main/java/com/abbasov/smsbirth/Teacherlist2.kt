package com.abbasov.smsbirth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.abbasov.smsbirth.Adapters.RvAdapter
import com.abbasov.smsbirth.Adapters.TimeAdapter
import com.abbasov.smsbirth.Db.AppDatabase
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.databinding.ActivityTeacherListBinding
import com.abbasov.smsbirth.databinding.ActivityTeacherlist2Binding

class Teacherlist2 : AppCompatActivity() {
    lateinit var binding: ActivityTeacherlist2Binding
    lateinit var rvAdapter: RvAdapter
    lateinit var appDatabase: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeacherlist2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()



    }
    override fun onResume() {
        super.onResume()
        val intent = intent
        val fTime = intent.getStringExtra("an")
        val fNick = intent.getStringExtra("kn")
        appDatabase = AppDatabase.getInstance(this)
        val list = ArrayList<Person>()
        val list2 = ArrayList<Person>()
        list.addAll(appDatabase.personDao().getAllPerson())
        for (p in list) {
            if (p.group==fNick && p.time==fTime){
                list2.add(p)
            }
        }

        rvAdapter = RvAdapter(this, object : RvAdapter.Click {
                override fun onClick(person: Person, position: Int) {
                    val dialog = AlertDialog.Builder(this@Teacherlist2)

                    dialog.setTitle("вы точно хотите удалить?")
                    dialog.setCancelable(false)
                    dialog.setPositiveButton(
                        "Yes"
                    ) { _, _ ->
                        appDatabase.personDao().deletePerson(person)
                        onResume()
                    }
                    dialog.setNeutralButton(
                        "No"
                    ) { _, _ ->
                        onResume()
                    }
                    dialog.show()

                }

                override fun onClick2(person: Person, position: Int) {

                }
            }, list2)

        binding.rv.adapter = rvAdapter
        }
    }

