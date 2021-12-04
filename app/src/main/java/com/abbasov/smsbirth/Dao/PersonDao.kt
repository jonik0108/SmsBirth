package com.abbasov.smsbirth.Dao

import androidx.room.*
import com.abbasov.smsbirth.Entity.Person

@Dao
interface PersonDao {

    @Query("select * from person")
    fun getAllPerson():List<Person>

    @Insert
    fun addPerson(person: Person)

    @Delete
    fun deletePerson(person: Person)

    @Update
    fun editPerson(person: Person)

}