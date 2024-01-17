package sdarty.larpo.blaol


import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sdarty.larpo.blaol.databinding.SpScreenBinding

class LoadAct:AppCompatActivity() {
    private val binding by lazy { SpScreenBinding.inflate(layoutInflater) }
    private val splashDuration: Long = 3000



   private val animationSplash by lazy { binding.loadd}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        animationSplash.visibility = View.VISIBLE
        CoroutineScope(Dispatchers.Main).launch {
            delay(splashDuration)
            startMainActivity()
        }
    }

    private fun startMainActivity() {
        val mainIntent = Intent(this@LoadAct, MenuAct::class.java)
        startActivity(mainIntent)
        animationSplash.pauseAnimation()
        finish()
    }
}