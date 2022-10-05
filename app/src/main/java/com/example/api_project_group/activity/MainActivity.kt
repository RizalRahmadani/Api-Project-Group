package com.example.api_project_group.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.api_project_group.R
import com.example.api_project_group.adapter.AdapterFilm
import com.example.api_project_group.databinding.ActivityMainBinding
import com.example.api_project_group.viewmodel.ViewModelFilm

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var filmAdapter: AdapterFilm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.mainToolbar.setTitle("Kumpulan Film")

        setVmToAdapter()

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddFilmActivity::class.java))
        }
    }

    private fun setVmToAdapter() {
        val viewModel =ViewModelProvider(this).get(ViewModelFilm::class.java)

        viewModel.getliveDataFilm().observe(this, Observer {
            filmAdapter = AdapterFilm(it)

            viewModel.loading.observe(this, Observer {
                when(it){
                    true -> binding.progressBarMain.visibility = View.VISIBLE
                    false -> binding.progressBarMain.visibility = View.GONE
                }
            })

            if (it != null) {
                binding.rvFilm.layoutManager = LinearLayoutManager(
                    this, LinearLayoutManager.VERTICAL, false
                )

                filmAdapter = AdapterFilm(it)
                binding.rvFilm.adapter = filmAdapter

                filmAdapter.onDelete={
                    viewModel.callDeleteFilm(it)
                    viewModel.getdeleteFilm().observe(this, Observer {
                        viewModel.loading.observe(this, Observer {
                            when(it){
                                true -> binding.progressBarMain.visibility = View.VISIBLE
                                false -> binding.progressBarMain.visibility = View.GONE
                            }
                            Toast.makeText(this, "Film Deleted", Toast.LENGTH_SHORT).show()
                        })
                    })
                }
                filmAdapter.notifyDataSetChanged()
            } else {
                Toast.makeText(this, "There is no data to show", Toast.LENGTH_SHORT).show()
            }

            filmAdapter.onDetail ={
                var getData = it
                var intent = Intent(this, DetailFilmActivity::class.java)

                intent.putExtra("det", getData)
                startActivity(intent)
            }

        })

        viewModel.callApiFilm()
    }


}