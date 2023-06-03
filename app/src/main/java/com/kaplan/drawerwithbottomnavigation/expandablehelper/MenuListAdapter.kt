package com.kaplan.drawerwithbottomnavigation.expandablehelper

import android.annotation.SuppressLint
import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.kaplan.drawerwithbottomnavigation.R
import pl.openrnd.multilevellistview.ItemInfo
import pl.openrnd.multilevellistview.MultiLevelListAdapter

 class MenuListAdapter(val context: Activity) : MultiLevelListAdapter() {
    public override fun getSubObjects(menu: Any): List<*>? {
        return MenuProvider.getSubItems(menu as MenuBaseItem, context)
    }

    public override fun isExpandable(menu: Any): Boolean {
        return MenuProvider.isExpandable(menu as MenuBaseItem)
    }

    @SuppressLint("InflateParams")
    override fun getViewForObject(menu: Any, convertView: View?, itemInfo: ItemInfo): View? {
        var convertView: View? = convertView
        val viewHolder: ViewHolder
        if (convertView == null) {
            viewHolder = ViewHolder()
            convertView = LayoutInflater.from(context).inflate(R.layout.drawer_menu_item, null)
            viewHolder.nameView = convertView.findViewById(R.id.dataItemName)
            viewHolder.arrowView = convertView.findViewById(R.id.dataItemArrow)
            viewHolder.icon = convertView.findViewById(R.id.di_image)
            convertView.setTag(viewHolder)
        } else {
            viewHolder = convertView.tag as ViewHolder
        }
        viewHolder.nameView?.text = (menu as MenuBaseItem).name
        if (itemInfo.isExpandable) {
            viewHolder.arrowView?.visibility = View.VISIBLE
            viewHolder.arrowView?.setImageResource(if (itemInfo.isExpanded) R.drawable.ic_baseline_arrow_down_24 else R.drawable.ic_baseline_chevron_right_24)
        } else {
            viewHolder.arrowView?.visibility = View.GONE
        }
        viewHolder.icon?.setImageResource(menu.icon)
        return convertView
    }

    private inner class ViewHolder {
        var nameView: TextView? = null
        var arrowView: ImageView? = null
        var icon: ImageView? = null
    }
}