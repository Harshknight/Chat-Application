package com.example.chatapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chatapplication.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignup.setOnClickListener {
            val intent = Intent(this,SignupActivity::class.java)
            startActivity(intent)
        }

        mAuth =FirebaseAuth.getInstance()

        binding.btnLogin.setOnClickListener {
            val email =binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            login(email,password)
        }



    }

    private fun login(email: String, password: String) {

        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
                task ->
            if (task.isSuccessful){
                val intent = Intent(this,MainActivity::class.java)
                startActivity(intent)
            }else{
                Toast.makeText(this@LoginActivity," User Doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }
    }
}