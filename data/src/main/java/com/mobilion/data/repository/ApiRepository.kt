package com.mobilion.data.repository

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.*
import com.mobilion.data.remote.response.Product
import timber.log.Timber
import javax.inject.Inject

class ApiRepository @Inject constructor(
    private val database: FirebaseDatabase
) {
    private val productsReference = database.getReference("products")

    fun fetchProducts(liveData: MutableLiveData<List<Product>>) {
        productsReference
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val productList: List<Product> = snapshot.children.map { dataSnapshot ->
                        dataSnapshot.getValue(Product::class.java)!!
                    }

                    liveData.postValue(productList)
                }

                override fun onCancelled(error: DatabaseError) {
                    Timber.e(error.message)
                    liveData.postValue(null)
                }
            })
    }

    fun findProduct(productId: String, liveData: MutableLiveData<Product>) {
        productsReference.child(productId).get()
            .addOnSuccessListener {
                Timber.i("Success : ${it.value}")
                liveData.postValue(it.getValue(Product::class.java))
            }
            .addOnFailureListener {
                Timber.e(it)
                liveData.postValue(null)
            }
    }

    fun addProduct(product: Product) {
        productsReference.child(product.id).setValue(product)
            .addOnSuccessListener { Timber.i("Success") }
            .addOnFailureListener { Timber.e(it) }
    }

    fun updateProduct(product: Product) {
        productsReference.child(product.id).setValue(product)
            .addOnSuccessListener { Timber.i("Success") }
            .addOnFailureListener { Timber.e(it) }
    }

    fun listenForQrRead(productId: String, liveData: MutableLiveData<Product>) {
        productsReference.child(productId)
            .addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    liveData.postValue(snapshot.getValue(Product::class.java))
                }

                override fun onCancelled(error: DatabaseError) {
                    Timber.e(error.message)
                    liveData.postValue(null)
                }
            })

        /* productsReference.child(productId).addChildEventListener(object : ChildEventListener {
             override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
             }

             override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                 liveData.postValue(snapshot.getValue(Product::class.java))
             }

             override fun onChildRemoved(snapshot: DataSnapshot) {
             }

             override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
             }

             override fun onCancelled(error: DatabaseError) {
             }
         })*/
    }
}
