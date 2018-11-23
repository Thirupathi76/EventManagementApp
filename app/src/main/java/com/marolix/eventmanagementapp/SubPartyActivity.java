package com.marolix.eventmanagementapp;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.marolix.eventmanagementapp.adapter.SubPartyListAdapter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SubPartyActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private String[] arr;
    private LinearLayout marriage_layout;
    private SubPartyListAdapter adapter;
    private Button getResults;
    private ArrayList<String> selectedParties = new ArrayList<>();
    private CheckBox checkBox1, checkBox2, checkBox3, checkBox4, checkBox_others;
    private EditText sub_others;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_party);

        recyclerView = findViewById(R.id.recyclerView);
        marriage_layout = findViewById(R.id.marriage_layout);
        getResults = findViewById(R.id.getResults);

        checkBox1 = findViewById(R.id.checkbox1);
        checkBox2 = findViewById(R.id.checkbox2);
        checkBox3 = findViewById(R.id.checkbox3);
        checkBox4 = findViewById(R.id.checkbox4);
        checkBox_others = findViewById(R.id.checkbox_others);
        checkBox_others.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sub_others.setVisibility(View.VISIBLE);
                } else {
                    sub_others.setVisibility(View.GONE);
                }
            }
        });

        sub_others = findViewById(R.id.et_others);

//        String getParty = getIntent().getStringExtra("PARTY_NAME");

        /*if (getParty.equals("Marriage Events")) {
            recyclerView.setVisibility(View.GONE);
            marriage_layout.setVisibility(View.VISIBLE);

        } else {
            marriage_layout.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }*/

        arr = getResources().getStringArray(R.array.marriage);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new SubPartyListAdapter(this, arr);
        /*if (getParty.equals("Marriage"))

            adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,
                    getResources().getStringArray(R.array.marriage));*/


        recyclerView.setAdapter(adapter);

        getResults.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedParties.clear();
                boolean isValid = false;
                if (checkBox1.isChecked()) {
                    isValid = true;
                    selectedParties.add(checkBox1.getText().toString());
                }
                if (checkBox2.isChecked()) {
                    isValid = true;
                    selectedParties.add(checkBox2.getText().toString());
                }
                if (checkBox3.isChecked()) {
                    isValid = true;
                    selectedParties.add(checkBox3.getText().toString());
                }
                if (checkBox4.isChecked()) {
                    isValid = true;
                    selectedParties.add(checkBox4.getText().toString());
                }
                if (checkBox_others.isChecked()) {
                    isValid = true;
                    sub_others.setVisibility(View.VISIBLE);
                    String subOther = sub_others.getText().toString();
                    if (!subOther.equals("")) {
                        selectedParties.add(subOther);
                    }
                }
                /*SharedPreferences pref = getSharedPreferences("Sub_parties", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                Set<String> set = new HashSet<String>();
                set.addAll(selectedParties);
                editor.putStringSet("Selected_Sub", set);
                editor.apply();*/
                Utilities.setArrayPreference(SubPartyActivity.this, Constants.SUB_PARTIES, selectedParties);
                Toast.makeText(SubPartyActivity.this, "Will show the results", Toast.LENGTH_SHORT).show();

                if (isValid) {
                    if (checkBox_others.isChecked()) {
                        sub_others.setVisibility(View.VISIBLE);
                        String subOther = sub_others.getText().toString();
                        if (!subOther.equals("")) {
                            selectedParties.add(subOther);
                            dialog = new ProgressDialog(SubPartyActivity.this);

                            dialog.setTitle("Please wait");
                            dialog.setMessage("Preparing resources...");
                            dialog.setCancelable(false);
                            dialog.show();
                            new Handler().postDelayed(new Runnable() {
                                public void run() {
                                    dialog.dismiss();
//                        text.setVisibility(View.VISIBLE);

                                    Intent intent = new Intent(SubPartyActivity.this, EventManagerActivity.class);
                                    startActivity(intent);

                                }
                            }, 2000);
                        } else {
                            Toast.makeText(SubPartyActivity.this, "Specify other", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        dialog = new ProgressDialog(SubPartyActivity.this);

                        dialog.setTitle("Please wait");
                        dialog.setMessage("Preparing resources...");
                        dialog.setCancelable(false);
                        dialog.show();
                        new Handler().postDelayed(new Runnable() {
                            public void run() {
                                dialog.dismiss();
//                        text.setVisibility(View.VISIBLE);

                                Intent intent = new Intent(SubPartyActivity.this, EventManagerActivity.class);
                                startActivity(intent);

                            }
                        }, 2000);
                    }
                } else {
                    Toast.makeText(SubPartyActivity.this, "Select any Event", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
