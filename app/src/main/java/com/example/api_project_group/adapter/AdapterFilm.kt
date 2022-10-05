package com.example.api_project_group.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.api_project_group.activity.DetailFilmActivity
import com.example.api_project_group.activity.UpdateFilmActivity
import com.example.api_project_group.databinding.ItemFilmBinding
import com.example.api_project_group.model.RestponseDataFilmItem

class AdapterFilm (private var listFilm : List<RestponseDataFilmItem>): RecyclerView.Adapter<AdapterFilm.ViewHolder>() {

    var onDelete : ((Int)->Unit)? = null
    var onEdit : ((RestponseDataFilmItem)->Unit)? = null
    var onDetail : ((RestponseDataFilmItem)->Unit)? = null

    class ViewHolder(var binding : ItemFilmBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemFilmBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return  ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.nameFilm.text = listFilm[position].name
        holder.binding.dateFilm.text = listFilm[position].date
        Glide.with(holder.itemView.context).load(listFilm[position].image).into(holder.binding.imgFilm)

        // Button detail
        holder.binding.klikDetail.setOnClickListener{
            val inten = Intent(it.context, DetailFilmActivity::class.java)
            inten.putExtra("okedetail", listFilm[position])
            it.context.startActivity(inten)
        }

        // Button edit
        holder.binding.editFilm.setOnClickListener {
            val edit = Intent(it.context, UpdateFilmActivity :: class.java)
            edit.putExtra("update", listFilm[position])
            it.context.startActivity(edit)
        }

        //Button Delete
        holder.binding.deleteFilm.setOnClickListener {
            onDelete?.invoke(listFilm[position].id)
        }
    }

    override fun getItemCount(): Int {
        return listFilm.size
    }
}