package sdarty.larpo.blaol

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import sdarty.larpo.blaol.databinding.InfoScreenBinding

class InfoAct:AppCompatActivity() {

    private val bindo by lazy { InfoScreenBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(bindo.root)


    }
}