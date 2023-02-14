package com.example.intentslab

import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.intentslab.databinding.ActivityMenuPickerBinding

class MenuPickerActivity : AppCompatActivity() {

    private val binding: ActivityMenuPickerBinding by lazy {
        ActivityMenuPickerBinding.inflate(layoutInflater)
    }

    private var foodType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        val options = intent.extras?.getStringArray("options")
        foodType = intent.extras?.getString("type")

        var buttons: Array<Button> = arrayOf()
        if (options?.size == 3) {
            buttons = arrayOf(binding.button5, binding.button6, binding.button7)
        }
        if (options?.size == 2) {
            buttons = arrayOf(binding.button5, binding.button6)
            binding.button7.visibility = View.GONE
        }

        options?.let {
            for (i in 0 until options.size) {
                buttons[i].text = it[i]
            }
        }

        binding.button5.setOnClickListener {
            val intent = Intent()
            intent.apply {
                putExtra("type", foodType)
                putExtra("option", binding.button5.text)
            }
            setResult(RESULT_OK, intent)
            finish()

        }


    }
}