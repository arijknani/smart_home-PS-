package eniso.gte2.projetsem

import android.widget.EditText
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import eniso.gte2.projetsem.databinding.ActivitySmartHome0Binding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SmartHome0Activity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_login)
        val binding = ActivitySmartHome0Binding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = Firebase.auth

        //admin and admin
        binding.loginbtn.setOnClickListener {
            performLogin()
        }

        binding.register.setOnClickListener {
            val intent = Intent(this, SmartHome1Activity::class.java)
            startActivity(intent)
        }
    }

    private fun performLogin() {
        //lets get input from the user
        val email = findViewById<EditText>(R.id.username)
        val password = findViewById<EditText>(R.id.password)

        //null checks on inputs
        if (email.text.isEmpty() || password.text.isEmpty()) {
            Toast.makeText(this, "Please fill all fields", Toast.LENGTH_LONG).show()
            return
        }
        val inputEmail = email.text.toString()
        val inputPassword = password.text.toString()
        auth.signInWithEmailAndPassword(inputEmail, inputPassword)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success,let move to the next activity
                    val intent = Intent(this, SmartHome4Activity::class.java)
                    startActivity(intent)

                    Toast.makeText(
                        baseContext, "Success",
                        Toast.LENGTH_SHORT
                    ).show()

                } else {
                    // If sign in fails, display a message to the user.

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
            .addOnFailureListener {
                Toast.makeText(
                    this,
                    "Authentification failed ${it.localizedMessage}",
                    Toast.LENGTH_LONG
                ).show()
            }
    }
}