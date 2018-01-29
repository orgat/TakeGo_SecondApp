package com.revenant.takego_secondapp.controller;


import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.revenant.takego_secondapp.R;
import com.revenant.takego_secondapp.model.backend.Constants;
import com.revenant.takego_secondapp.model.backend.DBManagerFactory;
import com.revenant.takego_secondapp.model.entities.Reservation;
import com.revenant.takego_secondapp.util.UserReservationsAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class UserReservationsFragment extends Fragment {
    private ListView userReservations;
    private EditText mileage;
    private EditText liters;

    private Button closeReservation;
    private Reservation reservationToClose;

    public UserReservationsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_user_reservations, container, false);
        setUpUI(view);
        // Inflate the layout for this fragment

        return view;
    }

    private void setUpUI(View view) {
        userReservations = view.findViewById(R.id.userOpenReservationsId);
        mileage= view.findViewById(R.id.mileageEditText);
        liters= view.findViewById(R.id.litersEditTex);
        closeReservation = view.findViewById(R.id.closeResButtonId);
        final long userID=Long.valueOf(getArguments().getString(Constants.CustomerConst.ID));

        new AsyncTask<Void,Void,ListAdapter>(){
            @Override
            protected void onPostExecute(ListAdapter listAdapter) {
                super.onPostExecute(listAdapter);
                userReservations.setAdapter(listAdapter);
            }

            @Override
            protected ListAdapter doInBackground(Void... voids) {
                List<Reservation> reservations = DBManagerFactory.getDB_SQL().userOpenReservations(userID);
                return new UserReservationsAdapter(getActivity(),reservations);
            }
        }.execute();



        userReservations.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                reservationToClose= (Reservation) adapterView.getItemAtPosition(i);
                Toast.makeText(getActivity(),"Reservation: "+reservationToClose.getReservationNumber()+" selected.",Toast.LENGTH_SHORT).show();
            }
        });

        closeReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                closeReservation();
            }
        });

    }

    private void closeReservation() {
        if (liters.getText().toString().isEmpty() || mileage.getText().toString().isEmpty()) {
            Toast.makeText(getActivity(), "Please fill out all fields", Toast.LENGTH_SHORT).show();
        } else if(reservationToClose==null){
            Toast.makeText(getActivity(), "Please select a reservation", Toast.LENGTH_SHORT).show();
        } else {
            double litersInput = Double.valueOf(liters.getText().toString());
            double mileageInput= Double.valueOf(mileage.getText().toString());
            reservationToClose.setLitersRefueled(litersInput);
            reservationToClose.setWasRefueled(litersInput>0? true: false);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            reservationToClose.setRentEnd(sdf.format(new Date()));
            reservationToClose.setPostKMCount(reservationToClose.getPreKMCount()+mileageInput);
            double price = (10*mileageInput)- (litersInput*6);
            reservationToClose.setPrice(price);
            reservationToClose.setOpen(false);
            final ContentValues cv= Constants.ReservationToContentValues(reservationToClose);
            new AsyncTask<Void,Void,Void>(){
                @Override
                protected void onPostExecute(Void aVoid) {
                    super.onPostExecute(aVoid);
                    Toast.makeText(getActivity(),"Reservation closed!",Toast.LENGTH_SHORT).show();
                }

                @Override
                protected Void doInBackground(Void... voids) {
                    DBManagerFactory.getDB_SQL().updateReservation(reservationToClose.getReservationNumber(),cv);
                    return null;
                }
            }.execute();
        }
    }

}
