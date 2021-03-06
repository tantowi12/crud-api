package com.tantowi.plugin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.tantowi.plugin.adapters.theory.TheoryAdapter
import com.tantowi.plugin.contracts.TheoryActivityContract
import com.tantowi.plugin.models.Theory
import com.tantowi.plugin.presenters.MainActivityPresenter
import com.tantowi.plugin.presenters.theory.GetTheoryActivityPresenter
import com.tantowi.plugin.utilities.Util
import kotlinx.android.synthetic.main.activity_theory.*

class TheoryActivity : AppCompatActivity(), TheoryActivityContract.TheoryActivityView {
    private var presenter = GetTheoryActivityPresenter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_theory)
        presenter = GetTheoryActivityPresenter(this)

    }

    private fun getDataTheory() {
        Util.getToken(this)?.let {
            presenter.allTheory(it)
        }
    }

    override fun attachToRecycle(theory: List<Theory>) {
        rvTheory.apply {
            layoutManager = LinearLayoutManager(this@TheoryActivity)
            adapter = TheoryAdapter(theory, this@TheoryActivity)
        }
    }

    override fun toast(message: String?) {
        Toast.makeText(this@TheoryActivity, message, Toast.LENGTH_LONG).show()
    }

    override fun isLoading(state: Boolean) {

    }

    override fun notConnect(message: String?) {
        TODO("Not yet implemented")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.destroy()
    }

    override fun onResume() {
        super.onResume()
        getDataTheory()
    }
}