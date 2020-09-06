package org.afdhal_fa.githubuserapp.adapter

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_list_user.view.*
import org.afdhal_fa.githubuserapp.R
import org.afdhal_fa.githubuserapp.fragment.ListUserFragment
import org.afdhal_fa.githubuserapp.util.UserGithub
import java.util.*


class ListUserGithubAdapter : RecyclerView.Adapter<ListUserGithubAdapter.ListViewHolder>() {
    private var mBundle: Bundle? = Bundle()


    private val listUserGithub = ArrayList<UserGithub>()

    fun setData(item: ArrayList<UserGithub>) {
        listUserGithub.clear()
        listUserGithub.addAll(item)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        return ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        )
    }

    override fun getItemCount(): Int = listUserGithub.size

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listUserGithub[position])
        holder.itemView.setOnClickListener(
            CustomOnItemClickListener(
                position,
                object : CustomOnItemClickListener.OnItemClickCallback {

                    override fun onItemClicked(view: View?, position: Int) {
                        mBundle?.putString(
                            ListUserFragment.EXTRA_NAME,
                            listUserGithub[position].name
                        )
                        mBundle?.putString(
                            ListUserFragment.EXTRA_USERNAME,
                            listUserGithub[position].username
                        )
                        mBundle?.putString(
                            ListUserFragment.EXTRA_PHOTO,
                            listUserGithub[position].photo
                        )
                        mBundle?.putString(
                            ListUserFragment.EXTRA_COMPANY,
                            listUserGithub[position].company
                        )
                        mBundle?.putString(
                            ListUserFragment.EXTRA_LOCATION,
                            listUserGithub[position].location
                        )
                        mBundle?.putString(
                            ListUserFragment.EXTRA_REPOSITORY,
                            listUserGithub[position].repository.toString()
                        )
                        mBundle?.putString(
                            ListUserFragment.EXTRA_FOLLOWER,
                            listUserGithub[position].follower.toString()
                        )
                        mBundle?.putString(
                            ListUserFragment.EXTRA_FOLLOWING,
                            listUserGithub[position].following.toString()
                        )

                        view?.findNavController()
                            ?.navigate(R.id.action_listUserFragment_to_detailFragment, mBundle)
                    }

                })
        )
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(userGithub: UserGithub) {

            val imageResource: Int =
                itemView.getResources()
                    .getIdentifier(userGithub.photo, null, itemView.context.packageName)

            val res: Drawable = itemView.getResources().getDrawable(imageResource)
            with(itemView) {
                Glide.with(itemView.context)
                    .load(imageResource)
                    .apply(RequestOptions().override(64, 64))
                    .into(img_profile)

                txt_name.text = userGithub.name
                txt_username.text = userGithub.username
            }
        }
    }

}

