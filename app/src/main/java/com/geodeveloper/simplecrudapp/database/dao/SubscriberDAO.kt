package com.geodeveloper.simplecrudapp.database.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.geodeveloper.simplecrudapp.database.tables.Subscriber

@Dao
interface SubscriberDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
      fun inserSubscriber(subscriber: Subscriber):Long

    @Update
     fun updateSubscriber(subscriber: Subscriber)

    @Delete
     fun deleteSubscriber(subscriber: Subscriber)

    @Query("DELETE FROM Subscriber_data_table")
     fun deleteAll()

    @Query("SELECT * FROM Subscriber_data_table")
     fun getAllSubscriber(): List<Subscriber>
}
