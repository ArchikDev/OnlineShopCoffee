package com.ar4uk.onlineshopcoffee.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ar4uk.onlineshopcoffee.Model.CategoryModel
import com.ar4uk.onlineshopcoffee.Model.ItemsModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class MainViewModel:ViewModel() {
    private val firebaseDatabase = FirebaseDatabase.getInstance()

    private val _category = MutableLiveData<MutableList<CategoryModel>>()
    private val _popular = MutableLiveData<MutableList<ItemsModel>>()

    val category: LiveData<MutableList<CategoryModel>> = _category
    val popular: LiveData<MutableList<ItemsModel>> = _popular

    fun loadCategory() {
        firebaseDatabase
            .getReference("Category")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lists = mutableListOf<CategoryModel>()

                    for (childSnapshot in snapshot.children) {
                        val list = childSnapshot.getValue(CategoryModel::class.java)

                        if (list != null) {
                            lists.add(list)
                        }
                    }

                    _category.value = lists
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }

    fun loadPopular() {
        firebaseDatabase
            .getReference("Items")
            .addValueEventListener(object: ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val lists = mutableListOf<ItemsModel>()

                    for (childSnapshot in snapshot.children) {
                        val list = childSnapshot.getValue(ItemsModel::class.java)

                        if (list != null) {
                            lists.add(list)
                        }
                    }

                    _popular.value = lists
                }

                override fun onCancelled(error: DatabaseError) {
                    TODO("Not yet implemented")
                }
            })
    }
}