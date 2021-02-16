package com.example.nabi1_trialbook;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ExperimentFragment extends DialogFragment {
    private EditText experimentName;
    private EditText dateCreated;
    public EditText numField;
    public int pos = -2;
    public int longClick = -1;
    private OnFragmentInteractionListener listener;

    // constructor
    public ExperimentFragment(Experiment experiment, int position, int lngClick){
        this.pos = position;
        this.longClick = lngClick;
    }
    // constructor
    public ExperimentFragment(Experiment experiment, int position) {
        this.pos = position;
    }
    // constructor
    public ExperimentFragment() {}

    // on click interface
    public interface OnFragmentInteractionListener {
        void onOkPressed(Experiment newExperiment);
        void changeText(String toString, String toString1,int pos);
        void addFails(int fail, int pos);
        void addPasses(int pass, int pos);
        void clickDelete(int pos);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener){
            listener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    // creats the dialogs for clicking a list item
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View expView = LayoutInflater.from(getActivity()).inflate(R.layout.experiment_dialog, null);
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.add_experiment_fragment_layout, null);
        experimentName = view.findViewById(R.id.Experiment_name);
        dateCreated = view.findViewById(R.id.dateEditText);
        numField = expView.findViewById(R.id.numPassFail);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        if (pos == -2) { // if the plus button is clicked
            return builder.setView(view).setTitle("Add Experiment")
                    .setNegativeButton("Cancel", null)
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String date_created = dateCreated.getText().toString();
                            String experiment_name = experimentName.getText().toString();
                            // validate user input
                            if (experiment_name.length() == 0 || date_created.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})") != true){ }
                            else {
                                listener.onOkPressed(new Experiment(experiment_name, date_created, 0, 0));
                            }
                        }}).create();
        }
        else if (longClick == -2){ // if the list item is long clicked to delete or edit the experiment
            return builder.setView(view).setTitle("Edit/Delete Experiment")
                    .setNegativeButton("Delete", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            listener.clickDelete(pos);
                            pos = -2;
                        }})
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            String date_created = dateCreated.getText().toString();
                            String experiment_name = experimentName.getText().toString();
                            // validate user input
                            if (experiment_name.length() == 0 || date_created.matches("([0-9]{4})-([0-9]{2})-([0-9]{2})") != true){ }
                            else {
                                listener.changeText(experiment_name, date_created, pos);
                            }
                            pos = -2;
                        }}).create();
        }
        else{ // if the list item is regular clicked to add pass fials to the experiment
            return builder.setView(expView).setTitle("Pass/Fail")
                    .setNegativeButton("Pass", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            try{
                            listener.addPasses(Integer.parseInt(numField.getText().toString()), pos);
                            pos = -2;
                            }
                            catch (Exception e){
                                pos = -2;
                            }
                        }
                    })
                    .setPositiveButton("Fail", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            try {
                                listener.addFails(Integer.parseInt(numField.getText().toString()), pos);
                                pos = -2;
                            }
                            catch (Exception e){
                                pos = -2;
                            }
                        }}).create();
        }
    }
}
