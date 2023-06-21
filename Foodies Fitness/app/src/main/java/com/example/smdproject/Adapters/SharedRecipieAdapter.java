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

public class SharedRecipieAdapter extends RecyclerView.Adapter<SharedRecipieHolder>{
    Context context;
    ArrayList<MyRecipie>shared_Recipie_Array;

    public SharedRecipieAdapter(Context context, ArrayList<MyRecipie> shared_Recipie_Array) {
        this.context = context;
        this.shared_Recipie_Array = shared_Recipie_Array;
    }

    @NonNull
    @Override
    public SharedRecipieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        return new SharedRecipieHolder(LayoutInflater.from(context).inflate(R.layout.shared_recipie_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull SharedRecipieHolder holder, @SuppressLint("RecyclerView") int position)
    {
        holder.dish_name.setText(shared_Recipie_Array.get(position).name);
       Picasso.get().load(shared_Recipie_Array.get(position).image).into(holder.dish_image);
        holder.bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shared_Recipie_Array.get(position).shareRecipie(shared_Recipie_Array.get(position));

            }
        });
        holder.my_recipie_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent myIntent = new Intent(context, Detials_Activity.class);
                myIntent.putExtra("index", position);
                myIntent.putExtra("from","shared");
                context.startActivity(myIntent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return shared_Recipie_Array.size();
    }
}

class SharedRecipieHolder extends RecyclerView.ViewHolder
{

    TextView dish_name;
    ImageView dish_image;
    CardView my_recipie_card;
    Button bt;

    public SharedRecipieHolder(@NonNull View itemView) {
        super(itemView);
        dish_name=itemView.findViewById(R.id.shared_recipie_name_id);
        dish_image=itemView.findViewById(R.id.shared_recipie_image_id);
        bt=itemView.findViewById(R.id.shared_shared_button);
        my_recipie_card=itemView.findViewById(R.id.Shared_recipie_card);
    }
}