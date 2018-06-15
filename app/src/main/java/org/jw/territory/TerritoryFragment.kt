package org.jw.territory

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_territory.view.*
import org.jw.territory.adapter.TerritoryAdapter
import org.jw.territory.data.Territory
import java.util.HashMap

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class TerritoryFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_territory, container, false).apply {
            val llm = LinearLayoutManager(context)
            llm.orientation = LinearLayoutManager.VERTICAL

            val list = mutableListOf<String>()
            list.add(" ")
            list.add(" ")
            list.add(" ")

            val adapter = TerritoryAdapter(list, { position: Int ->

//                listContract.removeAt(position)
//                adapterContract.filter(listContract)

            })
            recyclerViewTeritory.layoutManager = llm
            recyclerViewTeritory.itemAnimator = DefaultItemAnimator()
            recyclerViewTeritory.adapter = adapter

            val database = FirebaseDatabase.getInstance()
            val myRef = database.getReference("territory")

//        myRef.setValue("Território 01")

//            list = mutableListOf()



            myRef.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(dataSnapshot: DataSnapshot) {
                    var items = dataSnapshot.getValue(true) as HashMap<*, *>



                    items.forEach {

                        val territory = Territory()
                        territory.uid = it.key as String?

                        Log.d(TAG, "Value is: ${it.key}}")
                        val l = it.value as HashMap<*, *>
                        l.forEach{
                            Log.d(TAG, "Value is: ${it.key}}")
                        }



//                        list.add(territory)
                    }

//                Log.d(TAG, "Value is: " + value!!)
                }

                override fun onCancelled(error: DatabaseError) {
                    // Failed to read value
                    Log.w(TAG, "Failed to read value.", error.toException())
                }
            })

        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {

        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TerritoryFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                TerritoryFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
