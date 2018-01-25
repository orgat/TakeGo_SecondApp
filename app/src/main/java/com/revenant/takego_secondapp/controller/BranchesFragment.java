package com.revenant.takego_secondapp.controller;


import android.content.ContentValues;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.revenant.takego_secondapp.R;
import com.revenant.takego_secondapp.model.backend.Constants;
import com.revenant.takego_secondapp.model.backend.DBManagerFactory;
import com.revenant.takego_secondapp.model.entities.Branch;
import com.revenant.takego_secondapp.model.entities.Car;
import com.revenant.takego_secondapp.model.entities.Reservation;
import com.revenant.takego_secondapp.util.BranchesListViewAdapter;
import com.revenant.takego_secondapp.util.CarsPerBranchListViewAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BranchesFragment extends Fragment {

    private ListView branches;
    private ListView availableCars;
    private TextView branchDetails;
    private ListAdapter branchesAdapter;
    private Long userId;

    public BranchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_branches, container, false);
        setUpUI(view);
        return view;
    }

    private void setUpUI(View view) {
        branches = view.findViewById(R.id.branchesListViewId);
        availableCars = view.findViewById(R.id.availableCarsListViewId);
        branchDetails = view.findViewById(R.id.branchDetailsId);
        Bundle extras= this.getArguments();

        userId = Long.valueOf(extras.getString(Constants.CustomerConst.ID));

        branches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Branch branch = (Branch) adapterView.getItemAtPosition(i);
                branchDetails.setText(branch.toString());
                new AsyncTask<Void, Void, ListAdapter>() {
                    @Override
                    protected void onPostExecute(ListAdapter listAdapter) {
                        super.onPostExecute(listAdapter);
                        availableCars.setAdapter(listAdapter);
                    }

                    @Override
                    protected ListAdapter doInBackground(Void... voids) {
                        List<Car> availableCarsList = DBManagerFactory.getDB_SQL().availableCarsForBranch(branch.getBranchNumber());
                        return new CarsPerBranchListViewAdapter(getActivity(), availableCarsList);
                    }
                }.execute();
            }
        });

        availableCars.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Car car = (Car) adapterView.getItemAtPosition(i);
                final ContentValues cv = new ContentValues();
                cv.put(Constants.ReservationConst.IS_OPEN,true);
                cv.put(Constants.ReservationConst.CAR_NUMBER,car.getCarId());
                cv.put(Constants.ReservationConst.PRE_KM_COUNT,car.getMileage());
                cv.put(Constants.ReservationConst.CUSTOMER_NUMBER,userId);

                cv.put(Constants.ReservationConst.RENT_BEGINNING,new Date().toString());
                new AsyncTask<Void,Void,Long>(){
                    @Override
                    protected void onPostExecute(Long aLong) {
                        super.onPostExecute(aLong);
                        Toast.makeText(getActivity(),"Reservation: "+aLong+" created!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected Long doInBackground(Void... voids) {
                        return DBManagerFactory.getDB_SQL().addReservation(cv);
                    }
                }.execute();
            }
        });

        //Setting up the adapters
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                branches.setAdapter(branchesAdapter);

            }

            @Override
            protected Void doInBackground(Void... voids) {
                List<Branch> allBranches = DBManagerFactory.getDB_SQL().allBranches();
                branchesAdapter = new BranchesListViewAdapter(getActivity(), allBranches);

                return null;
            }
        }.execute();
    }
}



