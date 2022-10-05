package com.example.api_project_group.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.api_project_group.databinding.ActivityDetailFilmBinding
import com.example.api_project_group.model.RestponseDataFilmItem

class DetailFilmActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailFilmBinding
    private lateinit var dataFilm : RestponseDataFilmItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dataFilm = intent.getSerializableExtra("okedetail") as RestponseDataFilmItem
        binding.nameFilm.text = dataFilm.name
        binding.dirFilm.text = dataFilm.director
        binding.sinopsisFilm.text = dataFilm.description
        Glide.with(this).load(dataFilm.image).into(binding.imgFilm)
    }
}