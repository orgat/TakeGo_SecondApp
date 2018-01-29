package com.revenant.takego_secondapp.controller;


import android.content.ContentValues;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
    private ArrayAdapter branchesAdapter;
    private ArrayAdapter carsAdapter;
    private List<Car> carList=new ArrayList<>();
    private List<Branch> branchList=new ArrayList<>();
    private Branch selectedBranch;
    private EditText textFiler;

    private Long userId;

    public BranchesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_branches, container, false);
        setUpUI(view);
        textFiler.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                branchesAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        return view;
    }

    private void setUpUI(View view) {
        branches = view.findViewById(R.id.branchesListViewId);
        availableCars = view.findViewById(R.id.availableCarsListViewId);
        branchDetails = view.findViewById(R.id.branchDetailsId);
        textFiler = view.findViewById(R.id.cityFilterId);
        Bundle extras= this.getArguments();

        userId = Long.valueOf(extras.getString(Constants.CustomerConst.ID));

        branches.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedBranch = (Branch) adapterView.getItemAtPosition(i);
                branchDetails.setText(selectedBranch.toString());
                updateAvailableCarsList(selectedBranch);
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
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                cv.put(Constants.ReservationConst.RENT_BEGINNING,sdf.format(new Date()));
                new AsyncTask<Void,Void,Long>(){
                    @Override
                    protected void onPostExecute(Long aLong) {
                        super.onPostExecute(aLong);
                        Toast.makeText(getActivity(),"Reservation: "+aLong+" created!",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    protected Long doInBackground(Void... voids) {
                        long result= DBManagerFactory.getDB_SQL().addReservation(cv);
                        updateAvailableCarsList(selectedBranch);
                        return result;
                    }
                }.execute();
            }
        });

        //Setting up the adapters
        updateBranchesList();
    }

    private void updateBranchesList() {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                branches.setAdapter(branchesAdapter);

            }

            @Override
            protected Void doInBackground(Void... voids) {
                branchList = DBManagerFactory.getDB_SQL().allBranches();
                branchesAdapter = new BranchesListViewAdapter(getActivity(), branchList);

                return null;
            }
        }.execute();
    }
    private void updateAvailableCarsList(final Branch branch) {
        new AsyncTask<Void, Void, ListAdapter>() {
            @Override
            protected void onPostExecute(ListAdapter listAdapter) {
                super.onPostExecute(listAdapter);
                availableCars.setAdapter(carsAdapter);
            }

            @Override
            protected ListAdapter doInBackground(Void... voids) {
                carList = DBManagerFactory.getDB_SQL().availableCarsForBranch(branch.getBranchNumber());
                carsAdapter= new CarsPerBranchListViewAdapter(getActivity(), carList);
                return carsAdapter;
            }
        }.execute();
    }
}



