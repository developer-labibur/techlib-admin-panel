package com.rudhashi.techlibadminpanel.ui

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore
import com.rudhashi.techlibadminpanel.R
import com.rudhashi.techlibadminpanel.databinding.ActivityAddNewSemesterBinding
import com.rudhashi.techlibadminpanel.utils.ConvertFB.convertDepToAbbr
import com.rudhashi.techlibadminpanel.utils.ConvertFB.convertSemNoToSemNo
import com.rudhashi.techlibadminpanel.utils.MakeFirebaseEasy.getAllDepartmentName
import com.rudhashi.techlibadminpanel.utils.MakeFirebaseEasy.getSemesterData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AddNewSemesterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddNewSemesterBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNewSemesterBinding.inflate(layoutInflater)
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


        // Initialize Firestore
        firestore = FirebaseFirestore.getInstance()

        val adapterDepartment: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.item_dropdown,
            getAllDepartmentName(ArrayList())
        )
        val adapterSemester: ArrayAdapter<String> = ArrayAdapter<String>(
            this,
            R.layout.item_dropdown,
            getSemesterData(ArrayList())
        )

        binding.apply {
            // Set up department and semester adapters
            editProDepartment.setAdapter(adapterDepartment)
            editProSemester.setAdapter(adapterSemester)

            // Back Button Action
            btnBack.setOnClickListener { finish() }

            // Add Button Action
            btnAdd.setOnClickListener {
                val selectedDep = editProDepartment.text.toString()
                val selectedSem = editProSemester.text.toString()
                val pdfLink = editProName.text.toString()

                if (selectedDep.isEmpty() || selectedSem.isEmpty()) {
                    Toast.makeText(this@AddNewSemesterActivity, "Department and Semester fields are required!", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }

                val SAbbr = convertDepToAbbr(selectedDep)
                val SSem = convertSemNoToSemNo(selectedSem)
                val documentID = "$SSem$SAbbr"
                val STag = "$SAbbr$SSem"

                val semesterData = hashMapOf(
                    "SAbbr" to SAbbr,
                    "SDep" to selectedDep,
                    "SNo" to selectedSem,
                    "SPdf" to pdfLink,
                    "SSem" to SSem,
                    "STag" to STag
                )

                addSemesterToFirestore(documentID, semesterData)
            }
        }

    }

    private fun addSemesterToFirestore(documentID: String, semesterData: HashMap<String, String>) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                firestore.collection("semester")
                    .document(documentID)
                    .set(semesterData)
                    .addOnSuccessListener {
                        runOnUiThread {
                            Toast.makeText(this@AddNewSemesterActivity, "Semester Added Successfully!", Toast.LENGTH_SHORT).show()
                        }
                    }
                    .addOnFailureListener { e ->
                        runOnUiThread {
                            Toast.makeText(this@AddNewSemesterActivity, "Failed to Add: ${e.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } catch (e: Exception) {
                runOnUiThread {
                    Toast.makeText(this@AddNewSemesterActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

}