package com.kampen.riks.app.rikskampen.leader.activity.fragments.map;

import android.Manifest;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.akexorcist.googledirection.DirectionCallback;
import com.akexorcist.googledirection.GoogleDirection;
import com.akexorcist.googledirection.constant.TransportMode;
import com.akexorcist.googledirection.model.Direction;
import com.akexorcist.googledirection.model.Leg;
import com.akexorcist.googledirection.model.Route;
import com.akexorcist.googledirection.model.Step;
import com.akexorcist.googledirection.util.DirectionConverter;


import com.google.android.gms.maps.CameraUpdate;import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
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

import static android.location.GpsStatus.GPS_EVENT_STARTED;
import static android.location.GpsStatus.GPS_EVENT_STOPPED;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MapFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MapFragment extends Fragment implements PlacesListener, GoogleMap.OnMyLocationButtonClickListener,
        OnMapReadyCallback,
        ActivityCompat.OnRequestPermissionsResultCallback,
        CustomSupportMapFragment.OnMapFragmentReadyListener , GoogleMap.OnMarkerClickListener  {


    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    private boolean mPermissionDenied = false;

    private SupportMapFragment mMapFragment;

    private GoogleMap mMap;

    private View mStar1Button, mStar2Button, mStar3Button;

    private View mStarContainer;

    private Marker userMarker;

    private ArrayList<Marker> targetMarkerList=new ArrayList<>();


    private int mStarClicked = 1;


    private  View mStarItem1,mStarItem2,mStarItem3,mStarItem4,mStarItem5;

    private  View chasingButton;


    private List<Place>  mPlaces;

    private List<Polyline> polylines;




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
        View view = inflater.inflate(R.layout.fragment_map, container, false);


        // create SupportMapFragment, and listen for onMapfragmentReady callback
        mMapFragment = CustomSupportMapFragment.newInstance();
        getChildFragmentManager().beginTransaction().replace(R.id.mapFragment, mMapFragment).commit();
        setRetainInstance(true);
        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        mStarItem1 = view.findViewById(R.id.starItem1);
        mStarItem2 = view.findViewById(R.id.starItem2);
        mStarItem3 = view.findViewById(R.id.starItem3);
        mStarItem4 = view.findViewById(R.id.starItem4);
        mStarItem5 = view.findViewById(R.id.starItem5);


        chasingButton= view.findViewById(R.id.chasingButton);

        mStarContainer = view.findViewById(R.id.starContainer);

        manageStatButtonClick();

    }

    private void manageStatButtonClick() {
        mStarItem1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStarClicked = 1;
                mStarContainer.setVisibility(View.GONE);



                getPlaces();

            }
        });

        mStarItem2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStarClicked = 2;
                mStarContainer.setVisibility(View.GONE);

                getPlaces();

            }
        });

        mStarItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStarClicked = 3;
                mStarContainer.setVisibility(View.GONE);

                getPlaces();


            }
        });


        mStarItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStarClicked = 4;
                mStarContainer.setVisibility(View.GONE);


                getPlaces();


            }
        });


        mStarItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStarClicked = 5;
                mStarContainer.setVisibility(View.GONE);


                getPlaces();


            }
        });


        mStarItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mStarClicked = 5;
                mStarContainer.setVisibility(View.GONE);



                getPlaces();


            }
        });




    }


    private void getPlaces() {



        if (userMarker != null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    try {
                        new NRPlaces.Builder()
                                .listener(MapFragment.this)
                                .key(Constants.MAP_KEY)
                                .latlng(userMarker.getPosition().latitude, userMarker.getPosition().longitude)
                                .radius(Constants.MAP_RADIUS)
                                .type(PlaceType.GYM)
                                .build()
                                .execute();
                    } catch (Exception ex) {
                        ex.toString();
                    }
                }
            });
        }

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        mPlaces=null;

    }

    @Override
    public void onDetach() {
        super.onDetach();

        mMap = null;
        userMarker = null;
        targetMarkerList = null;
    }

    @Override
    public void onPlacesFailure(final PlacesException e) {


        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getActivity(), "" + e.toString(), Toast.LENGTH_SHORT).show();
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

                    if(mPlaces==null) {

                        mMap.setOnMarkerClickListener(MapFragment.this);

                        if (places != null && places.size() > 0) {

                            mPlaces=places;

                            if(places.size() < mStarClicked)
                            {
                                mStarClicked=places.size();
                            }

                            for(int i=0; i<mStarClicked; i++) {


                                    Place targetPlace = places.get(i);

                                    LatLng latlng = new LatLng(targetPlace.getLatitude(), targetPlace.getLongitude());

                                    if (targetMarkerList  != null) {

                                        MarkerOptions markerOptions = new MarkerOptions();
                                        markerOptions.position(latlng);
                                        markerOptions.title(targetPlace.getName());

                                        View marker = ((LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
                                        TextView numTxt = (TextView) marker.findViewById(R.id.text);
                                        ImageView   starImage= (ImageView) marker.findViewById(R.id.starButton);
                                        numTxt.setText(""+(i+1));


                                        if(i==0)
                                        {
                                            starImage.setImageResource(R.drawable.ic_star_1);
                                        }
                                        else if(i==1)
                                        {
                                            starImage.setImageResource(R.drawable.ic_star_2);
                                        }
                                        else if(i==2)
                                        {
                                            starImage.setImageResource(R.drawable.start_3);
                                        }
                                        else if(i==3)
                                        {
                                            starImage.setImageResource(R.drawable.star_4);
                                        }
                                        else
                                        {
                                            starImage.setImageResource(R.drawable.start_5);
                                        }


                                        markerOptions.icon(BitmapDescriptorFactory.fromBitmap(createDrawableFromView(getActivity(),marker)));
                                        markerOptions.getPosition();
                                        Marker targetMarker = mMap.addMarker(markerOptions);

                                        targetMarkerList.add(targetMarker);

                                    }

                            }
                        }
                    }

                } catch (IndexOutOfBoundsException ex) {
                    ex.toString();
                }

            }
        });

    }


    public static Bitmap createDrawableFromView(Context context, View view) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        view.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        view.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(), Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);

        return bitmap;
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


        if (isLocationEnabled(getActivity())) {

            getCurrentLocation();
        }


        listenGPS();

    }


    @Override
    public void onResume() {
        super.onResume();


    }


    private void listenGPS() {
        LocationManager lm = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }


        lm.addGpsStatusListener(new android.location.GpsStatus.Listener() {
            public void onGpsStatusChanged(int event) {
                switch (event) {
                    case GPS_EVENT_STARTED:
                        if (isLocationEnabled(getActivity())) {

                            getCurrentLocation();
                        }
                        break;
                    case GPS_EVENT_STOPPED:

                        break;
                }
            }
        });
    }


    @Override
    public void onMapFragmentReady() {

        mMapFragment.getMapAsync(this);
    }

    @Override
    public boolean onMarkerClick(Marker marker) {





        GoogleDirection.withServerKey(Constants.MAP_KEY)
                .from(userMarker.getPosition())
                //.and(waypoints)
                .to(marker.getPosition())
                .transportMode(TransportMode.DRIVING)
                .execute(new DirectionCallback() {
                    @Override
                    public void onDirectionSuccess(Direction direction, String rawBody) {
                        if(direction.isOK()) {

                            chasingButton.setVisibility(View.VISIBLE);

                            Route route = direction.getRouteList().get(0);
                            int legCount = route.getLegList().size();

                            for(int i=0; i<targetMarkerList.size(); i++)
                            {
                                if(!targetMarkerList.get(i).equals(marker))
                                {
                                    targetMarkerList.get(i).setVisible(false);
                                    targetMarkerList.get(i).remove();
                                }
                            }


                            //mMap.clear();

                            for (int index = 0; index < legCount; index++) {
                                Leg leg = route.getLegList().get(index);
                                //mMap.addMarker(new MarkerOptions().position(leg.getStartLocation().getCoordination()));
                               /* if (index == legCount - 1) {
                                    mMap.addMarker(new MarkerOptions().position(leg.getEndLocation().getCoordination()));
                                }*/
                                List<Step> stepList = leg.getStepList();
                                ArrayList<PolylineOptions> polylineOptionList = DirectionConverter.createTransitPolyline(getActivity(), stepList, 5, Color.RED, 3, Color.BLUE);
                                for (PolylineOptions polylineOption : polylineOptionList) {
                                    mMap.addPolyline(polylineOption);
                                }
                            }
                            setCameraWithCoordinationBounds(route);


                        } else {

                        }
                    }

                    @Override
                    public void onDirectionFailure(Throwable t) {

                    }
                });



        return false;
    }


    private void setCameraWithCoordinationBounds(Route route) {
        LatLng southwest = route.getBound().getSouthwestCoordination().getCoordination();
        LatLng northeast = route.getBound().getNortheastCoordination().getCoordination();
        LatLngBounds bounds = new LatLngBounds(southwest, northeast);
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, 100));
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

        try {
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
        }catch (Exception ex)
        {
            ex.toString();
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



                            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(userMarker.getPosition(), 14));
                        }

                    }
                });
    }






    public static boolean isLocationEnabled(Context context) {
        int locationMode = 0;
        String locationProviders;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT){
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);

            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
                return false;
            }

            return locationMode != Settings.Secure.LOCATION_MODE_OFF;

        }else{
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            return !TextUtils.isEmpty(locationProviders);
        }


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
