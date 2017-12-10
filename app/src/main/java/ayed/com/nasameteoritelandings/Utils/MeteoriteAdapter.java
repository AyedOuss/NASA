package ayed.com.nasameteoritelandings.Utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ayed.com.nasameteoritelandings.Entities.Meteorite;
import ayed.com.nasameteoritelandings.R;

/**
 * Created by oussama on 09/12/2017.
 */

public class MeteoriteAdapter extends ArrayAdapter<Meteorite> {

    public Context context;
    public int resource;
    public List<Meteorite> meteoriteItems;

    public MeteoriteAdapter(Context context, int resource, List<Meteorite> objects) {

        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.meteoriteItems = objects;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = li.inflate(resource, parent, false);




        TextView txtNameMeteorite = (TextView) view.findViewById(R.id.tvMeteoriteName);
        String nameMeteorite = meteoriteItems.get(position).getName();
        txtNameMeteorite.setText(nameMeteorite);


        TextView txtYear = (TextView) view.findViewById(R.id.tvYear);
        String yearMeteorite = meteoriteItems.get(position).getYear().substring(0,4);
        txtYear.setText("Year : "+yearMeteorite);

        return view;
    }
}

