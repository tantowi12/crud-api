package com.tantowi.plugin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.tantowi.plugin.contracts.MainActivityContract
import com.tantowi.plugin.presenters.CreateActivityPresenter
import com.tantowi.plugin.utilities.Util
import kotlinx.android.synthetic.main.edit_squad.*
import kotlinx.android.synthetic.main.tambah_squad.*

class tambah_squad : AppCompatActivity(), MainActivityContract.ViewCreate {

    private var presenter = CreateActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tambah_squad)
        presenter = CreateActivityPresenter(this)


        Save()
    }



    private fun Save() {
        btn_tambahSquad.setOnClickListener {
            val token = Util.getToken(this)
            val squads_name = et_TambahSquad.text.toString().trim()
            val description = et_TambahDesc.text.toString().trim()

            if (squads_name.isNotEmpty() && description.isNotEmpty()) {
                token?.let {
                    it -> presenter.save(it, squads_name, description)
                }
            } else {
                toast("isi semua form")
            }
        }
    }

    override fun success(message: String?) {
        startActivity(Intent(this@tambah_squad, MainActivity::class.java))
            .also { finish() }
    }

    override fun toast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {
        btn_tambahSquad.isEnabled = !state
    }
}