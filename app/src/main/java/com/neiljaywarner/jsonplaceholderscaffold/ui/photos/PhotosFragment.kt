package com.neiljaywarner.jsonplaceholderscaffold.ui.photos

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neiljaywarner.jsonplaceholderscaffold.JPHSApplication
import com.neiljaywarner.jsonplaceholderscaffold.R
import com.neiljaywarner.jsonplaceholderscaffold.model.Photo
import kotlinx.android.synthetic.main.photos_fragment.*
import org.jetbrains.anko.indeterminateProgressDialog
import org.jetbrains.anko.progressDialog
import org.koin.android.viewmodel.ext.android.viewModel

class PhotosFragment : Fragment() {

    companion object {
        fun newInstance() = PhotosFragment()
        val TAG = PhotosFragment::class.java.simpleName
    }

    val viewModel : PhotosViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.photos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
        /*
        var dead = false
        if (activity == null) {
            dead = true
            return
        }
        activity?.let {
            dead = it.isDestroyed || it.isFinishing
        }
        if (dead) {
            return
        }
                val dialog = JPHSApplication.instance.indeterminateProgressDialog(message = "Please wait a bit…", title = "Fetching data")

        */
        Log.d("NJW", "***creating fragment")
        showLoadingDialog(true)
        viewModel.getPhotos(2).observe(this, Observer { photos: List<Photo>? ->
            photos?.let {
                Log.d(TAG, "***back from observer: photo1 title=${it[0].title}")
                // TODO: set adapter to horizontal recyclerview
            }
        })


        viewModel.isLoading().observe(this, Observer { isLoading: Boolean? ->
            showLoadingDialog(isLoading == true)
        })

        // TODO: See https://blog.oozou.com/calling-web-service-with-android-architecture-component-8de864800a93
    }

    private fun showLoadingDialog(show: Boolean) {
        // wow, this network call only took 1.6 seconds, so spinner was fast.
        Log.d("NJW", "***showLoadingDialog$show")
        if (show) progressBar.visibility = View.VISIBLE else progressBar.visibility = View.GONE
    }

}
