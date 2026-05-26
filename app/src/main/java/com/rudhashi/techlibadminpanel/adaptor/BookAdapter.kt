package com.rudhashi.techlibadminpanel.adaptor

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.rudhashi.techlibadminpanel.databinding.ItemAllBookBinding
import com.rudhashi.techlibadminpanel.model.Book

class BookAdapter(
    private val onItemClicked: (String) -> Unit
) : RecyclerView.Adapter<BookAdapter.BookViewHolder>() {

    private val bookList = mutableListOf<Book>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val binding = ItemAllBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(bookList[position])
    }

    override fun getItemCount(): Int = bookList.size

    @SuppressLint("NotifyDataSetChanged")
    fun submitList(newList: List<Book>) {
        bookList.clear()
        bookList.addAll(newList)
        notifyDataSetChanged() // Notify the adapter that the data set has changed
    }

    inner class BookViewHolder(private val binding: ItemAllBookBinding) : RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(book: Book) {
            binding.bookTitle.text = book.BName
            binding.bookCode.text = book.BCode
            binding.bookViewCount.text = book.BView.toString()

            Glide.with(binding.bookImage.context)
                .load(book.BCover)
                .into(binding.bookImage)

            binding.root.setOnClickListener {
                onItemClicked(book.BCode)
            }
        }
    }
}