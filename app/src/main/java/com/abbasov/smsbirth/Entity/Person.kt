package com.abbasov.smsbirth.Entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class Person {
    @PrimaryKey(autoGenerate = true)
    var id:Int? = null

    @ColumnInfo(name="ismlar")
    var name:String? = null
    var number:String? = null
    var data:String? = null
    var group:String? = null
    var nick:String? = null
    var time:String? = null

    constructor(name: String?, number: String?, nick: String?) {
        this.name = name
        this.number = number
        this.nick = nick
    }

    constructor()

}