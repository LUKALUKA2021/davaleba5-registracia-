package com.example.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

 private lateinit var emailEdit : EditText
 private lateinit var pass1Edit: EditText
 private lateinit var pass2Edit: EditText
 private lateinit var submitButton : Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        submit()


    }

    private fun init() {
        emailEdit=findViewById(R.id.EmailAddress)
        pass1Edit=findViewById(R.id.Password1)
        pass2Edit=findViewById(R.id.Password2)
        submitButton =findViewById(R.id.submit)

    }

    private fun submit() {

        submitButton.setOnClickListener {

            val email = emailEdit.text.toString()
            val password = pass1Edit.text.toString()
            val password1 = pass2Edit.text.toString()


            if (email.isEmpty() || password.isEmpty() || password1.isEmpty()
                || password1 != password ||password.length <9 || !password.contains("[0-9]".toRegex()) || !password1.contains(
                    "[0-9]".toRegex()
                )
            ) {
                Toast.makeText(this, "გთხოვთ სწორად შეიყვანოთ ან გაიმეოროთ პაროლი!", Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "რეგისტრაცია წარმატებით გაიარეთ!", Toast.LENGTH_SHORT).show()

                    }
                    else
                        Toast.makeText(this, "გთხოვთ შეიყვანოთ სწორი E-mail!", Toast.LENGTH_SHORT).show()

                }




        }

    }}