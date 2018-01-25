package com.revenant.takego_secondapp.controller;


import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.revenant.takego_secondapp.R;
import com.revenant.takego_secondapp.model.backend.DBManagerFactory;
import com.revenant.takego_secondapp.model.entities.Branch;
import com.revenant.takego_secondapp.model.entities.Car;
import com.revenant.takego_secondapp.util.AvailableCarsAdapter;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AvailableCarsFragment extends Fragment {
    private ListView availableCarsListView;
    private TextView fullCarDetails;
    private TextView hostingBranchDetails;

    public AvailableCarsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_available_cars, container, false);
        setUpUI(view);



        return view;
    }

    private void setUpUI(View view) {
        availableCarsListView = view.findViewById(R.id.availableCarsId);
        fullCarDetails = view.findViewById(R.id.availableCarDetailsId);
        hostingBranchDetails = view.findViewById(R.id.hostingBranchDetailsId);
        new AsyncTask<Void,Void,ListAdapter>(){
            @Override
            protected void onPostExecute(ListAdapter listAdapter) {
                super.onPostExecute(listAdapter);
                availableCarsListView.setAdapter(listAdapter);
            }

            @Override
            protected ListAdapter doInBackground(Void... voids) {
                List<Car> cars= DBManagerFactory.getDB_SQL().allAvailableCars();
                return new AvailableCarsAdapter(getActivity(),cars);
            }
        }.execute();

        availableCarsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                final Car car= (Car) adapterView.getItemAtPosition(i);
                fullCarDetails.setText(car.toString());

                new AsyncTask<Void,Void,Branch>(){
                    @Override
                    protected void onPostExecute(Branch branch) {
                        super.onPostExecute(branch);
                        hostingBranchDetails.setText(branch.toString());
                    }

                    @Override
                    protected Branch doInBackground(Void... voids) {
                        return DBManagerFactory.getDB_SQL().findBranchById(car.getDefaultBranchNumber());
                    }
                }.execute();

            }
        });

    }

}
