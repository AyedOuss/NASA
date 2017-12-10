package ayed.com.nasameteoritelandings;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ayed.com.nasameteoritelandings.Entities.Meteorite;
import ayed.com.nasameteoritelandings.Utils.DataBaseHelper;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    DataBaseHelper dataBaseHelper ;
    Meteorite meteorite ;
    GoogleMap googleMap ;
    double lantitude ;
    double longitude ;
    TextView tvName , tvRec , tvClass ,tvMass ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        dataBaseHelper = new DataBaseHelper(DetailActivity.this);
        meteorite = dataBaseHelper.getMeteoriteById(getIntent().getExtras().getInt("id"));
        tvName = findViewById(R.id.tvName);
        tvRec = findViewById(R.id.tvRec);
        tvClass = findViewById(R.id.tvClass);
        tvMass = findViewById(R.id.tvMass);

        Log.i("test :" ,meteorite.getName().toString());

            lantitude = Double.parseDouble(meteorite.getLatitude().toString()) ;


            longitude = Double.parseDouble(meteorite.getLongitude().toString()) ;

        Log.i("lantitude :" ,lantitude+"");
        Log.i("longitude :" ,longitude+"");
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        tvName.setText("  "+meteorite.getName());
        tvMass.setText("  Mass : "+meteorite.getMass()+" Fall :" +meteorite.getFall());
        tvRec.setText("  Reclat : "+meteorite.getReclat()+" Reclong : "+meteorite.getReclong());
        tvClass.setText("  RecClass :"+meteorite.getRecclass()+" NameType :"+meteorite.getNametype());

    }
    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(lantitude, longitude)).title(meteorite.getName()));
        map.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(lantitude, longitude)));
    }
}
