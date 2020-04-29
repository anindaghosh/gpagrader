package com.khakidonald.srmgrader;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class subAdapter extends RecyclerView.Adapter<subAdapter.subViewHolder> {

    private Context mCtx;
    public static List<Subject> subList;


    public subAdapter(Context mCtx, List<Subject> subList) {
        this.mCtx = mCtx;
        this.subList = subList;
    }

    @Override
    public subViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_sub_cards, parent, false);
        return new subViewHolder(view);
    }

    @Override
    public void onBindViewHolder(subViewHolder holder, int position) {

        holder.spinnerCred.setSelection(subList.get(position).getSpinnerCredPos());
        holder.spinnerGrad.setSelection(subList.get(position).getSpinnerGradPos());

    }

    @Override
    public int getItemCount() {
        return this.subList.size();
    }


    class subViewHolder extends RecyclerView.ViewHolder {

        Spinner spinnerCred, spinnerGrad;

        public subViewHolder(View itemView) {
            super(itemView);
            spinnerCred = itemView.findViewById(R.id.spinner_cred);
            spinnerGrad = itemView.findViewById(R.id.spinner_grad);

            ArrayAdapter<CharSequence> adapterCred = ArrayAdapter.createFromResource(itemView.getContext(), R.array.credits_choice1, R.layout.spinner_item);
            adapterCred.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCred.setAdapter(adapterCred);

            ArrayAdapter<CharSequence> adapterGrad = ArrayAdapter.createFromResource(itemView.getContext(), R.array.grades_choice, R.layout.spinner_item);
            adapterGrad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerGrad.setAdapter(adapterGrad);

            spinnerCred.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    subList.get(getAdapterPosition()).setSpinnerCredPos(i);
                    subList.get(getAdapterPosition()).setCredits(adapterView.getItemAtPosition(i).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

            spinnerGrad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    subList.get(getAdapterPosition()).setSpinnerGradPos(i);
                    subList.get(getAdapterPosition()).setGrade(adapterView.getItemAtPosition(i).toString());
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        }
    }
}
