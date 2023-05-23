package com.omaradev.jetnote.routing


sealed class Screen(var routing :String){
    object Notes: Screen("Notes")

    object SaveNote: Screen("SaveNote")

    object Trash: Screen("Trash")
}