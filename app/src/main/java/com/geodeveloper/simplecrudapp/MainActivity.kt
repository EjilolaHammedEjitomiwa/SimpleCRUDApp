package com.geodeveloper.simplecrudapp

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.geodeveloper.simplecrudapp.adapter.SubscriberAdapter
import com.geodeveloper.simplecrudapp.database.MyDatabase
import com.geodeveloper.simplecrudapp.database.dao.SubscriberDAO
import com.geodeveloper.simplecrudapp.database.tables.Subscriber
import kotlinx.android.synthetic.main.activity_main.*

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    var allSubscriber = ArrayList<Subscriber>()
    var adapter:SubscriberAdapter? = null
    var dao:SubscriberDAO? = null
    var saveOrUpdateTag = "save"
    var clearOrDeleteTag = "clear"
    var subcriberID = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = SubscriberAdapter(this,allSubscriber)
        main_recyclerView.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(this)
        main_recyclerView.layoutManager = layoutManager
        main_recyclerView.adapter = adapter

        dao = MyDatabase.getInstance(application).subscriberDAO

        main_saveBtn.setOnClickListener {
            val name = main_nameEditext.text.toString()
            val email = main_emailEditext.text.toString()
            if ( email.isEmpty() || name.isEmpty() ) {
                Toast.makeText(this,"Empty field",Toast.LENGTH_LONG).show()
            }
            else{
                main_emailEditext.text.clear()
                main_nameEditext.text.clear()
                if(saveOrUpdateTag == "save"){
                   saveSubscriber().execute(Subscriber(0,name,email))
                }else{
                    updateSubscriber().execute(Subscriber(subcriberID,name,email))
                }
            }
        }
        main_clearAllBtn.setOnClickListener {
            if(clearOrDeleteTag == "clear"){
                clearAllSubscribers().execute()
            }
        }

        showAllSubscribers().execute()
    }

    inner class saveSubscriber():AsyncTask<Subscriber,Void,Void>(){
        override fun doInBackground(vararg p0: Subscriber?): Void ?{
            dao!!.inserSubscriber(p0[0]!!)
            return null
        }
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            showAllSubscribers().execute()
        }
    }
    inner class updateSubscriber():AsyncTask<Subscriber,Void,Void>(){
        override fun doInBackground(vararg p0: Subscriber?): Void ?{
            dao!!.updateSubscriber(p0[0]!!)
            return null
        }
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            main_saveBtn.text = "Save"
            saveOrUpdateTag = "save"
            showAllSubscribers().execute()
        }
    }
    inner class showAllSubscribers():AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg p0: Void?): Void? {
            val all =  dao!!.getAllSubscriber()
            allSubscriber.clear()
            if(all.isNotEmpty()){
                for (i in all){
                    allSubscriber.add(i)
                }
                allSubscriber.reverse()
                Log.i("MYTAG",allSubscriber.toString())
            }

            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            adapter!!.notifyDataSetChanged()
        }

    }
    inner class clearAllSubscribers():AsyncTask<Void,Void,Void>(){
        override fun doInBackground(vararg p0: Void?): Void? {
            dao!!.deleteAll()
            return null
        }
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            showAllSubscribers().execute()
        }

    }
    inner class deleteSubscriber():AsyncTask<Subscriber,Void,Void>(){
        override fun doInBackground(vararg p0: Subscriber?): Void ?{
            dao!!.deleteSubscriber(p0[0]!!)
            return null
        }
        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            showAllSubscribers().execute()
        }
    }
}