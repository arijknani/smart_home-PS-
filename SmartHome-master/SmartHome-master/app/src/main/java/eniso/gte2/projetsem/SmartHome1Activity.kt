package eniso.gte2.projetsem

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import eniso.gte2.projetsem.databinding.ActivitySmartHome1Binding
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SmartHome1Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_register)
        val binding=ActivitySmartHome1Binding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize Firebase Auth
        auth = Firebase.auth

        binding.t3.setOnClickListener{
            val intent= Intent(this, SmartHome0Activity::class.java)
            startActivity(intent)
        }

        binding.btn.setOnClickListener {
            //lets get email and password from the user
            performSignUp()
        }


    }

    private fun performSignUp() {
        val email= findViewById<EditText>(R.id.e1)
        val password=findViewById<EditText>(R.id.e2)

        if(email.text.isEmpty() || password.text.isEmpty() ){
            Toast.makeText(this,"Please fill all fields",Toast.LENGTH_LONG).show()
            return
        }

        val inputEmail= email.text.toString()
        val inputPassword= password.text.toString()
        auth.createUserWithEmailAndPassword(inputEmail,inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success,let move to the next activity
                    val intent=Intent(this,SmartHome2Activity::class.java)
                    startActivity(intent)

                    Toast.makeText(baseContext, "Success",
                        Toast.LENGTH_SHORT).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(this,"Error occured ${it.localizedMessage}",Toast.LENGTH_LONG).show()
            }
    }
}



