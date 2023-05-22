package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

@SuppressLint("ValidFragment")
public class AddFragment extends Fragment {

    ArrayList<Country> Countries;
    dbService Service;

    @SuppressLint("ValidFragment")
    public AddFragment(ArrayList<Country> countries,dbService Service) {
        Countries = countries;
        this.Service = Service;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add,container,false);

        Button btnAddNewItem = (Button) view.findViewById(R.id.btnAddNewItem);

        EditText etCountry = (EditText) view.findViewById(R.id.Name);
        EditText etCapital = (EditText) view.findViewById(R.id.Capital);
        EditText etNumber = (EditText) view.findViewById(R.id.Number);
        CheckBox cbFlag = (CheckBox) view.findViewById(R.id.Flag);

        btnAddNewItem.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                ContentValues cv = new ContentValues();
                SQLiteDatabase db = Service.getWritableDatabase();

                cv.put("name",etCountry.getText().toString());
                cv.put("capital",etCapital.getText().toString());
                cv.put("number",Integer.parseInt(etNumber.getText().toString()));
                cv.put("flag",1);

                long rowId= db.insert("mytable", null, cv);

                Countries.add(new Country(Math.toIntExact(rowId),
                        etCountry.getText().toString(),
                        etCapital.getText().toString(),
                        Integer.parseInt(etNumber.getText().toString()),
                        cbFlag.isChecked()));
                ((MainActivity)getActivity()).UpdateList();
            }
        });
        return view;

    }
}