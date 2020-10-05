package com.geodeveloper.simplecrudapp.database.tables

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.geodeveloper.simplecrudapp.Constants

@Entity(tableName = Constants.subscriberDataTable)
data class Subscriber(
    @ColumnInfo(name=Constants.subscriberID)
    @PrimaryKey(autoGenerate = true)
    val id:Int,
    @ColumnInfo(name=Constants.subscriberName)
    val name:String,
    @ColumnInfo(name=Constants.subscriberEmail)
    val email:String
)