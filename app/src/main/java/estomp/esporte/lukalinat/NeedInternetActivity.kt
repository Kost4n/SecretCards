package estomp.esporte.lukalinat

import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import estomp.esporte.lukalinat.databinding.ActivityNeedInternetBinding

class NeedInternetActivity: AppCompatActivity() {
    private lateinit var binding: ActivityNeedInternetBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNeedInternetBinding.inflate(layoutInflater)

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(binding.root)

        binding.buttonRestartInternet.setOnClickListener {
            startActivity(Intent(this, LoadingActivity::class.java))
        }
    }
}