package com.rudhashi.techlibadminpanel.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.rudhashi.techlibadminpanel.MainActivity
import com.rudhashi.techlibadminpanel.adaptor.BookAdapter
import com.rudhashi.techlibadminpanel.databinding.ActivityAllBookBinding
import com.rudhashi.techlibadminpanel.model.Book

class AllBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAllBookBinding
    private lateinit var bookAdapter: BookAdapter
    private val db = FirebaseFirestore.getInstance()
    private val booksCollection = db.collection("books")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAllBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Make the status bar transparent
        @Suppress("DEPRECATION")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            // Android 11 (API 30) and above
            window.setDecorFitsSystemWindows(false)
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        } else {
            // Android 9 (API 28) and Android 10 (API 29)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
            window.statusBarColor = android.graphics.Color.TRANSPARENT
        }

        bookAdapter = BookAdapter { bookCode ->
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("BOOK_CODE", bookCode)
            startActivity(intent)
        }

        fetchBooks()

        binding.apply {
            rvAllBook.layoutManager = LinearLayoutManager(this@AllBookActivity)
            rvAllBook.adapter = bookAdapter
            addNewBookButton.setOnClickListener {
                startActivity(Intent(this@AllBookActivity, AddNewBookActivity::class.java))
            }
            btnBack.setOnClickListener { finish() }
            btnRefresh.setOnClickListener {
                isShimmerOn(true)
                fetchBooks()
            }

            addNewSemesterButton.setOnClickListener {
                startActivity(Intent(this@AllBookActivity, AddNewSemesterActivity::class.java))
            }
        }
    }

    private fun fetchBooks() {
        booksCollection.orderBy("TimeStamp", Query.Direction.DESCENDING) // Or DESCENDING for reverse order
            .get().addOnSuccessListener { querySnapshot ->
                isShimmerOn(false)
                val books = querySnapshot.documents.mapNotNull { it.toObject(Book::class.java) }
                bookAdapter.submitList(books)
            }.addOnFailureListener { e ->
                isShimmerOn(false)
                Toast.makeText(this, "Failed to fetch books: ${e.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    override fun onResume() {
        super.onResume()
        // If data is available then Shimmer Effect will be gone
        isShimmerOn(true)
        fetchBooks()
    }

    override fun onPause() {
        // Shimmer effect will be stopped when the activity is paused to save battery life.
        // If data is available then Shimmer Effect will be gone
        isShimmerOn(false)
        super.onPause()
    }

    private fun isShimmerOn(flag: Boolean) {
        if (flag) {
            // If data is available then Shimmer Effect will be gone
            binding.shimmerBookList.startShimmer()
            binding.shimmerBookList.visibility = View.VISIBLE
            binding.rvAllBook.visibility = View.GONE
        } else {
            binding.shimmerBookList.stopShimmer()
            binding.shimmerBookList.visibility = View.GONE
            binding.rvAllBook.visibility = View.VISIBLE
        }
    }

}