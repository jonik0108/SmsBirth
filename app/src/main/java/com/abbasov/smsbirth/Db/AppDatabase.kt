package com.abbasov.smsbirth.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abbasov.smsbirth.Dao.PersonDao
import com.abbasov.smsbirth.Entity.Person

@Database(entities = [Person::class], version = 1)
abstract class AppDatabase : RoomDatabase(){

    abstract fun personDao(): PersonDao


    companion object{
        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null){
                instance = Room.databaseBuilder(context, AppDatabase::class.java, "db_person")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
            }
            return instance!!
        }
    }
}