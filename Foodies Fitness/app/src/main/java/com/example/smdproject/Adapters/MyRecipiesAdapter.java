package com.example.smdproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smdproject.Detials_Activity;
import com.example.smdproject.Models.MyRecipie;
import com.example.smdproject.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyRecipiesAdapter extends RecyclerView.Adapter<MyRecipieHolder> {
    Context context;
    ArrayList<MyRecipie> recipies_array;

    public MyRecipiesAdapter(Context context, ArrayList<MyRecipie> recipies_array) {
        this.context = context;
        this.recipies_array = recipies_array;
    }

    @NonNull
    @Override
    public MyRecipieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
         return new MyRecipieHolder(LayoutInflater.from(context).inflate(R.layout.my_recipies_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecipieHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.dish_name.setText(recipies_array.get(position).name);
        Picasso.get().load(recipies_array.get(position).image).into(holder.dish_image);
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipies_array.get(position).shareRecipie(recipies_array.get(position));

            }
        });
        holder.my_recipie_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(context, Detials_Activity.class);
                myIntent.putExtra("index", position);
                myIntent.putExtra("from","my");
                context.startActivity(myIntent);
            }
        });


    }



    @Override
    public int getItemCount() {
        return recipies_array.size();
    }
}
class MyRecipieHolder extends RecyclerView.ViewHolder
{

    TextView dish_name;
    ImageView dish_image;
    CardView my_recipie_card;
    Button bt;

    public MyRecipieHolder(@NonNull View itemView) {
        super(itemView);
        dish_name=itemView.findViewById(R.id.my_recipie_name_id);
        dish_image=itemView.findViewById(R.id.my_recipie_image_id);
       bt=itemView.findViewById(R.id.my_shared_button);
       my_recipie_card=itemView.findViewById(R.id.My_recipie_card);
    }
}
