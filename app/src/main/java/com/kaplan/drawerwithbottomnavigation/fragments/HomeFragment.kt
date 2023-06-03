package com.kaplan.drawerwithbottomnavigation.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.kaplan.drawerwithbottomnavigation.databinding.FragmentHomeBinding


class HomeFragment : Fragment(), View.OnClickListener { //OnClickListener

    private lateinit var navController: NavController
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = findNavController() //Initialising navController
    }

    override fun onClick(v: View?) { //When click occurs this function is triggered
        when (v?.id) { //Check for the id of the view i which click event happened

        }
    }


}
