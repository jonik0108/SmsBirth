package com.abbasov.smsbirth

class Person2 {
    var time:String?=null

    constructor()
    constructor(time: String?) {
        this.time = time
    }

    override fun toString(): String {
        return "Person2(time=$time)"
    }

}