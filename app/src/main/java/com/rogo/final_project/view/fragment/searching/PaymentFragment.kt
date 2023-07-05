package com.rogo.final_project.view.fragment.searching

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.rogo.final_project.R
import com.rogo.final_project.databinding.FragmentPaymentBinding
import com.rogo.final_project.view.fragment.checkout.BottomsheetPayment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PaymentFragment : Fragment() {

    lateinit var binding : FragmentPaymentBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentPaymentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            cvGopay.setOnClickListener {
                if (expandLayoutGopay.visibility == View.GONE){
                    TransitionManager.beginDelayedTransition(cvGopay, AutoTransition())
                    expandLayoutGopay.visibility = View.VISIBLE
                    constraintLay.setBackgroundColor(resources.getColor(R.color.darkblue05))
                    ivArrow.setImageResource(R.drawable.atas)
                }else{
                    TransitionManager.beginDelayedTransition(cvGopay, AutoTransition())
                    expandLayoutGopay.visibility = View.GONE
                    ivArrow.setImageResource(R.drawable.bawah)
                    constraintLay.setBackgroundColor(resources.getColor(R.color.neutral_04))
                }
            }

            cvCreditCard.setOnClickListener {
                if (expandLayoutCreditCard.visibility ==View.GONE){
                    TransitionManager.beginDelayedTransition(cvCreditCard, AutoTransition())
                    expandLayoutCreditCard.visibility = View.VISIBLE
                    constraintLay2.setBackgroundColor(resources.getColor(R.color.darkblue05))
                    ivArrow2.setImageResource(R.drawable.atas)
                }else{
                    TransitionManager.beginDelayedTransition(cvCreditCard, AutoTransition())
                    expandLayoutCreditCard.visibility = View.GONE
                    constraintLay2.setBackgroundColor(resources.getColor(R.color.neutral_04))
                    ivArrow2.setImageResource(R.drawable.bawah)
                }
            }

            btnBayarGopay.setOnClickListener {
                BottomsheetPayment().show(requireActivity().supportFragmentManager,BottomsheetPayment.bottomTag)
            }
            btnBayarCreditCard.setOnClickListener {
                BottomsheetPayment().show(requireActivity().supportFragmentManager,BottomsheetPayment.bottomTag)
            }

            topAppBar.setNavigationOnClickListener {
                findNavController().navigate(R.id.checkoutFragment)
            }
        }
    }


}