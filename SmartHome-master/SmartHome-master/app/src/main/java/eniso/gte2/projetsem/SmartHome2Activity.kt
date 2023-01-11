package eniso.gte2.projetsem
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eniso.gte2.projetsem.databinding.ActivitySmartHome2Binding

class SmartHome2Activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_smart_home2)
        val binding = ActivitySmartHome2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.t4.setOnClickListener {
            val i = Intent(this,SmartHome3Activity::class.java)
            startActivity(i)
        }
    }
}