package com.example.burguer.masterdetailfirebaseapp

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.support.design.widget.Snackbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.burguer.masterdetailfirebaseapp.dummy.DummyContent
import com.example.burguer.masterdetailfirebaseapp.smartphone.Smartphone
import com.example.burguer.masterdetailfirebaseapp.smartphone.SmartphoneContent
import com.google.firebase.database.*

import kotlinx.android.synthetic.main.activity_smartphone_list.*
import kotlinx.android.synthetic.main.smartphone_list_content.view.*

import kotlinx.android.synthetic.main.smartphone_list.*
class smartphoneListActivity : AppCompatActivity() {
    private var mTwoPane: Boolean = false

    //variable for get the list of smartphones
    var smartphones = SmartphoneContent()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //connection with firebase
        val dataBase = FirebaseDatabase.getInstance()
        val myRef = dataBase.getReference("smartphones")

        //load the list
        smartphones.loadSmartphoneList()
        //send data
        /*
        var movile = Smartphone("https://i0.wp.com/computerchamberi.com/wp-content/uploads/2017/01/Bq-aquaris-U-plus.jpg?resize=128%2C128&ssl=1", "1", "BQ Aquarius","https://www.bq.com/es/smartphones")
        myRef.child("smartphones").push().setValue(movile)
        */
        //end send data
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                println("Cambios en los datos")

                for (objj  in dataSnapshot.children){
                    smartphones.loadSmartphoneList()
                }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }//end listener

        myRef.child("smartphones").addValueEventListener(menuListener) //end of all the listener

        setContentView(R.layout.activity_smartphone_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener { view ->
            Snackbar.make(view,R.string.infoApp, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        if (smartphone_detail_container != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true
        }

        setupRecyclerView(smartphone_list)
    }

    private fun setupRecyclerView(recyclerView: RecyclerView) {
        //get the list to show it
        recyclerView.adapter = SimpleItemRecyclerViewAdapter(this, smartphones.getSmartphoneList(), mTwoPane)
    }

    class SimpleItemRecyclerViewAdapter(private val mParentActivity: smartphoneListActivity,
                                        private val mValues: MutableList<Smartphone>,// changed
                                        private val mTwoPane: Boolean) :
            RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder>() {

        private val mOnClickListener: View.OnClickListener

        init {
            mOnClickListener = View.OnClickListener { v ->
                //casting to smartphone class to use its methods
                val item = v.tag as Smartphone
                if (mTwoPane) {
                    val fragment = smartphoneDetailFragment().apply {
                        arguments = Bundle()
                        arguments.putString(smartphoneDetailFragment.ARG_ITEM_ID, item.id)
                    }
                    mParentActivity.supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.smartphone_detail_container, fragment)
                            .commit()
                } else {
                    val intent = Intent(v.context, smartphoneDetailActivity::class.java).apply {
                        putExtra(smartphoneDetailFragment.ARG_ITEM_ID, item.id)
                    }
                    v.context.startActivity(intent)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.smartphone_list_content, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = mValues[position]
            holder.mIdView.text = item.id
            holder.mContentView.text = item.nombre
            Glide.with(holder.imageSmartphone).load(item.foto).into(holder.imageSmartphone)

            with(holder.itemView) {
                tag = item
                setOnClickListener(mOnClickListener)
            }
        }

        override fun getItemCount(): Int {
            return mValues.size
        }

        inner class ViewHolder(mView: View) : RecyclerView.ViewHolder(mView) {
            val mIdView: TextView = mView.id_text
            val mContentView: TextView = mView.content
            val imageSmartphone: ImageView = mView.imageSmartphone
        }
    }
}
