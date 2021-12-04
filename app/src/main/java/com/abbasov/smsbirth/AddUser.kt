package com.abbasov.smsbirth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.abbasov.smsbirth.Adapters.RvAdapter
import com.abbasov.smsbirth.Adapters.UserAdapter
import com.abbasov.smsbirth.Db.AppDatabase
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.databinding.ActivityAddUserBinding
import kotlinx.android.synthetic.main.activity_add_user.*


class AddUser : AppCompatActivity() {
    lateinit var appDatabase: AppDatabase
    lateinit var rvAdapter: RvAdapter
    lateinit var userAdapter: UserAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_user)
        supportActionBar?.hide()

        appDatabase = AppDatabase.getInstance(this)

        btn_close.setOnClickListener {
            val selected = spinner.selectedItem.toString()

            val person = Person()
            person.name = name.text.toString()
            person.number = number.text.toString()
            person.data = data.text.toString()
            person.time = time1.text.toString()
            person.group= selected
            if (name.text.toString()=="" && number.text.toString()=="" && data.text.toString()==""){
                Toast.makeText(this, "Не все поля заполнены", Toast.LENGTH_SHORT).show()
            }else {
                appDatabase.personDao().addPerson(person)
                Toast.makeText(this, "save", Toast.LENGTH_SHORT).show()
                onResume()
            }
        }




    }

    override fun onResume() {
        super.onResume()
        val list = ArrayList<Person>()
        val list2 = ArrayList<Person>()
        list.addAll(appDatabase.personDao().getAllPerson())
        if (list.size==0){

        }else{
            list2.clear()
            list2.add(list.last())
        }
        rvAdapter = RvAdapter(this, object : RvAdapter.Click {
            override fun onClick(person: Person, position: Int) {
                val dialog = AlertDialog.Builder(this@AddUser)
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
        rv.adapter = rvAdapter

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}