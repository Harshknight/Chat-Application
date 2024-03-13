package com.example.chatapplication


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.chatapplication.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class SignupActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var mAuth :FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mAuth =FirebaseAuth.getInstance()
        binding.btnSignup.setOnClickListener {
            val email =binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            signup(email,password)

        }
    }

    private fun signup(email: String, password: String) {
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this) {
            task ->
            if (task.isSuccessful){
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
            }else{
                    Toast.makeText(this@SignupActivity," Some Error Occured",Toast.LENGTH_SHORT).show()
            }
        }
    }
}