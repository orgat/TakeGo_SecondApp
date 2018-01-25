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
import com.revenant.takego_secondapp.model.entities.Reservation;

import java.util.List;

/**
 * Created by Or on 25-Jan-18.
 */


    public class UserReservationsAdapter extends ArrayAdapter<Reservation> {

        public UserReservationsAdapter(@NonNull Context context, List<Reservation> reservations) {
            super(context, R.layout.user_open_reservation_row, reservations);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            View view = inflater.inflate(R.layout.user_open_reservation_row,parent,false);

            TextView resDetails= view.findViewById(R.id.openReservationTextId);
            Reservation reservation = getItem(position);
            resDetails.setText(reservation.toString());

            return view;
        }
    }


