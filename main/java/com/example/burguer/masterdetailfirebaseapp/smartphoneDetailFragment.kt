package com.example.burguer.masterdetailfirebaseapp

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.burguer.masterdetailfirebaseapp.dummy.DummyContent
import com.example.burguer.masterdetailfirebaseapp.smartphone.Smartphone
import com.example.burguer.masterdetailfirebaseapp.smartphone.SmartphoneContent
import kotlinx.android.synthetic.main.activity_smartphone_detail.*
import kotlinx.android.synthetic.main.activity_smartphone_detail.view.*
import kotlinx.android.synthetic.main.smartphone_detail.view.*
import android.content.Intent
import android.net.Uri

class smartphoneDetailFragment : Fragment() {

    //private var mItem: DummyContent.DummyItem? = null

    //content of smartphones
    private var mItem: Smartphone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //get the content
        var movile = SmartphoneContent()
        movile.loadSmartphoneList()

        if (arguments.containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            //mItem = DummyContent.ITEM_MAP[arguments.getString(ARG_ITEM_ID)]

            print(arguments.getInt(ARG_ITEM_ID))
            mItem = movile.getIdSmartphone(arguments.getString(ARG_ITEM_ID))

            mItem?.let {
                activity.toolbar_layout?.title = it.nombre
                Glide.with(activity.smartphoneImage2).load(it.foto).into(activity.smartphoneImage2)


            } //show the name of smartphone
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.smartphone_detail, container, false)

        // Show the dummy content as text in a TextView.
        mItem?.let {
            rootView.smartphone_detail.text = it.web
            val urlToGo = it.web
            rootView.smartphone_detail.setOnClickListener {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(urlToGo))
                startActivity(intent)
            }

        }

        return rootView
    }

    companion object {
        /**
         * The fragment argument representing the item ID that this fragment
         * represents.
         */
        const val ARG_ITEM_ID = "item_id"
    }
}
