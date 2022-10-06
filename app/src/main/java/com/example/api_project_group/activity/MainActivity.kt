package com.example.api_project_group.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
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
    lateinit var sharedpref : SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedpref = this.getSharedPreferences("dataUser", Context.MODE_PRIVATE)

        binding.mainToolbar.setTitle("Kumpulan Film")

        binding.addButton.setOnClickListener {
            startActivity(Intent(this, AddFilmActivity::class.java))
        }

        binding.txtLogout.setOnClickListener{
            //add ke sharedpref
            var addUser = sharedpref.edit()
            addUser.putString("username", "")
            addUser.putString("password", "")
            addUser.apply()

            var pinda = Intent(this, LoginActivity::class.java)
            startActivity(pinda)
        }
    }

    override fun onResume() {
        super.onResume()
        setVmToAdapter()
    }

    private fun setVmToAdapter() {
        val viewModel =ViewModelProvider(this).get(ViewModelFilm::class.java)

        viewModel.getliveDataFilm().observe(this, Observer {

            Log.d("response", "setVmToAdapter: " + it.toString())

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