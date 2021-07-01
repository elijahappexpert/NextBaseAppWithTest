package com.example.nextbaseapp.MapView

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.nextbaseapp.Mvvm.DataViewModel
import com.example.nextbaseapp.Mvvm.DataViewModelFactory
import com.example.nextbaseapp.R
import com.example.nextbaseapp.di.DaggerAppComponent
import com.example.nextbaseapp.model.Data
import com.example.nextbaseapp.model.Journey_model
import com.example.nextbaseapp.repo.Repository
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject
/**
 * A simple [Fragment] subclass.
 * Use the [MapViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MapViewFragment : Fragment() {

    private lateinit var mMap : GoogleMap
    private var mapReady = false

    @Inject
    lateinit var repository: Repository

    private val dataViewModelFactory by lazy {
        DataViewModelFactory(repository)
    }

    private val viewmodel by lazy {
        ViewModelProvider(this, dataViewModelFactory)
            .get(DataViewModel::class.java)
    }

    private lateinit var dataList: List<Data>



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_map_view, container, false)
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mapFragment) as SupportMapFragment
        mapFragment.getMapAsync { googleMap ->
            mMap = googleMap
            mapReady = true


        }
        return rootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewmodel.ResponseList.observe(this.viewLifecycleOwner,object : Observer<Journey_model> {
            override fun onChanged(t: Journey_model?) {
                if (t != null) {
                        dataList =t.data
                    if(dataList != null)
                        updateMap()
                    }
                }
        })

    }

    override fun onAttach(context: Context) {
        DaggerAppComponent.create().injectMapViewFragment(this)
        //(getContext() as MyNextBaseApp).appComponent.injectMapViewFragment(this)
        super.onAttach(context)

    }


    internal fun updateMap() {
        if(dataList.isNotEmpty())
        if (mapReady && dataList != null) {
            dataList.forEach {
                    data ->
                if (data.lon != null && data.lat != null) {
                    Log.d("Map Data Longitude", data.lon.toString())
                    Log.d("Map Data Latitude", data.lat.toString())
                    val marker = LatLng(data.lat.toDouble(), data.lon.toDouble())
                    mMap.addMarker(MarkerOptions().position(marker).title(data.toString()))
                }

            }
        }
    }


}


