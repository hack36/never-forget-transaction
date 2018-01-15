package com.example.sunny.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by sunny on 1/6/2018.
 */

public class DetailAdapter extends ArrayAdapter<DataModel> {
    TextView name;
    public DetailAdapter(Context context, ArrayList<DataModel> dataModels) {
        super(context, 0, dataModels);
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        DataModel dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_detail, parent, false);
        }
        // Lookup view for data population
        name = (TextView) convertView.findViewById(R.id.text1_id);
        TextView dr = (TextView) convertView.findViewById(R.id.text2_id);
        TextView cr = (TextView) convertView.findViewById(R.id.text3_id);
        TextView t = (TextView) convertView.findViewById(R.id.text4_id);
        t.setTag(dataModel);
        // Populate the data into the template view using the data object
        name.setText(dataModel.mName);
        dr.setText(dataModel.mDr);
        cr.setText(dataModel.mCr);
        t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataModel d = (DataModel) view.getTag();
                Intent intent = new Intent(DetailAdapter.this.getContext(),EditActivity.class);
                intent.putExtra("string",d.mName);
                DetailAdapter.this.getContext().startActivity(intent);
            }
        });
        // Return the completed view to render on screen
        return convertView;
    }

}
