package com.example.myapplication1.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.R;
import com.example.myapplication1.cards.AbstractCard;
import com.example.myapplication1.cards.CardViewPerson;
import com.example.myapplication1.model.Persons;

import java.util.ArrayList;



public class Adapter extends RecyclerView.Adapter  {

    private ArrayList<Persons> persons = new ArrayList<>();
    private Context context;

    public Adapter(ArrayList<Persons> details, Context cntx){

        persons= details;
        context=cntx;

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        AbstractCard card;
        card=new CardViewPerson(context,parent);

        return card;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        AbstractCard card= (AbstractCard) holder;

        Persons myPerson= persons.get(position);
        card.bind(myPerson);

    }

    @Override
    public int getItemCount() {
        return persons.size();
    }


}
