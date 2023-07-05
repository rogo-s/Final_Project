package com.rogo.final_project.view.fragment.checkout

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.rogo.final_project.view.fragment.roundTrip.DetailDepature
import com.rogo.final_project.view.fragment.roundTrip.DetailReturn


class CheckoutDetailAdapterRound (activity: AppCompatActivity): FragmentStateAdapter(activity) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when(position){
            0 -> fragment = CheckoutDepatureDetail()
            1 -> fragment = CheckoutReturnDetail()
        }
        return fragment as Fragment
    }
}