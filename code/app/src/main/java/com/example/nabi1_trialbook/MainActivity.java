package com.example.nabi1_trialbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExperimentFragment.OnFragmentInteractionListener {

    // Declare the variables so that you will be able to reference it later.
    ListView experimentList;
    ArrayAdapter<Experiment> experimentAdapter;
    ArrayList<Experiment> experimentDataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // initialize variables
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        experimentList = findViewById(R.id.city_list);
        experimentDataList = new ArrayList<>();
        experimentAdapter = new CustomList(this, experimentDataList);
        experimentList.setAdapter(experimentAdapter);
        final FloatingActionButton addExperimentButton = findViewById(R.id.add_experiment_button);
        // listener for the plus button
        addExperimentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new ExperimentFragment().show(getSupportFragmentManager(), "ADD_EXPERIMENT");
            }
        });

        // long click listener to edit/delete experiment
        experimentList.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Experiment experiment = experimentAdapter.getItem(position);
                new ExperimentFragment(experiment, position, -2).show(getSupportFragmentManager(), "EDIT_EXPERIMENT");
                return true;
            }
        });

        // normal listener to add pass/fails to the experiment
        experimentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Experiment experiment = experimentAdapter.getItem(position);
                new ExperimentFragment(experiment, position).show(getSupportFragmentManager(), "EDIT_EXPERIMENT");
            }
        });

    }

    public void clickDelete(int pos) { // delete selected experiment
        experimentDataList.remove(pos);
        experimentAdapter.notifyDataSetChanged();
    }

    public void addFails(int fail, int pos){ // add to the number of fails for the experiment
        Experiment change = experimentAdapter.getItem(pos);
        int numFails = change.getFails();
        change.setFails(fail+numFails);
        update(change, fail);
    }

    public void addPasses(int pass, int pos){ // add to the number of fails for the experiment
        Experiment change = experimentAdapter.getItem(pos);
        int numPasses = change.getPasses();
        change.setPasses(pass+numPasses);
        update(change, pass);
    }

    public void update(Experiment change, int newTrials){ // update the experiment success rate and total trials
        int total = change.getTotalTrials();
        change.setTotalTrials(total+newTrials);
        int newTotal = change.getTotalTrials();
        int numPasses = change.getPasses();
        try {
            change.setSuccessRate((float) numPasses / newTotal);
        }
        catch (Exception e){
            change.setSuccessRate(0);
        }
    }
    public void changeText(String experiment, String date, int pos){ // edit experiment
        Experiment change = experimentAdapter.getItem(pos);
        change.setExperiment(experiment);
        change.setDateCreated(date);
    }

    @Override
    public void onOkPressed(Experiment newExperiment) { // add new experiment
        experimentAdapter.add(newExperiment);
    }


}
