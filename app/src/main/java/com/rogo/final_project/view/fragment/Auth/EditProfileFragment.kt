package com.rogo.final_project.view.fragment.Auth

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.rogo.final_project.databinding.FragmentEditProfileBinding
import com.rogo.final_project.view.model.data.profile.UpdateProfile
import com.rogo.final_project.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditProfileFragment : Fragment() {
    lateinit var binding : FragmentEditProfileBinding
    private lateinit var sharedPref : SharedPreferences
    private val profileViewModel : UserViewModel by viewModels()
//    private lateinit var  gettoken : TokenDataStore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentEditProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPref = requireContext().getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        val getName = sharedPref.getString("name", "")
        val getPhone = sharedPref.getString("telephone", "")
        binding.etNameProf.setText(getName)
        binding.etPhoneProf.setText(getPhone)

        getdataProfile()

        binding.btnUpdate.setOnClickListener{
            updateProfile()
        }
    }

    fun getdataProfile(){
        val accessToken = sharedPref.getString("token", "").toString()

        profileViewModel.getDataProfile(accessToken)
        profileViewModel.usersGetProfile.observe(viewLifecycleOwner){
            binding.etNameProf.setText(it!!.data.name)
            binding.etPhoneProf.setText(it!!.data.phoneNumber)
        }
    }

    fun updateProfile(){
        val name = binding.etNameProf.text.toString()
        val phoneNumber = binding.etPhoneProf.text.toString()
        val accessToken = sharedPref.getString("token", "").toString()
        val dataProfile = UpdateProfile(name, phoneNumber)
        profileViewModel.updateDataProfile(accessToken, dataProfile)
        profileViewModel.profileUpdate.observe(viewLifecycleOwner){
            if (it != null) {
                Toast.makeText(requireContext(), "Update Profile Berhasil", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Update Profile Gagal", Toast.LENGTH_SHORT).show()
            }
        }
    }
}