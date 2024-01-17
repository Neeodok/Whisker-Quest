package sdarty.larpo.blaol

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sdarty.larpo.blaol.databinding.LocScreensBinding

class LocationActivity : AppCompatActivity() {
    private val bind by lazy { LocScreensBinding.inflate(layoutInflater) }
    private val btnMicky by lazy { bind.btnMicky }
    private val btnBunny by lazy { bind.btnBunny }
    private val btnTiggy by lazy { bind.btnTiggy }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bind.root)
        locListeners()


    }

    private fun locListeners() {
        btnMicky.setOnClickListener {
            animClickView(it, this)
            val intent = Intent(this@LocationActivity, GameActivity::class.java)
            startActivity(intent)
        }
        btnBunny.setOnClickListener {
            animClickView(it, this)
            val intent = Intent(this@LocationActivity, GameActivityBunny::class.java)
            startActivity(intent)
        }
        btnTiggy.setOnClickListener {
            animClickView(it, this)
            val intent = Intent(this@LocationActivity, GameActivityTiggy::class.java)
            startActivity(intent)
        }

    }
}
