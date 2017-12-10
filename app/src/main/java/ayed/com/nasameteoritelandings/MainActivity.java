package ayed.com.nasameteoritelandings;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import ayed.com.nasameteoritelandings.Entities.Meteorite;
import ayed.com.nasameteoritelandings.Utils.DataBaseHelper;
import ayed.com.nasameteoritelandings.Utils.MeteoriteAdapter;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueueTags;
    ProgressDialog pDialog;
    String output ="";
    String[] t ;
    ListView listMeteorites ;
    ArrayList<Meteorite> meteorites ;
    MeteoriteAdapter meteoriteAdapter ;
    DataBaseHelper dataBaseHelper ;
    ArrayList<Meteorite> sqliteMeteorites ;
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        prefs = getSharedPreferences("NASA", MODE_PRIVATE);
        listMeteorites = findViewById(R.id.listeMeteorite);
        meteorites = new ArrayList<Meteorite>();
        sqliteMeteorites = new ArrayList<Meteorite>();
        dataBaseHelper = new DataBaseHelper(MainActivity.this);
        sqliteMeteorites = dataBaseHelper.getMeteorites();
        if (checkInternetConenction())
        {
            if (prefs.getBoolean("FirstLaunch", true)) {

                getMeteoriteFromNASA();
                prefs.edit().putBoolean("FirstLaunch", false).commit();

            }else{
                Collections.sort(sqliteMeteorites, new Comparator<Meteorite>() {
                    @Override
                    public int compare(Meteorite o1, Meteorite o2) {
                        double f = Double.parseDouble(o2.getMass().toString())-Double.parseDouble(o1.getMass().toString());
                        return (int) f ;
                    }
                });

                meteoriteAdapter = new MeteoriteAdapter(MainActivity.this,R.layout.meteorite_list_adapter,sqliteMeteorites);
                listMeteorites.setAdapter(meteoriteAdapter);
            }


        }else if (sqliteMeteorites.isEmpty()) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("No!!");
            alertDialog.setMessage("Won't be able to load data");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            System.exit(0);
                        }
                    });
            alertDialog.show();
        }else {
            Collections.sort(sqliteMeteorites, new Comparator<Meteorite>() {
                @Override
                public int compare(Meteorite o1, Meteorite o2) {
                    double f = Double.parseDouble(o2.getMass().toString())-Double.parseDouble(o1.getMass().toString());
                    return (int) f ;
                }
            });

            meteoriteAdapter = new MeteoriteAdapter(MainActivity.this,R.layout.meteorite_list_adapter,sqliteMeteorites);
            listMeteorites.setAdapter(meteoriteAdapter);
        }

        listMeteorites.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent nextView = new Intent(MainActivity.this,DetailActivity.class);
                nextView.putExtra("id",sqliteMeteorites.get(position).getId());
                startActivity(nextView);
            }
        });
    }
    private boolean checkInternetConenction() {
        // get Connectivity Manager object to check connection
        ConnectivityManager connec
                =(ConnectivityManager)getSystemService(getBaseContext().CONNECTIVITY_SERVICE);

        // Check for network connections
        if ( connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTED ||
                connec.getNetworkInfo(0).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTING ||
                connec.getNetworkInfo(1).getState() == android.net.NetworkInfo.State.CONNECTED )
        {
            Toast.makeText(this, " Connected ", Toast.LENGTH_LONG).show();
            return true;
        }else if (connec.getNetworkInfo(0).getState() ==
                        android.net.NetworkInfo.State.DISCONNECTED ||
                        connec.getNetworkInfo(1).getState() ==
                                android.net.NetworkInfo.State.DISCONNECTED  ) {
            Toast.makeText(this, " Not Connected ", Toast.LENGTH_LONG).show();
            return false;
        }
        return false;
    }
    public void getMeteoriteFromNASA()
    {
        requestQueueTags = Volley.newRequestQueue(MainActivity.this);
        requestQueueTags.start();

        pDialog = new ProgressDialog(MainActivity.this);
        pDialog.setMessage("Chargement...");
        pDialog.show();
        String url ="https://data.nasa.gov/resource/y77d-th95.json?$limit=1000000&$where=year>'2010-01-01T00:00:00.000'";
        StringRequest strReq = new StringRequest(Request.Method.GET,
                url, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {



                try {


                    pDialog.hide();


                    JSONArray array = new JSONArray(response);

                    for (int i = 0; i <array.length(); i++) {
                        JSONObject j = array.getJSONObject(i);
                        Meteorite meteorite = new Meteorite();
                        meteorite.setId(j.getInt("id"));
                        meteorite.setName(j.getString("name"));
                        meteorite.setNametype(j.getString("nametype"));
                        if(j.has("mass")){
                            meteorite.setMass(j.getString("mass"));
                        }else{
                            meteorite.setMass("");
                        }
                        meteorite.setFall(j.getString("fall"));
                        if(j.has("reclat")){
                            meteorite.setReclat(j.getString("reclat"));
                        }else{
                            meteorite.setReclat("");
                        }
                        if(j.has("reclong")){
                            meteorite.setReclong(j.getString("reclong"));
                        }else{
                            meteorite.setReclong("");
                        }
                        meteorite.setRecclass(j.getString("recclass"));
                        if(j.has("year")){
                            meteorite.setYear(j.getString("year"));
                        }else{
                            meteorite.setReclong("");
                        }


                        JSONObject jr = j.getJSONObject("geolocation");
                        JSONArray ja = jr.getJSONArray("coordinates");
                        Log.i("Langitude ", ja.get(0).toString());
                        Log.i("Longitude ", ja.get(1).toString()) ;
                        meteorite.setLatitude(ja.get(0).toString());
                        meteorite.setLongitude(ja.get(1).toString());


                        meteorites.add(meteorite);

                    }


                    dataBaseHelper.insertMeteorites(meteorites);

                    sqliteMeteorites = dataBaseHelper.getMeteorites();
                    Collections.sort(sqliteMeteorites, new Comparator<Meteorite>() {
                        @Override
                        public int compare(Meteorite o1, Meteorite o2) {
                            double f = Double.parseDouble(o2.getMass().toString())-Double.parseDouble(o1.getMass().toString());
                            return (int) f ;
                        }
                    });
                    meteoriteAdapter = new MeteoriteAdapter(MainActivity.this,R.layout.meteorite_list_adapter,sqliteMeteorites);
                    listMeteorites.setAdapter(meteoriteAdapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

                if (error.networkResponse == null) {
                    pDialog.hide();

                } else {
                    pDialog.hide();

                    System.out.println(" Internet problem or very slow movile to SQLITE !");
                }

            }


        }) {

        };


        requestQueueTags.add(strReq);
    }
}
