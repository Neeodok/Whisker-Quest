package sdarty.larpo.blaol

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import sdarty.larpo.blaol.databinding.MenuScreenBinding


class MenuAct : AppCompatActivity(){

    private val binding by lazy { MenuScreenBinding.inflate(layoutInflater) }
    private val btnGame by lazy {binding.btnStart  }
    private val btnBack by lazy {binding.exit  }
    private val btnPolina by lazy {binding.btnPoli  }
    private val knopkaSettings by lazy { binding.knopkaSettings }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupListeners()


    }


    private fun setupListeners(){
        btnGame.setOnClickListener {
            animClickView(it,this)
            val intent = Intent(this@MenuAct, LocationActivity::class.java)
            startActivity(intent)
        }

        knopkaSettings.setOnClickListener {
            animClickView(it,this)
            val intent = Intent(this@MenuAct, OptionActi::class.java)
            startActivity(intent)
        }

        btnBack.setOnClickListener {
            animClickView(it,this)
            showExitDialog(this@MenuAct)
        }
        btnPolina.setOnClickListener {
            animClickView(it,this)
            val intent = Intent(this@MenuAct, InfoAct::class.java)
            startActivity(intent)
        }

    }
    private fun showExitDialog(context: Context) {
        AlertDialog.Builder(context)
            .setMessage("Close Game?")
            .setCancelable(false)
            .setPositiveButton("Yes") { _, _ ->
                (context as? AppCompatActivity)?.finishAndRemoveTask()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }
}
