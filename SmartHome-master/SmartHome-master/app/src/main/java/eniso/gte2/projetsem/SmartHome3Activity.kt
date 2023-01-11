package eniso.gte2.projetsem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eniso.gte2.projetsem.databinding.ActivitySmartHome3Binding

class SmartHome3Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_smart_home3)
        val binding = ActivitySmartHome3Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.t4.setOnClickListener {
            val i = Intent(this,SmartHome4Activity::class.java)
            startActivity(i)
        }
    }
}