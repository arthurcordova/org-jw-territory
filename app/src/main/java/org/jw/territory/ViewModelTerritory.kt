package org.jw.territory

import android.util.Log
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import org.jw.territory.adapter.TerritoryAdapter
import org.jw.territory.data.Territory

class ViewModelTerritory {


    companion object {

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("territory")


        fun territoriesForMapsView(googleMap: GoogleMap) {

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val list = populateTerritory(dataSnapshot = dataSnapshot)

                    list.forEach {

                        if (it.latitude != null && it.longitude != null) {
                            val pin = LatLng(it.latitude!!, it.longitude!!)
                            googleMap.addMarker(MarkerOptions().position(pin).title(it.name))
                        }
                    }

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })
        }


        fun territoriesForListView(adapter: TerritoryAdapter) {

            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {

                    val list = populateTerritory(dataSnapshot = dataSnapshot)
                    adapter.filter(list)

                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })
        }


        private fun populateTerritory(dataSnapshot: DataSnapshot) : MutableList<Territory> {

            val list = mutableListOf<Territory>()

            for (territorySnapshot in dataSnapshot.children) {
                val territory = Territory()
                territory.uid = territorySnapshot.key
                territory.name = territorySnapshot.child("name").value as String?
                territory.latitude = territorySnapshot.child("latitude").value as Double?
                territory.longitude = territorySnapshot.child("longitude").value as Double?

//                territorySnapshot.child("location").apply {
                    ////                            val location = Location(
////                                    latitude = child("latitude").value as Float?,
////                                    longitude = child("longitude").value as Float?,
////                                    street = child("street").value as String?,
////                                    number = child("number").value as Int?,
////                                    district = child("district").value as String
////                            )
////                            territory.location = location
////                        }

                list.add(territory)
            }

            return list

        }

    }

}