package com.example.myapplication1.cards;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class  AbstractCard extends RecyclerView.ViewHolder {

    Context context;


    public AbstractCard(@NonNull View itemView, Context context) {
        super(itemView);
        this.context=context;
    }

    public abstract void bind(Object obj);


}
