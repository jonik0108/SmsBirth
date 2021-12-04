package com.abbasov.smsbirth

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.abbasov.smsbirth.Adapters.RvAdapter
import com.abbasov.smsbirth.Adapters.TimeAdapter
import com.abbasov.smsbirth.Adapters.UserAdapter
import com.abbasov.smsbirth.Db.AppDatabase
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.databinding.ActivityTeacherListBinding
import kotlinx.android.synthetic.main.item_rv.*

class TeacherList : AppCompatActivity() {
    lateinit var binding: ActivityTeacherListBinding
    lateinit var rvAdapter: RvAdapter
    lateinit var timeAdapter: TimeAdapter
    lateinit var userAdapter: UserAdapter
    lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTeacherListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()





    }

    override fun onResume() {
        super.onResume()
        val timelist=ArrayList<String>()
        val intent = intent
        val fNick = intent.getStringExtra("en")
        appDatabase = AppDatabase.getInstance(this)
        val list = ArrayList<Person>()
        list.addAll(appDatabase.personDao().getAllPerson())
        val list2 = ArrayList<Person>()
        for (p in list) {
            if (p.group==fNick.toString()){
                list2.add(p)
                timelist.add(p.time.toString())
            }
        }
        val set: Set<String> = HashSet(timelist)
        timelist.clear()
        timelist.addAll(set)
        val tlist=ArrayList<Person2>()
        for (s in timelist) {
            tlist.add(Person2(s))
        }


        timeAdapter = TimeAdapter(this,object : TimeAdapter.onClck{
            override fun onTimeClik(person2: Person2, position: Int) {
                val intent2 = Intent(this@TeacherList,Teacherlist2::class.java)
                intent2.putExtra("kn",fNick)
                intent2.putExtra("an", "${person2.time}") //second param is Serializable
                startActivity(intent2)
            }
        },tlist)
        /*if (fNick!=null) {
            rvAdapter = RvAdapter(this, object : RvAdapter.Click {
                override fun onClick(person: Person, position: Int) {
                    val dialog = AlertDialog.Builder(this@TeacherList)

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
        }else{
            rvAdapter = RvAdapter(this, object : RvAdapter.Click {
                override fun onClick(person: Person, position: Int) {
                    val dialog = AlertDialog.Builder(this@TeacherList)

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
            }, list)
        }*/
       binding.rv.adapter = timeAdapter
    }
}