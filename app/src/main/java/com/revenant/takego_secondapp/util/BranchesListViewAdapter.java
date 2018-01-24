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
import com.revenant.takego_secondapp.model.entities.Branch;

import java.util.List;

/**
 * Created by Or on 24-Jan-18.
 */

public class BranchesListViewAdapter extends ArrayAdapter<Branch> {

    public BranchesListViewAdapter(@NonNull Context context, List<Branch> branches) {
        super(context, R.layout.branch_list_row, branches);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.branch_list_row,parent,false);

        TextView address= view.findViewById(R.id.branchAddressId);
        Branch branch= getItem(position);
        address.setText(branch.getAddress());
        return view;
    }
}
