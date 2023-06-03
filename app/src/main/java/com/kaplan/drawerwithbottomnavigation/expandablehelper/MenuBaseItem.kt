package com.kaplan.drawerwithbottomnavigation.expandablehelper

/* Base class for multi level menu*/

open class MenuBaseItem {
    var name: String
    var icon = 0
    var id = 0


    constructor(mName: String, icon: Int, id: Int) {
        name = mName
        this.icon = icon
        this.id = id
    }

    constructor(name: String, id: Int) {
        this.name = name
        this.id = id
    }
}