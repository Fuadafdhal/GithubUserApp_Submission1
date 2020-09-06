package org.afdhal_fa.githubuserapp.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_list_user.*
import org.afdhal_fa.githubuserapp.R

/**
 * A simple [Fragment] subclass.
 */
class DetailFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val name = arguments?.getString(ListUserFragment.EXTRA_NAME)
        val username = arguments?.getString(ListUserFragment.EXTRA_USERNAME)
        val photo = arguments?.getString(ListUserFragment.EXTRA_PHOTO)
        val company = arguments?.getString(ListUserFragment.EXTRA_COMPANY)
        val location = arguments?.getString(ListUserFragment.EXTRA_LOCATION)
        val repository = arguments?.getString(ListUserFragment.EXTRA_REPOSITORY)
        val follower = arguments?.getString(ListUserFragment.EXTRA_FOLLOWER)
        val following = arguments?.getString(ListUserFragment.EXTRA_FOLLOWING)

        txt_name.text = String.format("Name : %s", name)
        txt_username.text = String.format("@%s", username)

        val imageResource: Int = getResources()
            .getIdentifier(photo, null, context?.packageName)

        val res: Drawable = getResources().getDrawable(imageResource)
        Glide.with(requireActivity())
            .load(res)
            .into(img_profile)

//        txt_company.text = String.format("Company : %s", company)
//        txt_location.text = String.format("Location : %s", location)
//        txt_repository.text = String.format("Repository : %s", repository)
        txt_follower.text = String.format("Follower : %s", follower)
        txt_following.text = String.format("Following : %s", following)

    }
}
