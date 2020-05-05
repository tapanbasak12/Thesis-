package com.example.fitnessapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fitnessapp.R;
import com.example.fitnessapp.objects.People;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class MyAdapter extends ArrayAdapter<People> {

    public MyAdapter(@NonNull Context context, ArrayList<People> arrayList) {
        super(context, 0,  arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the {@link AndroidFlavor} object located at this position in the list
        People currentPeople = getItem(position);

        TextView keyView = listItemView.findViewById(R.id.name);
        keyView.setText(currentPeople.getKey());

        TextView cidView = listItemView.findViewById(R.id.address);
        cidView.setText(currentPeople.getCid());

        TextView uidView = listItemView.findViewById(R.id.ud);
        uidView.setText(currentPeople.getUid());
        // Find the TextView in the list_item.xml layout with the ID version_name

        Button ShareBtn = (Button)listItemView.findViewById(R.id.BtnShare);

        //AddOnclicktoShareButton
        ShareBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    
            }
        });

        return listItemView;
    }
}
