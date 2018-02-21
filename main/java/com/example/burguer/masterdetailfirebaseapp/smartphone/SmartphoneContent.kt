package com.example.burguer.masterdetailfirebaseapp.smartphone

import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import android.content.ContentValues.TAG

class SmartphoneContent {

    //connection with firebase
    val dataBase = FirebaseDatabase.getInstance()
    val myRef = dataBase.getReference("smartphones")
    //mutable list with smartphones
    val smartphonesList: MutableList<Smartphone> = ArrayList()

    public fun getSmartphoneList(): MutableList<Smartphone> {
        return smartphonesList
    }//get the list of smartphones

    public fun getIdSmartphone(id: String):Smartphone {
        for(movile in smartphonesList) {
            if (movile.id == id) {
                return movile
            }
        }//loop in all the smartphone list
        return smartphonesList.get(0)  //get the first item
    }

    public fun loadSmartphoneList() {

        /*var movile = Smartphone("urlExample", "1", "luis 1","www.ociovertical.com")
        myRef.child("smartphones").push().setValue(movile)
        */

        var movile = Smartphone("https://i0.wp.com/computerchamberi.com/wp-content/uploads/2017/01/Bq-aquaris-U-plus.jpg?resize=128%2C128&ssl=1", "1", "BQ Aquarius","https://www.bq.com/es/smartphones")
        smartphonesList.add(movile)

        movile = Smartphone("https://rukminim1.flixcart.com/image/128/128/cases-covers/fit-to-use/u/z/n/cubix-hard-case-back-cover-for-sony-xperia-z1-ultra-thin-rubberized-matte-original-imadqa3x8hzyfkrq.jpeg?q=70", "2", "Sony Xperia","https://www.sonymobile.com/es/xperia/")
        smartphonesList.add(movile)

        movile = Smartphone("https://i1.wp.com/www.mojandroid.sk/wp-content/uploads/2017/06/huawei-p10-lite.png?fit=128%2C128&ssl=1", "3", "Huawei P10 lite","http://www.huaweispain.com/smartphones/huawei-p10-lite/")
        smartphonesList.add(movile)

        movile = Smartphone("http://www.qdossound.com/files/styles/swatch_large/public/user-files/variants/cases/images/09-17/iphone-8-Fang_Gua_Hua_-4_SQUARE.jpg?itok=NaL-lXIM", "4", "Iphone X","https://www.apple.com/es/iphone/")
        smartphonesList.add(movile)
        //val movileData = dataReference

        //dataReference.child("smartphones").addListenerForSingleValueEvent(dataReference)
    }//load all the list

}//Content of smartphones