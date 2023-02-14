package com.example.intentslab

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.example.intentslab.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding:ActivityMainBinding by lazy{
        ActivityMainBinding.inflate(layoutInflater)
    }

    private lateinit var launcher: ActivityResultLauncher<Intent>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        launcher = registerForActivityResult(StartActivityForResult(), ::onResult)

        binding.fuerteBTN.setOnClickListener(::showSelector)
        binding.bebidaBTN.setOnClickListener(::showSelector)
        binding.postreBTN.setOnClickListener(::showSelector)
    }

    fun onResult(result:ActivityResult){
        if(result.resultCode == RESULT_OK){
            val type = result.data?.extras?.getString("type")
            val option = result.data?.extras?.getString("option")
            Toast.makeText(this, "${type}:${option}", Toast.LENGTH_LONG).show()
        }
    }

    fun showSelector(view: View){
        val intent = Intent(this, MenuPickerActivity::class.java)
        when(view.id){
            R.id.fuerteBTN->{
                intent.apply {
                    putExtra("type", "fuerte")
                    putExtra("options", arrayOf("Fuerte 1", "Fuerte 2","Fuerte 3"))
                }
            }
            R.id.bebidaBTN->{
                intent.apply {
                    putExtra("type", "bebida")
                    putExtra("options", arrayOf("Bebida 1", "Bebida 2","Bebida 3"))
                }
            }
            R.id.postreBTN->{
                intent.apply {
                    putExtra("type", "postre")
                    putExtra("options", arrayOf("Postre 1", "Postre 2"))
                }
            }
        }
        launcher.launch(intent)
    }

}