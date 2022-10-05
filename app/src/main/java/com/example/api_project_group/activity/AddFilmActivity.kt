package com.example.api_project_group.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api_project_group.R
import com.example.api_project_group.databinding.ActivityAddFilmBinding
import com.example.api_project_group.viewmodel.ViewModelFilm

class AddFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityAddFilmBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_film)

        binding = ActivityAddFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddFilm.setOnClickListener {
            postCar()
            val pindah = Intent(this, MainActivity::class.java)
            startActivity(pindah)

        }
    }



    fun postCar(){
        var name = binding.name.text.toString()
        var director = binding.director.text.toString()
        var image = binding.image.text.toString()
        var description = binding.description.text.toString()


        val viewModel = ViewModelProvider(this).get(ViewModelFilm::class.java)
        viewModel.callAddFilm(name, director, image, description)
        viewModel.addDataFilm().observe(this, Observer {
            if (it != null){
                Toast.makeText(this, "Add Data Successfull", Toast.LENGTH_SHORT).show()
                Log.d("addfilm", it.toString())
            }
        })
    }
}