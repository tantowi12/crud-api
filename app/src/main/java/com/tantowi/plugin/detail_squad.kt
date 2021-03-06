package com.tantowi.plugin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import com.tantowi.plugin.contracts.MainActivityContract
import com.tantowi.plugin.models.Post
import com.tantowi.plugin.presenters.DeleteActivityPresenter
import com.tantowi.plugin.utilities.Util
import kotlinx.android.synthetic.main.detail_squad.*
import org.w3c.dom.Text

class detail_squad : AppCompatActivity(), MainActivityContract.ViewDelete {

    private var presenter = DeleteActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_squad)

//        val allsquad = getSquad()
//        val id = findViewById<TextView>(R.id.tv_IdDetail)
//        val squad = findViewById<TextView>(R.id.tv_SquadDetail)
//        val description = findViewById<TextView>(R.id.tv_DetailDetail)
//
//        allsquad?.let {
//            id.text = it.id.toString()
//            squad.text = it.squad
//            description.text = it.description
//        }

        Squad()
        presenter = DeleteActivityPresenter(this)
        deleteSquad()
        editSquad()
    }


    override fun success(message: String?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {
        btn_DeleteDetail.isEnabled = !state
    }

    private fun Squad(){
        val squad = getSquad()
        val squads = findViewById<TextView>(R.id.tv_SquadDetail)
        val description = findViewById<TextView>(R.id.tv_DetailDetail)
        squad?.let {
            squads.text=it.squads_name
            description.text=it.description
        }
    }

    private fun getSquad() = intent.getParcelableExtra<Post>("post")

    private fun editSquad() {
        btn_EditDetail.setOnClickListener {
            val squad = getSquad()
            val intent = Intent(this, edit_squad::class.java)
            println("Squad" + squad)
            intent.putExtra("USER_ID", squad?.id)
            intent.putExtra("SQUAD", squad?.squads_name)
            intent.putExtra("DESCRIPTION", squad?.description)

            startActivity(intent)
        }
    }

    private fun deleteSquad() {
        btn_DeleteDetail.setOnClickListener {
            val squad = getSquad()
            val id = squad?.id.toString().toInt()
            val token = Util.getToken(this)
            token?.let {
                it -> presenter.delete(id, it)
            }
        }
    }
}