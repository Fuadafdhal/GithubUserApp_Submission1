package org.afdhal_fa.githubuserapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_list_user.*
import org.afdhal_fa.githubuserapp.R
import org.afdhal_fa.githubuserapp.adapter.ListUserGithubAdapter
import org.afdhal_fa.githubuserapp.view_model.MainViewModel
import java.io.InputStream


/**
 * A simple [Fragment] subclass.
 */
class ListUserFragment : Fragment() {

    companion object {
        val EXTRA_NAME = "extra_name"
        val EXTRA_USERNAME = "extra_username"
        val EXTRA_PHOTO = "extra_avatar"
        val EXTRA_COMPANY = "extra_company"
        val EXTRA_LOCATION = "extra_location"
        val EXTRA_REPOSITORY = "extra_repository"
        val EXTRA_FOLLOWER = "extra_follower"
        val EXTRA_FOLLOWING = "extra_following"
    }

    lateinit var adapter: ListUserGithubAdapter
    lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ListUserGithubAdapter()
        adapter.notifyDataSetChanged()

        rv_listUser.setHasFixedSize(true)
        rv_listUser.layoutManager = LinearLayoutManager(activity)
        rv_listUser.adapter = adapter

        mainViewModel = ViewModelProvider(
            requireActivity(),
            ViewModelProvider.NewInstanceFactory()
        ).get(MainViewModel::class.java)

        mainViewModel.getUserGithub().observe(requireActivity(), Observer { userGithub ->
            if (userGithub != null) {
                adapter.setData(userGithub)
            }
        })

        val inputStream: InputStream = requireActivity().assets.open("githubuser.json")
        mainViewModel.readJSONFile(inputStream)
    }

}
