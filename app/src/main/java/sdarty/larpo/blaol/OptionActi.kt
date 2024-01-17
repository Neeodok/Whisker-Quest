package sdarty.larpo.blaol

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import sdarty.larpo.blaol.databinding.OptionsScreBinding


class OptionActi : AppCompatActivity() {

    private val binding by lazy { OptionsScreBinding.inflate(layoutInflater)}

    private val sharedPrefs by lazy { getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) }

    private val rbSoundOn by lazy { binding.soundOn }
    private val rbSoundOff by lazy { binding.soundOff }
    private val rulesinfo by lazy {binding.rulesinfo  }
    private val btnInfokbtnInfok by lazy {binding.btnRule  }
    private var rulesVisible = false

    private val isSoundOn by lazy { sharedPrefs.getBoolean(prefSoundOnKey,true) }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)




        getvalues()

        rbSoundOn.setOnClickListener {
            sharedPrefs.edit().putBoolean(prefSoundOnKey, true).apply()
        }

        rbSoundOff.setOnClickListener {
            sharedPrefs.edit().putBoolean(prefSoundOnKey, false).apply()

        }
        btnInfokbtnInfok.setOnClickListener {
            animClickView(it,this)

            rulesVisible = !rulesVisible
            rulesinfo.visibility = if (rulesVisible) View.VISIBLE else View.INVISIBLE
        }


    }
    private fun getvalues (){

        rbSoundOn.isChecked = isSoundOn
        rbSoundOff.isChecked = !isSoundOn


    }

}
