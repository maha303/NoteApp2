package com.example.noteapp2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var db:DatabaseHandler
    private lateinit var edNotes:EditText
    private lateinit var btnSave:Button
    private lateinit var rvMain:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db= DatabaseHandler(this)

        edNotes=findViewById(R.id.edNote)

        btnSave=findViewById(R.id.btnSave)
        btnSave.setOnClickListener { postNote()}
        rvMain=findViewById(R.id.rvMain)
        updateRV()

    }

    private fun updateRV() {
     rvMain.adapter=NoteAdapter(this,getItemList())
     rvMain.layoutManager=LinearLayoutManager(this)
    }

    private fun getItemList(): ArrayList<NoteModel> {
        return db.viewNotes()

    }

    private fun postNote() {
        db.addNot(NoteModel(0,edNotes.text.toString()))
        edNotes.text.clear()
        Toast.makeText(this,"SAVED ",Toast.LENGTH_LONG).show()
        updateRV()

    }
}