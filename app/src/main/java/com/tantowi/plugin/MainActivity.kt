package com.tantowi.plugin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tantowi.plugin.adapters.PostAdapter
import com.tantowi.plugin.contracts.MainActivityContract
import com.tantowi.plugin.models.Post
import com.tantowi.plugin.presenters.MainActivityPresenter
import com.tantowi.plugin.utilities.Util
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainActivityContract.MainActivityView {
    private var presenter = MainActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainActivityPresenter(this)

        Logout()
        pindah()

        btn_add.setOnClickListener {
            startActivity(Intent(this@MainActivity, tambah_squad::class.java).apply {
                putExtra("IS_NEW", true)
            })
        }
    }

    private fun Logout(){
        btnLogout.setOnClickListener{
            startActivity(Intent(this, activity_login::class.java)).also{
                Util.clearToken(this)
                finish()
            }
        }
    }

    private fun pindah(){
        btn_pindah.setOnClickListener {
            startActivity(Intent(this, AccountActivity::class.java))
        }

        btn_theory.setOnClickListener {
            startActivity(Intent(this, TheoryActivity::class.java))
        }
    }

    //~~//

    private fun getData() {
        Util.getToken(this)?.let {
            presenter.allUser(it)
        }
    }

    override fun attachToRecycle(squad: List<Post>) {
        rvPost.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = PostAdapter(squad, this@MainActivity)
        }
    }

    override fun toast(message: String?) {
        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {
        TODO("Not yet implemented")
    }

    override fun notConnect(message: String?) {
        TODO("Not yet implemented")
    }

    private fun checkIsLoggedIn(){
        val token = Util.getToken(this@MainActivity)
        if (token == null || token.equals("UNDEFINED")){
            startActivity(Intent(this@MainActivity, activity_login::class.java).also { finish() })
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onResume() {
        super.onResume()
        getData()
    }
}
