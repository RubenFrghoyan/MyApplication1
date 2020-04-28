package com.example.myapplication1.cards;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.myapplication1.R;
import com.example.myapplication1.model.Persons;

public class CardViewPerson extends AbstractCard {

    private AppCompatTextView name;
    private LinearLayout container;

    public CardViewPerson(@NonNull View itemView,Context context) {
        super(itemView,context);

        name= itemView.findViewById(R.id.name);
        container=itemView.findViewById(R.id.my_container);

    }

    public  CardViewPerson(Context context,ViewGroup parent){
        this(LayoutInflater.from(context).inflate(R.layout.my_card_view,parent,false ),context);
        this.context=context;

    }


    @Override
    public void bind(Object obj) {

        final Persons person= (Persons) obj;

        name.setText(person.getName()+"   "+person.getSurname());
        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Toast.makeText(context, "The id is:  "+person.getiD(),Toast.LENGTH_LONG).show();
            }
        });

    }


}
