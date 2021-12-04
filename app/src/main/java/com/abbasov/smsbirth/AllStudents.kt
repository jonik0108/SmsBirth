package com.abbasov.smsbirth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.abbasov.smsbirth.Adapters.RvAdapter
import com.abbasov.smsbirth.Db.AppDatabase
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.databinding.ActivityAllStudentsBinding
import com.abbasov.smsbirth.databinding.ActivityTeacherListBinding
import com.abbasov.smsbirth.databinding.ActivityTeacherlist2Binding

class AllStudents : AppCompatActivity() {
    lateinit var binding: ActivityAllStudentsBinding
    lateinit var rvAdapter: RvAdapter
    lateinit var appDatabase: AppDatabase
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllStudentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        appDatabase = AppDatabase.getInstance(this)
        val list = ArrayList<Person>()
        list.addAll(appDatabase.personDao().getAllPerson())
        rvAdapter = RvAdapter(this, object : RvAdapter.Click {
            override fun onClick(person: Person, position: Int) {
                val dialog = AlertDialog.Builder(this@AllStudents)

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

        binding.rv.adapter = rvAdapter
    }
}