package org.jw.territory

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem

import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.ValueEventListener
import org.jw.territory.data.Territory
import java.util.HashMap


const val TAG = "FIREBASE"

class MainActivity : AppCompatActivity() {

    lateinit var list: MutableList<Territory>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("territory")
        fab.setOnClickListener { view ->


        }
//        myRef.setValue("Território 01")

        list = mutableListOf()



        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                var items = dataSnapshot.getValue(true) as HashMap<*, *>



                items.forEach {

                    val territory = Territory()
                    territory.uid = it.key as String?

                    Log.d(TAG, "Value is: ${it.key}}")
                    val l = it.value as HashMap<*,*>
                    l.forEach{
                        Log.d(TAG, "Value is: ${it.key}}")
                    }



                    list.add(territory)
                }

//                Log.d(TAG, "Value is: " + value!!)
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
