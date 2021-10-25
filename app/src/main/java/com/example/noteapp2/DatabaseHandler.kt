package com.example.noteapp2

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHandler (context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION){
    companion object{
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "MyDB"
        private const val TABLE_NOTES = "NotesTable"

        private const val KEY_ID = "_id"
        private const val KEY_NOTE = "note"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createT=("CREATE TABLE $TABLE_NOTES($KEY_ID INTEGER PRIMARY KEY,$KEY_NOTE TEXT)")
        db?.execSQL(createT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }
    fun addNot(note : NoteModel):Long{
        val db=this.writableDatabase
        val cv=ContentValues()
        cv.put(KEY_NOTE,note.noteText)
        val success=db.insert(TABLE_NOTES,null,cv)
        db.close()
        return success
    }
    fun viewNotes():ArrayList<NoteModel>{
        val noteList :ArrayList<NoteModel> = ArrayList()
        val selectQuery="SELECT * FROM $TABLE_NOTES"
        val db=this.readableDatabase
        var c:Cursor? = null
        try {
            c = db.rawQuery(selectQuery,null)
        }catch (e:SQLiteException){
            db.execSQL(selectQuery)
            return ArrayList()
        }
        var id :Int
        var noteText:String

        if(c.moveToFirst()){
            do {
                id= c.getInt(c.getColumnIndex(KEY_ID))
                noteText=c.getString(c.getColumnIndex(KEY_NOTE))
                val note =NoteModel(id=id,noteText=noteText)
                noteList.add(note)
            }while (c.moveToNext())
        }
        return noteList

    }

}