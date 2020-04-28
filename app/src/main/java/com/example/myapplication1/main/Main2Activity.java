package com.example.myapplication1.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.example.myapplication1.R;
import com.example.myapplication1.adapters.Adapter;
import com.example.myapplication1.model.Persons;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.FormatFlagsConversionMismatchException;

public class Main2Activity extends AppCompatActivity {

    private ArrayList<Persons> person = new ArrayList<>();

    private static final String TAG = "MyActivity";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //setPersonsDetails();
        loadJson();
        startRecyclerView();
    }


    private void startRecyclerView() {

        RecyclerView recyclerView=findViewById(R.id.recycler_view);

        Adapter adapter= new Adapter(person,this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void loadJson() {

       try{
           JSONObject object = new JSONObject(loadFromAssets());
           JSONArray personArray = object.getJSONArray("person");

           for (int i=0;i<personArray.length();i++){
               JSONObject currentPerson= personArray.getJSONObject(i);
               person.add(getPersonObject(currentPerson));
           }



       }catch (Exception e){
           e.printStackTrace();
       }

    }

    private Persons getPersonObject(JSONObject currentPerson) throws JSONException {
        String name;
        String surname;
        int id;
        Persons person;

            name= currentPerson.getString("name");
            surname= currentPerson.getString("surname");
            id=currentPerson.getInt("id");
            person= new Persons(name,surname,id);


        return person;
    }

    private String loadFromAssets() {

        String json = null;
        try {
            InputStream is = getAssets().open("persons.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }





}
