package com.khakidonald.srmgrader;

import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class cardAdapter extends RecyclerView.Adapter<cardAdapter.cardViewHolder> {
    private Context mCtx;
    public static List<Semester> semesterList;

    public cardAdapter(Context mCtx, List<Semester> semesterList){
        setHasStableIds(true);
        this.mCtx = mCtx;
        this.semesterList = semesterList;
    }

    @Override
    public cardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.layout_sem_cards, parent, false);
        return new cardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(cardViewHolder holder, int position){

        holder.textViewSem.setText(semesterList.get(position).getSem());
        holder.etGpa.setHintTextColor(Color.DKGRAY);
        holder.etCred.setHintTextColor(Color.DKGRAY);
    }

    @Override
    public int getItemCount() {
        return semesterList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(final int position) {
        return position;
    }

    class cardViewHolder extends RecyclerView.ViewHolder {

        TextView textViewSem;
        EditText etGpa, etCred;

        public cardViewHolder(View itemView) {
            super(itemView);
            textViewSem = itemView.findViewById(R.id.textViewSem);
            etGpa = itemView.findViewById(R.id.etGpa);
            etCred = itemView.findViewById(R.id.etCred);

            etGpa.addTextChangedListener(new TextWatcher() {

                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(etGpa.getText().toString().length()!=0 && !(etGpa.getText().toString().equals("."))) semesterList.get(getAdapterPosition()).setGpa(Float.parseFloat(etGpa.getText().toString()));
                    else semesterList.get(getAdapterPosition()).setGpa(0);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            etCred.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(etCred.getText().toString().length()!=0) semesterList.get(getAdapterPosition()).setCredits(Integer.parseInt(etCred.getText().toString()));
                    else semesterList.get(getAdapterPosition()).setCredits(0);
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        }
    }

}
