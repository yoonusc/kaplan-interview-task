package com.kaplan.drawerwithbottomnavigation.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.kaplan.drawerwithbottomnavigation.R
import com.kaplan.drawerwithbottomnavigation.databinding.ActivityMainBinding
import com.kaplan.drawerwithbottomnavigation.expandablehelper.MenuBaseItem
import com.kaplan.drawerwithbottomnavigation.expandablehelper.MenuListAdapter
import com.kaplan.drawerwithbottomnavigation.expandablehelper.MenuProvider
import pl.openrnd.multilevellistview.ItemInfo
import pl.openrnd.multilevellistview.MultiLevelListView
import pl.openrnd.multilevellistview.OnItemClickListener


class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var navController: NavController
    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        navController = findNavController(R.id.main_nav_host) //Initialising navController

        appBarConfiguration = AppBarConfiguration.Builder(
            R.id.homeFragment, R.id.accountsFragment,
            R.id.aboutFragment, R.id.categoryFragment, R.id.favoritesFragment
        ) //Pass the ids of fragments from nav_graph which you d'ont want to show back button in toolbar
            .setOpenableLayout(binding.mainDrawerLayout) //Pass the drawer layout id from activity xml
            .build()

        setSupportActionBar(binding.mainToolbar) //Set toolbar

        setupActionBarWithNavController(
            navController,
            appBarConfiguration
        ) //Setup toolbar with back button and drawer icon according to appBarConfiguration

        visibilityNavElements(navController) //If you want to hide drawer or bottom navigation configure that in this function
        prepareNavigationDrawer()
    }


    private fun prepareNavigationDrawer() {
        val menuListAdapter = MenuListAdapter(this)
        binding.multiNavView.setAdapter(menuListAdapter)
        binding.multiNavView.setOnItemClickListener(mOnItemClickListener)
        menuListAdapter.setDataItems(MenuProvider.getRootMenu(this))
    }

    private val mOnItemClickListener: OnItemClickListener = object : OnItemClickListener {
        private fun showItemDescription(menu: Any, itemInfo: ItemInfo) {
            //displaySelectedScreen()
            when ((menu as MenuBaseItem).id) {
                MenuProvider.MENU_HOME -> {
                    navController.navigate(R.id.homeFragment)
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                }
                MenuProvider.MENU_FASHION, MenuProvider.MENU_SPORTS, MenuProvider.MENU_ELECTRONICS -> {
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.categoryFragment)
                }
                MenuProvider.MENU_FAVORITE_1, MenuProvider.MENU_FAVORITE_2 -> {
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.favoritesFragment)
                }
                MenuProvider.MENU_ABOUT -> {
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.aboutFragment)
                }
                MenuProvider.MENU_ACCOUNT -> {
                    binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
                    navController.navigate(R.id.accountsFragment)
                }

            }

        }

        override fun onItemClicked(
            parent: MultiLevelListView,
            view: View,
            item: Any,
            itemInfo: ItemInfo
        ) {
            showItemDescription(item, itemInfo)
        }

        override fun onGroupItemClicked(
            parent: MultiLevelListView,
            view: View,
            item: Any,
            itemInfo: ItemInfo
        ) {
            showItemDescription(item, itemInfo)
        }
    }

    private fun visibilityNavElements(navController: NavController) {

        //Listen for the change in fragment (navigation) and hide or show drawer or bottom navigation accordingly if required
        //Modify this according to your need
        //If you want you can implement logic to deselect(styling) the bottom navigation menu item when drawer item selected using listener
        showBothNavigation()
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.categoryFragment -> {
                    binding.toolbarTitle.text = getString(R.string.category)


                }
                R.id.homeFragment -> {
                    binding.toolbarTitle.text = getString(R.string.home)

                }
                R.id.favoritesFragment -> {
                    binding.toolbarTitle.text = getString(R.string.favorites)

                }
                R.id.aboutFragment -> {
                    binding.toolbarTitle.text = getString(R.string.about_us)

                }

                R.id.accountsFragment -> {
                    binding.toolbarTitle.text = getString(R.string.account)
                    showBothNavigation()
                }

            }
        }

    }

    private fun hideBothNavigation() { //Hide both drawer and bottom navigation bar
        binding.mainBottomNavigationView.visibility = View.GONE
        binding.navView.visibility = View.GONE
        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED) //To lock navigation drawer so that it don't respond to swipe gesture
    }

    private fun hideBottomNavigation() { //Hide bottom navigation
        binding.mainBottomNavigationView.visibility = View.GONE
        binding.navView.visibility = View.VISIBLE
        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED) //To unlock navigation drawer
        binding.navView.setupWithNavController(navController) //Setup Drawer navigation with navController
    }

    private fun showBothNavigation() {
        binding.mainBottomNavigationView.visibility = View.VISIBLE
        binding.navView.visibility = View.VISIBLE
        binding.mainDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        setupNavControl() //To configure navController with drawer and bottom navigation
    }

    private fun setupNavControl() {
        binding.mainBottomNavigationView.setupWithNavController(navController) //Setup Bottom navigation with navController
    }

    fun exitApp() { //To exit the application call this function from fragment
        this.finishAffinity()
    }

    override fun onSupportNavigateUp(): Boolean { //Setup appBarConfiguration for back arrow
        return NavigationUI.navigateUp(navController, appBarConfiguration)
    }

    override fun onBackPressed() {
        when { //If drawer layout is open close that on back pressed
            binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START) -> {
                binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
            }
            else -> {
                super.onBackPressed() //If drawer is already in closed condition then go back
            }
        }
    }
}
