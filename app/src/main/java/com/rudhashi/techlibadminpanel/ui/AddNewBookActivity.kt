package com.rudhashi.techlibadminpanel.ui

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.list.listItemsMultiChoice
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FirebaseFirestore
import com.rudhashi.techlibadminpanel.databinding.ActivityAddNewBookBinding
import com.rudhashi.techlibadminpanel.utils.ConvertFB.covertDepNameToCode
import com.rudhashi.techlibadminpanel.utils.ConvertFB.covertSemNameToTag
import com.rudhashi.techlibadminpanel.utils.MakeFirebaseEasy.getAllDepartment
import com.rudhashi.techlibadminpanel.utils.MakeFirebaseEasy.getAllSemester

class AddNewBookActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewBookBinding

    private val selectedDepartments = mutableListOf<String>()
    private val selectedSemesters = mutableListOf<String>()

    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewBookBinding.inflate(layoutInflater)
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

        // Set up department and semester selection dialogs
        setupDepartmentSelection(getAllDepartment(ArrayList()))
        setupSemesterSelection(getAllSemester(ArrayList()))

        // Post data to Firestore on button click
        binding.postButton.setOnClickListener {
            uploadBookData()
        }

        binding.btnBack.setOnClickListener { finish() }
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun setupDepartmentSelection(department: ArrayList<String>) {
        val departments: List<String> = department

        binding.departmentSelectButton.setOnClickListener {
            MaterialDialog(this).show {
                title(text = "Select Departments")

                listItemsMultiChoice(items = departments) { _, _, items ->

                    val departmentCode = items.map {
                        covertDepNameToCode(it.toString())
                    }

                    selectedDepartments.clear()
                    selectedDepartments.addAll(departmentCode)
                    binding.selectedDepartmentsText.text = "Selected: ${selectedDepartments.joinToString(", ")}"
                }
                positiveButton(text = "OK")
                negativeButton(text = "Cancel")
            }
        }
    }

    @SuppressLint("CheckResult", "SetTextI18n")
    private fun setupSemesterSelection(sem: ArrayList<String>) {
        val semesters: List<String> = sem

        binding.semesterSelectButton.setOnClickListener {
            MaterialDialog(this).show {
                title(text = "Select Semesters")
                listItemsMultiChoice(items = semesters) { _, _, items ->
                    val semesterTag = items.map {
                        covertSemNameToTag(it.toString())
                    }
                    selectedSemesters.clear()
                    selectedSemesters.addAll(semesterTag)
                    binding.selectedSemestersText.text = "Selected: ${selectedSemesters.joinToString(", ")}"
                }
                positiveButton(text = "OK")
                negativeButton(text = "Cancel")
            }
        }
    }

    private fun uploadBookData() {
        val bookCode = binding.addBookCode.text.toString()
        val bookCover = binding.addBookCover.text.toString()
        val bookCredit = binding.addBookCredit.text.toString()
        val bookDescription = binding.addBookDescription.text.toString()
        val bookName = binding.addBookName.text.toString()
        val bookTotalU = binding.addBookTotalUnit.text.toString()

        // Validate input fields
        if (bookCode.isBlank() || bookCover.isBlank() || bookName.isBlank() || bookTotalU.isBlank()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show()
            return
        }

        // Prepare data to be uploaded
        val bookData = hashMapOf(
            "BCode" to bookCode,
            "BCover" to bookCover,
            "BCredit" to bookCredit,
            "BDes" to bookDescription,
            "BName" to bookName,
            "BTotalU" to bookTotalU,
            "TimeStamp" to Timestamp.now(),
            "xDep" to selectedDepartments,
            "xSem" to selectedSemesters
        )

        // Upload data to Firestore
        db.collection("books").document(bookCode).set(bookData)
            .addOnSuccessListener {
                Toast.makeText(this, "Book added successfully", Toast.LENGTH_LONG).show()
                finish()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Failed to add book: ${e.message}", Toast.LENGTH_LONG).show()
            }
    }

}