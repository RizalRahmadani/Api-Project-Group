package com.example.api_project_group.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.api_project_group.R
import com.example.api_project_group.viewmodel.ViewModelUser
import com.example.api_project_group.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {

    lateinit var binding : ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_register)

        //seleksi pass = confpass
        binding.btnRegister.setOnClickListener {

            //ambil inputan data dari layout
            var editUsername = binding.editUsername.text.toString()
            var editName = binding.editNama.text.toString()
            var editPass = binding.editPassword.text.toString()
            var editConfPass = binding.editConfirmPassword.text.toString()

            //seleksi password
            if (editPass.equals(editConfPass)) {
                //masukin data ke API
                addUser(editName, editUsername, editPass)
                //back to login
                var pindah = Intent(this, LoginActivity::class.java)
                startActivity(pindah)
            } else toast("Password Doesn't Match!")
        }
    }

    fun toast(mess:String){
        Toast.makeText(this, mess, Toast.LENGTH_SHORT).show()
    }

    fun addUser(name:String, username:String, password:String){
        var viewModel = ViewModelProvider(this).get(ViewModelUser::class.java)
        viewModel.callPostAPICar(name, username, password)
        viewModel.addLiveDataUser().observe(this, {
            if(it!=null){
                Toast.makeText(this, "Registration Success!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}