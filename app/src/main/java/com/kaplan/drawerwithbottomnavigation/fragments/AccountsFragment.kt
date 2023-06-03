package com.kaplan.drawerwithbottomnavigation.fragments


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kaplan.drawerwithbottomnavigation.R


/**
 * AccountsFragment
 */

class AccountsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_accounts, container, false)
    }


}
