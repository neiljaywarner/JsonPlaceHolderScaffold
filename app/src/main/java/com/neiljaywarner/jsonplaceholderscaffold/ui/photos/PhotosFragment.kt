package com.neiljaywarner.jsonplaceholderscaffold.ui.photos

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neiljaywarner.jsonplaceholderscaffold.R
import com.neiljaywarner.jsonplaceholderscaffold.model.Photo
import kotlinx.android.synthetic.main.photos_fragment.*
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
        viewModel.getPhotos(2)?.observe(this, Observer { photos: List<Photo>? ->
            photos?.let {
                Log.d(TAG, "***photo1 title=${it[0].title}")
                // TODO: set adapter to horizontal recyclerview
            }
        })
    }

}
