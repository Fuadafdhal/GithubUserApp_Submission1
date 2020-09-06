package org.afdhal_fa.githubuserapp.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.afdhal_fa.githubuserapp.util.UserGithub
import org.json.JSONObject
import timber.log.Timber
import java.io.IOException
import java.io.InputStream

class MainViewModel : ViewModel() {
    private val lisUserGithub: MutableLiveData<ArrayList<UserGithub>> =
        MutableLiveData<ArrayList<UserGithub>>()

    fun readJSONFile(inputStream: InputStream) {
        val listItems: ArrayList<UserGithub> = ArrayList<UserGithub>()
        var json: String? = null
        try {

            json = inputStream.bufferedReader().use { it.readText() }
            Timber.i("This data JSON: $json")
            val jsonObject = JSONObject(json)
            val dataArray = jsonObject.getJSONArray("users")
            for (i in 0 until dataArray.length()) {
                val users: JSONObject = dataArray.getJSONObject(i)

                val name = users.getString("name")
                val username = users.getString("username")
                val avatar = users.getString("avatar")
                val company = users.getString("company")
                val location = users.getString("location")
                val repository = users.getInt("repository")
                val follower = users.getInt("follower")
                val following = users.getInt("following")

                val userGithub = UserGithub(
                    name, username, avatar, company, location, repository, follower, following
                )

                listItems.add(userGithub)
            }
            lisUserGithub.postValue(listItems)
        } catch (e: IOException) {
            Timber.i(e)
        }
    }

    fun getUserGithub(): LiveData<ArrayList<UserGithub>> {
        return lisUserGithub
    }
}