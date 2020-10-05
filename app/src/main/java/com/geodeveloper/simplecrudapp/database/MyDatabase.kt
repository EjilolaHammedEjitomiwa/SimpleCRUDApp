package com.geodeveloper.simplecrudapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.geodeveloper.simplecrudapp.database.dao.SubscriberDAO
import com.geodeveloper.simplecrudapp.database.tables.Subscriber


@Database(entities = [Subscriber::class],version = 1)
abstract  class MyDatabase : RoomDatabase(){
    abstract val subscriberDAO: SubscriberDAO
    companion object{
        @Volatile
        private var INSTANCE: MyDatabase? = null
        fun getInstance(context: Context): MyDatabase {
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance =  Room.databaseBuilder(context.applicationContext,MyDatabase::class.java,"Subscriber_data_databae").build()
                }
                return instance
            }
        }
    }
}