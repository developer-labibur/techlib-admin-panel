package com.rudhashi.techlibadminpanel.utils

import android.content.Intent
import android.util.Log
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.toObject
import com.rudhashi.techlibadminpanel.model.Department
import com.rudhashi.techlibadminpanel.model.Semester
import com.rudhashi.techlibadminpanel.model.SemesterTotal

object MakeFirebaseEasy {

    fun getAllDepartment(allDepartment: ArrayList<String>): ArrayList<String> {
        FirebaseFirestore.getInstance().collection ("department")
            .orderBy("DCode", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val xDep = document.toObject(Department::class.java)
                    val dName = xDep.DName
                    val dCode = xDep.DCode
                    if (!((dCode == "98") || (dCode == "99"))) {
                        val department = "$dCode => $dName"
                        allDepartment.add(department)
                    }

                }
            }
            .addOnFailureListener { exception ->
                Log.e("MakeFirebaseEasy", "Error loading units: ", exception)
            }
        return allDepartment
    }

    fun getAllSemester(allDepartment: ArrayList<String>): ArrayList<String> {
        FirebaseFirestore.getInstance().collection ("semester").get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val xSem = document.toObject(Semester::class.java)
                    val sDep = xSem.SDep
                    val sNo = xSem.SNo
                    val semester = "$sNo => $sDep"
                    allDepartment.add(semester)
                }
            }
            .addOnFailureListener { exception ->
                Log.e("MakeFirebaseEasy", "Error loading units: ", exception)
            }
        return allDepartment
    }

    fun getSemesterData(allSemester: ArrayList<String>): ArrayList<String> {
        FirebaseFirestore.getInstance().collection("semester_total")
            .get().addOnSuccessListener { querySnapshot ->
                for (document in querySnapshot) {
                    val department = document.toObject(SemesterTotal::class.java)
                    allSemester.add(department.STName)
                }
            }

        return allSemester
    }

    fun getAllDepartmentName(allDepartment: ArrayList<String>): ArrayList<String> {
        FirebaseFirestore.getInstance().collection ("department")
            .orderBy("DCode", Query.Direction.ASCENDING)
            .get()
            .addOnSuccessListener { documents ->
                for (document in documents) {
                    val xDep = document.toObject(Department::class.java)
                    val dName = xDep.DName
                    val dCode = xDep.DCode
                    if (!((dCode == "98") || (dCode == "99"))) {
                        allDepartment.add(dName)
                    }

                }
            }
            .addOnFailureListener { exception ->
                Log.e("MakeFirebaseEasy", "Error loading units: ", exception)
            }

        return allDepartment
    }
}