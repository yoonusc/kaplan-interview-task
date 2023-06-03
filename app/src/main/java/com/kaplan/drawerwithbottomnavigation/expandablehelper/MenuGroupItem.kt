package com.kaplan.drawerwithbottomnavigation.expandablehelper

class MenuGroupItem : MenuBaseItem {
    var level: Int

    constructor(mName: String?, icon: Int, id: Int) : super(mName!!, icon, id) {
        level = 0
    }

    constructor(name: String?, id: Int) : super(name!!, id) {
        level = 0
    }
}