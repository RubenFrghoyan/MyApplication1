package com.example.myapplication1.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.myapplication1.R;
import com.example.myapplication1.adapters.Adapter;
import com.example.myapplication1.model.Persons;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<Persons> person = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        setPersonsDetails();
        startRecyclerView();
    }

    private void setPersonsDetails() {

        person.add(new Persons("Ruben","Frghoyan",1996));
        person.add(new Persons("Meri","Mikayelyan",1998));
        person.add(new Persons("Khachik","Frghoyan",1997));
        person.add(new Persons("Hrach","Mikayelyan",2000));
        person.add(new Persons("Mher","Shahinyan",1995));
        person.add(new Persons("Karen","Frghoyan",1975));
        person.add(new Persons("Shushan","Danielyan",1975));
        person.add(new Persons("Anun","Azganun",1234));
        person.add(new Persons("Ruben","Frghoyan",5678));
        person.add(new Persons("Ruben","Frghoyan",9876));
        person.add(new Persons("Ruben","Frghoyan",1996));
        person.add(new Persons("Meri","Mikayelyan",1998));
        person.add(new Persons("Khachik","Frghoyan",1997));
        person.add(new Persons("Hrach","Mikayelyan",2000));
        person.add(new Persons("Mher","Shahinyan",1995));
        person.add(new Persons("Karen","Frghoyan",1975));
        person.add(new Persons("Shushan","Danielyan",1975));
        person.add(new Persons("Anun","Azganun",1234));
        person.add(new Persons("Ruben","Frghoyan",5678));
        person.add(new Persons("Ruben","Frghoyan",9876));


    }

    private void startRecyclerView() {

        RecyclerView recyclerView=findViewById(R.id.recycler_view);

        Adapter adapter= new Adapter(person,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


}
