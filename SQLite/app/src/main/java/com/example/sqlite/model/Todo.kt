package com.example.sqlite.model

data class Todo(val task:String,val done:Boolean){
    override fun toString(): String {
        return task
    }
}