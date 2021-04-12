package com.example.firebasetutorial

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.ChildEventListener
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val dbReference = FirebaseDatabase.getInstance().reference
    private val notesList = arrayListOf<Note>()
    var firebaseUser:FirebaseUser? = null
    val RC_SIGN_IN = 123
    lateinit var adapter: CustomAdapter
//    val _error = 0/0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        adapter = CustomAdapter(this, R.layout.note_layout, notesList)
        listNotes.adapter = adapter

        firebaseUser = FirebaseAuth.getInstance().currentUser
        if(firebaseUser!=null) {
            //already signed in
            addListeners()
        }
        else{
            startActivityForResult(AuthUI.getInstance()
                    .createSignInIntentBuilder()
                    .setIsSmartLockEnabled(false) //disable auto google log in when only one account
                    .setAvailableProviders(listOf(AuthUI.IdpConfig.GoogleBuilder().build(),
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.PhoneBuilder().build()))
                    .build(), RC_SIGN_IN)
        }
        btLogOut.setOnClickListener {
            if(firebaseUser!=null) {
                AuthUI.getInstance()
                        .signOut(this)
                        .addOnCompleteListener {
                            firebaseUser = null
                            this.recreate()
                        }
            }
            else
                Toast.makeText(this,"You are not signed in!",Toast.LENGTH_SHORT).show()
        }

    }

    private fun addListeners() {
        dbReference.child(firebaseUser!!.uid).addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                snapshot.getValue(Note::class.java)?.let { notesList.add(it) }
                adapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        btAddToNotes.setOnClickListener {
            saveToDatabase()
        }

    }

    private fun saveToDatabase() {
        if(etTextValue.text.isNotBlank())
            dbReference.child(firebaseUser!!.uid).push().setValue(Note(etTextValue.text.toString(), etTextValue.text.toString()))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            val response = IdpResponse.fromResultIntent(data)
            // Successfully signed in
            if (resultCode == RESULT_OK) {
                firebaseUser = FirebaseAuth.getInstance().currentUser
                addListeners()
            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    return
                }
                Log.d("pulkit", "Sign-in error: ", response.error)
                if (response.error!!.errorCode == ErrorCodes.NO_NETWORK) {
                    return
                }
            }
        }
    }
}