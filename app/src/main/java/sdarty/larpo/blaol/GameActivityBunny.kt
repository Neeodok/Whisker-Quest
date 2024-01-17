package sdarty.larpo.blaol

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import sdarty.larpo.blaol.databinding.BunnyScrnBinding
import kotlin.random.Random

class GameActivityBunny : AppCompatActivity() {
    private val binding by lazy { BunnyScrnBinding.inflate(layoutInflater) }
    private var gameCoroutine: Job? = null
    private val sharedPrefs by lazy { getSharedPreferences("MyPrefs", Context.MODE_PRIVATE) }
    private val isSoundOn by lazy { sharedPrefs.getBoolean(prefSoundOnKey,true) }

    private  val frontCrystals by lazy {
        listOf(
            binding.itemHolder.i1front, binding.itemHolder.i2front, binding.itemHolder.i3front, binding.itemHolder.i4front, binding.itemHolder.i5front,
            binding.itemHolder.i6front, binding.itemHolder.i7front, binding.itemHolder.i8front, binding.itemHolder.i9front, binding.itemHolder.i10front,
            binding.itemHolder.i11front, binding.itemHolder.i12front, binding.itemHolder.i13front, binding.itemHolder.i14front, binding.itemHolder.i15front,
            binding.itemHolder.i16front, binding.itemHolder.i17front, binding.itemHolder.i18front, binding.itemHolder.i19front, binding.itemHolder.i20front,
            binding.itemHolder.i21front, binding.itemHolder.i22front, binding.itemHolder.i23front, binding.itemHolder.i24front, binding.itemHolder.i25front,
            binding.itemHolder.i26front, binding.itemHolder.i27front, binding.itemHolder.i28front, binding.itemHolder.i29front, binding.itemHolder.i30front,
        )
    }
    private  val backCrystals by lazy {
        listOf(
            binding.itemHolder.i1back, binding.itemHolder.i2back, binding.itemHolder.i3back, binding.itemHolder.i4back, binding.itemHolder.i5back,
            binding.itemHolder.i6back, binding.itemHolder.i7back, binding.itemHolder.i8back, binding.itemHolder.i9back, binding.itemHolder.i10back,
            binding.itemHolder.i11back, binding.itemHolder.i12back, binding.itemHolder.i13back, binding.itemHolder.i14back, binding.itemHolder.i15back,
            binding.itemHolder.i16back, binding.itemHolder.i17back, binding.itemHolder.i18back, binding.itemHolder.i19back, binding.itemHolder.i20back,
            binding.itemHolder.i21back, binding.itemHolder.i22back, binding.itemHolder.i23back, binding.itemHolder.i24back, binding.itemHolder.i25back,
            binding.itemHolder.i26back, binding.itemHolder.i27back, binding.itemHolder.i28back, binding.itemHolder.i29back, binding.itemHolder.i30back,

        )
    }

    private val indyx = mutableListOf(
        0,0,0,0,0,
        0,0,0,0,0,
        0,0,0,0,0,
        0,0,0,0,0,
        0,0,0,0,0,
        0,0,0,0,0,
        )

    private val currentCrystal by lazy { binding.currItem }
    private val tvCrystalLast by lazy { binding.tvItemLast }
    private val tvTostik by lazy { binding.tvToast }
    private val tvWin by lazy { binding.wintext }

    private var currentIndyx = 0
    private var lastToFind = 0

    private val pictyre = listOf(
        R.drawable.symbol_1,
        R.drawable.symbol_2,
        R.drawable.symbol_3 ,
        R.drawable.symbol_4,
        R.drawable.symbol_5,
        R.drawable.symbol_6,
        R.drawable.symbol_7)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tvWin.visibility = View.GONE



        pulseBtnAnimation(currentCrystal)

        setFrontsCryst(frontCrystals)
        setBacksCryst()

        startGame()

        frontCrystals.forEachIndexed { index, item ->
            item.setOnClickListener {
                if (isSoundOn){
                    MediaPlayer.create(this, R.raw.perevorot).start()
                }
                animClickView(it, this)
                val backItem = backCrystals[index]
                flipKartunka(this,backItem, item)
                item.isClickable = false

                if (indyx[index] == currentIndyx) {
                    lastToFind--
                    binding.tvItemLast.text = lastToFind.toString()
                    if (lastToFind == 0) {
                        currentIndyx++
                        if (currentIndyx < pictyre.size) {
                            binding.currItem.setBackgroundResource(pictyre[currentIndyx])
                            setAmountLast()
                        } else {
                            gameOverchik()
                        }
                    }
                } else {
                    flipKartunka(this,item, backItem)
                    item.isClickable = true
                }
            }
        }
    }

    private fun startGame() {
        gameCoroutine?.cancel()
        tvTostik.text = "Look and remember!"
        gameCoroutine = CoroutineScope(Dispatchers.Main).launch{
            setAllNotClickable(frontCrystals)
            for (i in 0..29 ){
                flipKartunka(this@GameActivityBunny,backCrystals[i], frontCrystals[i])
                delay(100)
            }

            delay(5500)

            for (i in 0..29 ){
                flipKartunka(this@GameActivityBunny,frontCrystals[i], backCrystals[i])
                delay(50)
            }
            delay(500)
            setAllClickable(frontCrystals)

            setCurrCryst()



        }
    }
    private fun setBacksCryst() {
        for (i in 0..29){
            val index = Random.nextInt(pictyre.size)
            indyx[i] = index
            backCrystals[i].setBackgroundResource(pictyre[index])

        }
    }
    override fun onDestroy() {
        super.onDestroy()
        gameCoroutine?.cancel()
    }
    private fun setCurrCryst() {
        if (currentIndyx < pictyre.size) {
            setAmountLast()
            binding.currItem.setBackgroundResource(pictyre[currentIndyx])
        } else {
            gameOverchik()
        }
    }
    private fun setAmountLast() {
        lastToFind = indyx.count { it == currentIndyx }
        tvCrystalLast.text = lastToFind.toString()
    }

    private fun gameOverchik() {
        tvTostik.text = "Game over!"
        currentIndyx = 0
        lastToFind = 0
        tvWin.visibility = View.VISIBLE

        CoroutineScope(Dispatchers.Main).launch {
            delay(2500)
            startGame()
            tvWin.visibility = View.GONE

        }
    }




}
