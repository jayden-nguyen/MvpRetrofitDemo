package com.example.admin.retrofitdemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.admin.retrofitdemo.R
import com.example.admin.retrofitdemo.model.BookInfo
import kotlinx.android.synthetic.main.item_book_infomation.view.*

class BookAdapter(bookList: List<BookInfo>?) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {
    private val dataList = bookList
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_book_infomation, parent, false)
        return BookViewHolder(view)
    }

    override fun getItemCount(): Int = dataList!!.size

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.itemView.title.text = dataList!![position].volumeInfo.title
        holder.itemView.publishedDate.text = dataList[position].volumeInfo.publishedDate
        holder.itemView.description.text = dataList[position].volumeInfo.description
        holder.itemView.authors.text = dataList[position].volumeInfo.authors?.toString()
    }

    class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}