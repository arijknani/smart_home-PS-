package eniso.gte2.projetsem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import eniso.gte2.projetsem.databinding.ActivityKitchenBinding
import eniso.gte2.projetsem.databinding.ActivityLivingroomBinding

class LivingroomActivity : AppCompatActivity() {
    private var retrieveTV: TextView? = null
    var myRef = FirebaseDatabase.getInstance().reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLivingroomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveTV=binding.t1
        binding.toolbar.setOnClickListener{
            val intent = Intent(this,SmartHome4Activity::class.java)
            startActivity(intent)
        }
        binding.switch3.setOnClickListener{
            if(binding.switch3.isChecked){
                binding.imageView6.visibility= View.VISIBLE
                binding.imageView5.visibility= View.INVISIBLE
                binding.imageView6.setImageResource(R.drawable.img_6)
            }
            else {
                binding.imageView6.visibility= View.INVISIBLE
                binding.imageView5.visibility= View.VISIBLE
                binding.imageView5.setImageResource(R.drawable.img_5)
            }
        }

        binding.switch4.setOnClickListener{
            if(binding.switch4.isChecked){
                //firebase : get data
                getdata()
                binding.imageView6.visibility= View.VISIBLE
                binding.imageView5.visibility= View.INVISIBLE
                binding.imageView6.setImageResource(R.drawable.gaz)
            }
            else {
                binding.t1.setText("Gaz")
                binding.imageView6.visibility= View.INVISIBLE
                binding.imageView5.visibility= View.VISIBLE
                binding.imageView5.setImageResource(R.drawable.img_5)
            }
        }
        binding.switch5.setOnClickListener{
            if(binding.switch5.isChecked){
                binding.imageView6.visibility= View.VISIBLE
                binding.imageView5.visibility= View.INVISIBLE
                binding.imageView6.setImageResource(R.drawable.temp)
            }
            else {
                binding.imageView6.visibility= View.INVISIBLE
                binding.imageView5.visibility= View.VISIBLE
                binding.imageView5.setImageResource(R.drawable.img_5)
            }
        }
    }

    private fun getdata() {
        var getdata= object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var sb=java.lang.StringBuilder()
                for (i in snapshot.children){
                    var value = i.child("gaz").getValue()
                    retrieveTV?.setText("$value")
                }

            }

            override fun onCancelled(error: DatabaseError) {
                retrieveTV?.setText("Gaz")
            }
        }
        myRef.addValueEventListener(getdata)
        myRef.addListenerForSingleValueEvent(getdata)
    }
}