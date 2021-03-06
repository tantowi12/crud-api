package com.tantowi.plugin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tantowi.plugin.contracts.MainActivityContract
import com.tantowi.plugin.presenters.UpdateActivityPresenter
import com.tantowi.plugin.utilities.Util
import kotlinx.android.synthetic.main.edit_squad.*

class edit_squad : AppCompatActivity(), MainActivityContract.ViewEdit {

    private var presenter = UpdateActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_squad)
        et_IdEdit.setText(intent.getStringExtra("USER_ID"))
        et_SquadEdit.setText(intent.getStringExtra("SQUAD"))
        et_DescEdit.setText(intent.getStringExtra("DESCRIPTION"))
        presenter = UpdateActivityPresenter(this)

        Update()
    }

    private fun Update() {
        btn_Edit.setOnClickListener {
            val token = Util.getToken(this)
            val id = et_IdEdit.text.toString().toInt();
            val squad = et_SquadEdit.text.toString().trim()
            val description = et_DescEdit.text.toString().trim()
            if (squad.isNotEmpty() && description.isNotEmpty()) {
//                token?.let { it1 -> presenter.update(id, it1, squad, description)}
                token?.let { it1 -> presenter.update(id, it1, squad, description) }
            }
            else {
                toast("isi semua form")
            }
        }
    }

    override fun success(message: String?) {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {
        btn_Edit.isEnabled = !state
    }
}