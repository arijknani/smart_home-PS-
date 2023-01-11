package eniso.gte2.projetsem

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import eniso.gte2.projetsem.databinding.ActivityBedroomBinding
import eniso.gte2.projetsem.databinding.ActivityKitchenBinding

class BedroomActivity : AppCompatActivity() {
    private var retrieveTV: TextView? = null
    var myRef = FirebaseDatabase.getInstance().reference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBedroomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        retrieveTV=binding.t1
        binding.toolbar.setOnClickListener{
            val intent = Intent(this,SmartHome4Activity::class.java)
            startActivity(intent)
        }
        binding.switch3.setOnClickListener{
            if(binding.switch3.isChecked){
                binding.imageView6.visibility=View.VISIBLE
                binding.imageView5.visibility=View.INVISIBLE
                binding.imageView6.setImageResource(R.drawable.img_6)
            }
            else {
                binding.imageView6.visibility=View.INVISIBLE
                binding.imageView5.visibility=View.VISIBLE
                binding.imageView5.setImageResource(R.drawable.img)
            }
        }

        binding.switch4.setOnClickListener{
            if(binding.switch4.isChecked){
                //firebase : get data
                getdata()
                binding.imageView6.visibility=View.VISIBLE
                binding.imageView5.visibility=View.INVISIBLE
                binding.imageView6.setImageResource(R.drawable.gaz)
            }
            else {
                binding.t1.setText("Gaz")
                binding.t1.setTextColor(Color.BLACK)
                binding.imageView6.visibility=View.INVISIBLE
                binding.imageView5.visibility=View.VISIBLE
                binding.imageView5.setImageResource(R.drawable.img)
            }
        }
        binding.switch5.setOnClickListener{
            if(binding.switch5.isChecked){
                binding.imageView6.visibility=View.VISIBLE
                binding.imageView5.visibility=View.INVISIBLE
                binding.imageView6.setImageResource(R.drawable.temp)
            }
            else {
                binding.imageView6.visibility=View.INVISIBLE
                binding.imageView5.visibility=View.VISIBLE
                binding.imageView5.setImageResource(R.drawable.img)
            }
        }
    }

    private fun getdata() {
        var getdata= object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val builder = AlertDialog.Builder(this@BedroomActivity)
                val builder1 = AlertDialog.Builder(this@BedroomActivity)
                for (i in snapshot.children){
                    var value = i.child("gaz").getValue()
                    retrieveTV?.setText("$value")
                    if (retrieveTV?.text.toString().toInt() > 50){
                        retrieveTV?.setTextColor(Color.RED)
                        builder.setTitle("Alert!")
                            .setMessage("Your GAZ level has exceeded 50Pa")
                            .setCancelable(true)
                            .setPositiveButton("OK") { dialogInterface, it ->
                                finish()
                            }

                            .setNeutralButton("Help"){ dialogInterface, it ->
                                builder1.setTitle("Advices")
                                    .setMessage("Ventilate your home without turning the light on \n Wear a mask \n Shut off your gas supply \n Do not touch any electrical device or telephone \n Avoid flames and sparks \n Leave your room \n Call 0 800 47 33 33 \n Wait for the arrival of the GRDF technician in front of your home")
                                    .setCancelable(true)
                                    .setPositiveButton("OK") { dialogInterface, it ->
                                        finish()
                                    }
                                    .show()
                            }
                            .show()
                    }
                }

            }

            override fun onCancelled(error: DatabaseError) {
                retrieveTV?.setText("Gaz")
                retrieveTV?.setTextColor(Color.BLACK)
            }
        }
        myRef.addValueEventListener(getdata)
        myRef.addListenerForSingleValueEvent(getdata)
    }
}