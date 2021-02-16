package com.example.nabi1_trialbook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomList extends ArrayAdapter<Experiment> {

    private final ArrayList<Experiment> experiments;
    private final Context context;

    public CustomList(Context context, ArrayList<Experiment> experiments) {
        super(context,0, experiments);
        this.experiments = experiments;
        this.context = context;
    }

    // set the attributes of each item in the list
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;

        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.content,parent,false);
        }

        Experiment experiment = experiments.get(position);

        TextView experiment_name = view.findViewById(R.id.Exp_text);
        TextView date = view.findViewById(R.id.date_text);
        TextView totalTrials = view.findViewById(R.id.total_trials);
        TextView passRate = view.findViewById(R.id.pass_rate);

        experiment_name.setText(experiment.getExperimentName());
        date.setText("Date: "+experiment.getDateCreated());
        totalTrials.setText("Trials: "+ experiment.getTotalTrials());
        passRate.setText("Pass Rate: "+ experiment.getSuccessRate()*100+"%");

        return view;
    }
}
