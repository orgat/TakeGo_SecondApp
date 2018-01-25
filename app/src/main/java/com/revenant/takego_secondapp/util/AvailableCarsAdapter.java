package com.revenant.takego_secondapp.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.revenant.takego_secondapp.R;
import com.revenant.takego_secondapp.model.entities.Car;

import java.util.List;

/**
 * Created by Or on 25-Jan-18.
 */

    public class AvailableCarsAdapter extends ArrayAdapter<Car> {

        public AvailableCarsAdapter(@NonNull Context context, List<Car> cars) {
            super(context, R.layout.available_car_row, cars);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.available_car_row,parent,false);

            TextView carDetails= view.findViewById(R.id.carIdId);
            Car car = getItem(position);
            carDetails.setText("Car ID: "+ String.valueOf(car.getCarId()));

            return view;
        }
    }

