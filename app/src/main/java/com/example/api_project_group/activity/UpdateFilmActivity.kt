package com.example.api_project_group.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api_project_group.databinding.ActivityUpdateFilmBinding
import com.example.api_project_group.viewmodel.ViewModelFilm
import kotlinx.android.synthetic.main.activity_add_film.*

class UpdateFilmActivity : AppCompatActivity() {

    lateinit var binding : ActivityUpdateFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnUpdate.setOnClickListener {
            var fetId = intent.getIntExtra("update", 0)
            var filmName = binding.nameEditInput.text.toString()
            var filmDirector = binding.directorEditInput.text.toString()
            var filmPict = binding.pictureEditInput.text.toString()
            var filmDesc = binding.descEditInput.text.toString()

            updateDataFilm(fetId, filmName, filmDirector, filmPict, filmDesc)
            finish()
        }
    }

    fun updateDataFilm(id : Int, name : String, director : String,  img : String, desc : String) {
        var viewModel = ViewModelProvider(this).get(ViewModelFilm :: class.java)
        viewModel.callUpdateFilm(id, name, director, img, desc)
        viewModel.updateDataFilm().observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "Data Updated !", Toast.LENGTH_SHORT).show()
                Log.d("updatefilm", it.toString())
            }
        })
    }
}