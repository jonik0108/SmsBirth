package com.abbasov.smsbirth

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.abbasov.smsbirth.Adapters.RvAdapter
import com.abbasov.smsbirth.Adapters.UserAdapter
import com.abbasov.smsbirth.Db.AppDatabase
import com.abbasov.smsbirth.Entity.Person
import com.abbasov.smsbirth.databinding.ActivityMainBinding
import com.abbasov.smsbirth.databinding.ActivityMainBinding.inflate
import com.github.florent37.runtimepermission.kotlin.askPermission
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_custom_dialog.*
import kotlinx.android.synthetic.main.item_custom_dialog.view.*
import kotlinx.android.synthetic.main.item_rv2.*





class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var rvAdapter: RvAdapter
    lateinit var userAdapter: UserAdapter
    lateinit var appDatabase: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        myMethod()

        binding.add.setOnClickListener {
            startActivity(Intent(this,AddUser::class.java))

        }
        binding.all.setOnClickListener {
            startActivity(Intent(this,AllStudents::class.java))

        }

    }

    private fun myMethod() {
        askPermission(Manifest.permission.SEND_SMS) {
            //all permissions already granted or just granted

        }.onDeclined { e ->
            if (e.hasDenied()) {

                AlertDialog.Builder(this)
                    .setMessage("Please accept our permissions")
                    .setPositiveButton("yes") { dialog, which ->
                        e.askAgain();
                    } //ask again
                    .setNegativeButton("no") { dialog, which ->
                        dialog.dismiss();
                    }
                    .show();
            }

            if (e.hasForeverDenied()) {
                //the list of forever denied permissions, user has check 'never ask again'

                // you need to open setting manually if you really need it
                e.goToSettings();
            }
        }

    }

    override fun onResume() {
        super.onResume()
        appDatabase = AppDatabase.getInstance(this)
        val list = ArrayList<Person>()
        val list2 = ArrayList<Person>()
        list2.add(Person("Музаффар Хомидов",        "97 966 47 77",   "Mr.Merle"))
        list2.add(Person("Жасур Обиджанов",         "99 051 12 36",   "Mr.Justin"))
        list2.add(Person("Ойбек Мирхужаев",         "93 734 78 02",   "Mr.James"))
        list2.add(Person("Хайдарбек Таваккалжонов", "90 300 10 39",   "Mr.Henry"))
        list2.add(Person("Диера Нишанова",          "99 921 29 09",   "Ms.Dori"))
        list2.add(Person("Бегойим Сатторова",       "91 158 80 18",   "Ms.Bbegi"))
        list2.add(Person("Диера Салохиддинова",     "90 277 77 05",   "Ms.Diyora"))
        userAdapter = UserAdapter(this,object : UserAdapter.onClick2 {
            override fun Click2(person: Person, position: Int) {
                val intent = Intent(this@MainActivity,TeacherList::class.java)
                intent.putExtra("en", "${list2[position].nick}") //second param is Serializable
                startActivity(intent)
            }
        },list2)
        rv.adapter = userAdapter


        list.addAll(appDatabase.personDao().getAllPerson())

        binding.count.text = list.size.toString()

    }
}