package com.kaplan.drawerwithbottomnavigation.expandablehelper

import android.app.Activity
import android.content.Context
import com.kaplan.drawerwithbottomnavigation.R

/* Menu generator class */
class MenuProvider {
    var context: Context? = null

    companion object {
        private const val MAX_LEVELS = 3
        private const val LEVEL_1 = 1
        private const val LEVEL_2 = 2
        private const val LEVEL_3 = 3

        const val MENU_HOME = 1
        const val MENU_CATEGORY = 2
        const val MENU_FAVORITES = 3
        const val MENU_ABOUT = 4
        const val MENU_ACCOUNT = 5
        const val MENU_FASHION = 6
        const val MENU_ELECTRONICS = 7
        const val MENU_SPORTS = 8
        const val MENU_FAVORITE_1 = 9
        const val MENU_FAVORITE_2 = 10

        private val mMenu: List<MenuBaseItem> = ArrayList()

        fun getRootMenu(context: Activity): List<MenuBaseItem> {

            val rootMenu: MutableList<MenuBaseItem> = ArrayList()
            rootMenu.add(MenuItem(context.getString(R.string.home), R.drawable.ic_home, MENU_HOME))
            rootMenu.add(
                MenuGroupItem(
                    context.getString(R.string.category),
                    R.drawable.ic_category,
                    MENU_CATEGORY
                )
            )
            rootMenu.add(
                MenuGroupItem(
                    context.getString(R.string.favorites),
                    R.drawable.ic_baseline_favorite_24,
                    MENU_FAVORITES
                )
            )

            rootMenu.add(
                MenuItem(
                    context.getString(R.string.account),
                    R.drawable.ic_baseline_account_circle_24,
                    MENU_ACCOUNT
                )
            )

            rootMenu.add(
                MenuItem(
                    context.getString(R.string.about_us),
                    R.drawable.ic_baseline_info_24,
                    MENU_ABOUT
                )
            )
            return rootMenu
        }

        fun getSubItems(baseItem: MenuBaseItem, context: Activity): List<MenuBaseItem?>? {
            var result: List<MenuBaseItem?> = ArrayList()
            val level = (baseItem as MenuGroupItem).level + 1
            val menuItem = baseItem.id
            if (baseItem.level >= MAX_LEVELS) {
                return null
            }
            if (level == LEVEL_1) {
                when (menuItem) {
                    MENU_CATEGORY -> result = getSubCategory(context)
                    MENU_FAVORITES -> result = getFavorites(context)
                }
            }
            return result
        }

        fun isExpandable(baseItem: MenuBaseItem?): Boolean {
            return baseItem is MenuGroupItem
        }

        private fun getSubCategory(context: Activity): List<MenuBaseItem?> {
            val categories: MutableList<MenuBaseItem?> = ArrayList()
            categories.add(MenuItem(context.getString(R.string.category_fashion), MENU_ELECTRONICS))
            categories.add(MenuItem(context.getString(R.string.Electronics), MENU_FASHION))
            categories.add(MenuItem(context.getString(R.string.category_sports), MENU_SPORTS))
            return categories
        }

        private fun getFavorites(context: Activity): List<MenuBaseItem?> {
            val favorites: MutableList<MenuBaseItem?> = ArrayList()
            favorites.add(MenuItem(context.getString(R.string.category_fashion), MENU_FAVORITE_1))
            favorites.add(
                MenuItem(
                    context.getString(R.string.favorites_electronics),
                    MENU_FAVORITE_2
                )
            )
            return favorites
        }

    }
}