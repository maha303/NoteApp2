package com.example.noteapp2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class NoteAdapter (private val activity: MainActivity , private val items:ArrayList<NoteModel>):RecyclerView.Adapter<NoteAdapter.ItemViewHolder>(){
    class ItemViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_row,
                parent,
                false
            )
        )
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.apply {
            tvNotes.text=item.noteText
        }
    }
    override fun getItemCount()=items.size
}