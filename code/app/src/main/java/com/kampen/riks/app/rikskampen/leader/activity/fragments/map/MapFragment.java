package com.kampen.riks.app.rikskampen.leader.activity.fragments.map;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kampen.riks.app.rikskampen.R;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.map.Modal.PlacesDetails_Modal;
import com.kampen.riks.app.rikskampen.leader.activity.fragments.map.Response.PlacesResponse;
import com.kampen.riks.app.rikskampen.utils.Constants;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;
import io.nlopez.smartlocation.location.providers.LocationGooglePlayServicesProvider;
import noman.googleplaces.NRPlaces;
import noman.googleplaces.Place;
import noman.googleplaces.PlaceType;
import noman.googleplaces.PlacesException;
import noman.googleplaces.PlacesListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MapFragment extends Fragment implements PlacesListener,GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        CustomSupportMapFragment.OnMapFragmentReadyListener  {



    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private boolean mPermissionDenied = false;

    private SupportMapFragment mMapFragment;
    private GoogleMap mMap;

    private  Marker userMarker;

    private  Marker targetMarker;



    public MapFragment() {
        // Required empty public constructor
    }

    public static MapFragment newInstance() {
        MapFragment fragment = new MapFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_map, container, false);


        // create SupportMapFragment, and listen for onMapfragmentReady callback
        mMapFragment = CustomSupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.mapFragment, mMapFragment).commit();
         setRetainInstance(true);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mMap=null;
        userMarker=null;
        targetMarker=null;
    }

    @Override
    public void onPlacesFailure(final  PlacesException e) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), ""+e.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onPlacesStart() {

    }

    @Override
    public void onPlacesSuccess(final List<Place> places) {

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                try {

                    if (places != null && places.size() > 0) {
                        Random rnd = new Random(places.size() - 1);

                        int nearByRandomPlace = rnd.nextInt();

                        Place targetPlace = places.get(nearByRandomPlace);

                        LatLng latlng = new LatLng(targetPlace.getLatitude(), targetPlace.getLongitude());

                        if (targetMarker == null) {

                            MarkerOptions markerOptions = new MarkerOptions();
                            markerOptions.position(latlng);
                            markerOptions.title("Current Position");
                            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_start));
                            markerOptions.getPosition();
                            userMarker = mMap.addMarker(markerOptions);
                        } else {
                            userMarker.setPosition(latlng);
                        }
                    }

                }catch (IndexOutOfBoundsException ex)
                {
                    ex.toString();
                }

            }
        });

    }

    @Override
    public void onPlacesFinished() {

    }

    @Override
    public boolean onMyLocationButtonClick() {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        mMap = map;
        map.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        // button in upper right of map that zooms to current location when pressed
        map.getUiSettings().setMyLocationButtonEnabled(true);
        enableMyLocation();

        // zoom controls in lower right of map
        map.getUiSettings().setZoomControlsEnabled(true);

        /*mInitialMapBounds = loadMapMarkers(map);
        map.setOnMapLoadedCallback(new GoogleMap.OnMapLoadedCallback() {
            @Override
            public void onMapLoaded() {
                mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(mInitialMapBounds, 40));
            }
        });*/


        getCurrentLocation();



    }

    @Override
    public void onMapFragmentReady() {

        mMapFragment.getMapAsync(this);
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode != LOCATION_PERMISSION_REQUEST_CODE) {
            return;
        }

        if (PermissionUtils.isPermissionGranted(permissions, grantResults,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Enable the my location layer if the permission has been granted.
            enableMyLocation();
        } else {
            // Display the missing permission error dialog when the fragments resume.
            mPermissionDenied = true;
        }
    }


    //methods

    
    /**
     * Enables the My Location layer if the fine location permission has been granted.
     * // https://gist.github.com/MariusVolkhart/618a51bb09c4fc7f86a4
     */
    private void enableMyLocation() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission to access the location is missing.
            this.requestPermissions(new String[] {Manifest.permission.ACCESS_FINE_LOCATION},
                    LOCATION_PERMISSION_REQUEST_CODE);
        } else if (mMap != null) {
            // Access to the location has been granted to the app.
            mMap.setMyLocationEnabled(true);


        }
    }


    private void getCurrentLocation() {


         LocationGooglePlayServicesProvider provider;

        provider = new LocationGooglePlayServicesProvider();
        provider.setCheckLocationSettings(true);





        SmartLocation.with(getActivity()).location(provider)
                .config(LocationParams.NAVIGATION)
                .oneFix()
                .start(new OnLocationUpdatedListener() {
                    @Override
                    public void onLocationUpdated(final Location location) {

                        if(mMap!=null)
                        {
                            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());

                            if(userMarker ==null)
                            {

                                MarkerOptions markerOptions = new MarkerOptions();
                                markerOptions.position(latLng);
                                mMap.clear();
                                markerOptions.title("Current Position");
                                //markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_start));
                                markerOptions.getPosition();
                                userMarker = mMap.addMarker(markerOptions);
                            }
                            else
                            {
                                userMarker.setPosition(latLng);
                            }


                            //fetchStores(location.getLatitude() + "," + location.getLongitude());

                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    try {
                                        new NRPlaces.Builder()
                                                .listener(MapFragment.this)
                                                .key(Constants.MAP_KEY)
                                                .latlng(location.getLatitude(),location.getLongitude())
                                                .radius(1500)
                                                .type(PlaceType.GYM)
                                                .build()
                                                .execute();
                                    }catch (Exception ex)
                                    {
                                        ex.toString();
                                    }
                                }
                            });



                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userMarker.getPosition(), 14));
                        }

                    }
                });
    }















    private APIInterface apiService;
    ArrayList<PlacesResponse.CustomA> results;

    public ArrayList<PlacesDetails_Modal> details_modal;

    private void fetchStores(String location) {

        apiService = ApiClient.getClient().create(APIInterface.class);

        Call<PlacesResponse.Root> call = apiService.doPlaces(location,1000,PlaceType.GYM, ApiClient.GOOGLE_PLACE_API_KEY);
        call.enqueue(new Callback<PlacesResponse.Root>() {
            @Override
            public void onResponse(Call<PlacesResponse.Root> call, Response<PlacesResponse.Root> response) {
                PlacesResponse.Root root = (PlacesResponse.Root) response.body();


                if (response.isSuccessful()) {

                    switch (root.status) {
                        case "OK":

                            results = root.customA;

                            details_modal = new ArrayList<PlacesDetails_Modal>();
                            String photourl;



                            for (int i = 0; i < results.size(); i++) {

                                PlacesResponse.CustomA info = (PlacesResponse.CustomA) results.get(i);

                                String place_id = results.get(i).place_id;


                                if (results.get(i).photos != null) {

                                    String photo_reference = results.get(i).photos.get(0).photo_reference;

                                    photourl = ApiClient.base_url + "place/photo?maxwidth=100&photoreference=" + photo_reference +
                                            "&key=" + ApiClient.GOOGLE_PLACE_API_KEY;

                                } else {
                                    photourl = "NA";
                                }




                                Log.i("Coordinates  ", info.geometry.locationA.lat + " , " + info.geometry.locationA.lng);
                                Log.i("Names  ",info.name);

                            }

                            break;
                        case "ZERO_RESULTS":
                            Toast.makeText(getActivity(), "No matches found near you", Toast.LENGTH_SHORT).show();

                            break;
                        case "OVER_QUERY_LIMIT":
                            Toast.makeText(getActivity(), "You have reached the Daily Quota of Requests", Toast.LENGTH_SHORT).show();

                            break;
                        default:
                            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();

                            break;
                    }

                } else if (response.code() != 200) {
                    Toast.makeText(getActivity(), "Error " + response.code() + " found.", Toast.LENGTH_SHORT).show();
                }


            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Toast.makeText(getActivity(),"Error in Fetching Details,Please Refresh",Toast.LENGTH_SHORT).show();
                call.cancel();
            }
        });

    }

}
