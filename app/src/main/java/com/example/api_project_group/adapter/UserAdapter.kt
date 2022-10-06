package com.example.api_project_group.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.api_project_group.databinding.ActivityLoginBinding
import com.example.api_project_group.model.ResponseDataUserItem

class UserAdapter(var listUser : List<ResponseDataUserItem>): RecyclerView.Adapter<UserAdapter.ViewHolder>(){
    class ViewHolder(var binding : ActivityLoginBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var view = ActivityLoginBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val id = listUser[position].id
        val name = listUser[position].name
        val username = listUser[position].username
        val pass = listUser[position].password

        var inputUsername = holder.binding.editUsernameLog.text.toString()
        var inputPass = holder.binding.editPasswordLog.text.toString()

        /* if(username.equals(inputUsername) && pass.equals(inputPass)) {
            Toast.makeText(LoginActivity(), "Username ${username} inputusername ${inputUsername}", Toast.LENGTH_LONG).show()
            val it = LoginActivity()
            var pindah = Intent(it, LoginActivity::class.java)
            pindah.putExtra("id", id)
            pindah.putExtra("name", name)
            pindah.putExtra("username", username)
            pindah.putExtra("pass", pass)
            it.startActivity(pindah)
        }*/
    }

    override fun getItemCount(): Int = listUser.size

}