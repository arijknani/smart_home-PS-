package eniso.gte2.projetsem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eniso.gte2.projetsem.databinding.ActivitySmartHome4Binding

class SmartHome4Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding= ActivitySmartHome4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn1.setOnClickListener {
            val i= Intent(this,BedroomActivity::class.java)
            startActivity(i)
        }
        binding.btn2.setOnClickListener {
            val i= Intent(this,KitchenActivity::class.java)
            startActivity(i)
        }
        binding.btn3.setOnClickListener {
            val i= Intent(this,BathroomActivity::class.java)
            startActivity(i)
        }
        binding.btn4.setOnClickListener {
            val i= Intent(this,LivingroomActivity::class.java)
            startActivity(i)
        }
    }
}
