package edu.lambton.roomify.landlord.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

import edu.lambton.roomify.R;

public class ListingOneCFragment extends Fragment {
    private double latitude;
    private double longitude;
    final int REQUEST_PERMISSION_CODE = 1000;

    private LocationManager locationManager;
    private LocationListener locationListener;

    private final OnMapReadyCallback callback = new OnMapReadyCallback() {

        @Override
        public void onMapReady(@NonNull GoogleMap googleMap) {

            LatLng currentLocation = new LatLng(latitude, longitude);

            // Get the address name
            String addressName = getAddressFromLocation(latitude, longitude);

            MarkerOptions markerOptions = new MarkerOptions()
                    .position(currentLocation)
                    .title("House Location")
                    .snippet(addressName);

            googleMap.addMarker(markerOptions);
            googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 15));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_property_listing_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }


        // location
        locationManager = (LocationManager) requireActivity().getSystemService(Context.LOCATION_SERVICE);
        locationListener = this::updateLocationInfo;

        // if the permission is granted, we request the update.
        // if the permission is not granted, we request for the access.
        if (ContextCompat.checkSelfPermission(requireContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(requireActivity(), new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_CODE);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);

            Location lasKnownLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lasKnownLocation != null) updateLocationInfo(lasKnownLocation);
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private void updateLocationInfo(@NonNull Location location) {
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();

    }

    private String getAddressFromLocation(double latitude, double longitude) {
        Geocoder geocoder = new Geocoder(requireContext());
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);

            if (addresses != null && addresses.size() > 0) {
                Address address = addresses.get(0);

                String postalCode = address.getPostalCode();
                String city = address.getLocality();
                String state = address.getAdminArea();
                String country = address.getCountryCode();
                String subLocality = address.getSubLocality();
                String featureName = address.getFeatureName();
                String thoroughfare = address.getThoroughfare();
                String subThoroughfare = address.getSubThoroughfare();

                // You can use these values as needed
                System.out.println("Postal Code: " + postalCode);
                System.out.println("City: " + city);
                System.out.println("State: " + state);
                System.out.println("Country: " + country);
                System.out.println("Sub Locality: " + subLocality);
                System.out.println("Feature Name: " + featureName);
                System.out.println("Thoroughfare: " + thoroughfare);
                System.out.println("SubThoroughfare: " + subThoroughfare);

                String addressName = address.getAddressLine(0); // You can get other address details as needed
                System.out.println("Address: " + addressName);

                return addressName;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

}