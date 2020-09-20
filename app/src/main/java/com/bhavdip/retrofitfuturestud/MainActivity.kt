package com.bhavdip.retrofitfuturestud

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.bhavdip.retrofitfuturestud.data.ServiceGenerator
import com.bhavdip.retrofitfuturestud.data.apis.GitHubClient
import com.bhavdip.retrofitfuturestud.data.model.GitHubUserRepo
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        listView = findViewById(R.id.listview)
        progressBar = findViewById(R.id.progressBar)
        progressBar.visibility = View.VISIBLE

        val client: GitHubClient = ServiceGenerator.createService(GitHubClient::class.java)

        // Fetch a list of the Github repositories.
        val call =
            client.reposForUser(user = "bhavdip99")// You can change my username with your username and see your repos in list
        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(object : Callback<List<GitHubUserRepo>> {
            override fun onResponse(
                call: Call<List<GitHubUserRepo>>,
                response: Response<List<GitHubUserRepo>>
            ) {
                // The network call was a success and we got a response
                // TODO: use the repository list and display it
                if (!response.isSuccessful) return
                val repos = response.body()
                listView.adapter = ArrayAdapter(
                    this@MainActivity, android.R.layout.simple_list_item_1,
                    repos!!
                )
                progressBar.visibility = View.GONE
            }

            override fun onFailure(call: Call<List<GitHubUserRepo>>, t: Throwable) {
                // the network call was a failure
                // TODO: handle error
                progressBar.visibility = View.GONE
            }
        })
    }

    companion object {
        private val API_BASE_URL = "https://api.github.com/"
    }
}