package com.example.api_project_group.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.api_project_group.databinding.ActivityUpdateFilmBinding
import com.example.api_project_group.model.RestponseDataFilmItem
import com.example.api_project_group.viewmodel.ViewModelFilm
import java.util.*

class UpdateFilmActivity : AppCompatActivity() {

    private lateinit var detailFilm: RestponseDataFilmItem
    lateinit var binding : ActivityUpdateFilmBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        detailFilm = intent.getSerializableExtra("update") as RestponseDataFilmItem
        binding.nameEditInput.setText(detailFilm.name)
        binding.directorEditInput.setText(detailFilm.director)
        binding.pictureEditInput.setText(detailFilm.image)
        binding.descEditInput.setText(detailFilm.description)

        binding.btnUpdate.setOnClickListener {
            val fetId = detailFilm.id
            val filmName = binding.nameEditInput.text.toString()
            val filmDirector = binding.directorEditInput.text.toString()
            val filmPict = binding.pictureEditInput.text.toString()
            val filmDesc = binding.descEditInput.text.toString()

            updateDataFilm(fetId, filmName, filmDirector, filmPict, filmDesc)
            finish()
        }

        binding.btnEng.setOnClickListener {
            setLocale("en")
        }

        binding.btnIna.setOnClickListener {
            setLocale("id")
        }
    }

    private fun updateDataFilm(id : Int, name : String, director : String, img : String, desc : String) {
        val viewModel = ViewModelProvider(this)[ViewModelFilm :: class.java]
        viewModel.callUpdateFilm(id, name, director, img, desc)
        viewModel.updateDataFilm().observe(this, Observer {
            if (it != null) {
                Toast.makeText(this, "Data Updated !", Toast.LENGTH_SHORT).show()
                Log.d("updatefilm", it.toString())
            }
        })
    }

    private fun setLocale(lang : String?) {
        val myLocale = Locale(lang)
        val res = resources
        val conf = res.configuration
        conf.locale = myLocale
        res.updateConfiguration(conf, res.displayMetrics)
        val intent = Intent(this, UpdateFilmActivity :: class.java)
        intent.putExtra("update", detailFilm)
        startActivity(intent)
        finish()
    }
}