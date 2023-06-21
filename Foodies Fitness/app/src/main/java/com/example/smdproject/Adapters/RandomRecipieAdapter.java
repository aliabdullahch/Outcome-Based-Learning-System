package com.example.smdproject.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.smdproject.Models.Recipe;
import com.example.smdproject.R;
import com.example.smdproject.listeners.RecipieClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RandomRecipieAdapter extends RecyclerView.Adapter<RandomRecipieViewHolder>
{
    Context context;
    ArrayList<Recipe> recipies_array;
    RecipieClickListener recipieClickListener;

    public RandomRecipieAdapter(Context context, ArrayList<Recipe> recipies_array,RecipieClickListener r_c_l) {
        this.context = context;
        this.recipies_array = recipies_array;
        this.recipieClickListener = r_c_l;
    }


    @NonNull
    @Override
    public RandomRecipieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new RandomRecipieViewHolder(LayoutInflater.from(context).inflate(R.layout.recipie_card_layout,parent,false));
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull RandomRecipieViewHolder holder, int position) {
        holder.dish_name.setText(recipies_array.get(position).title+" ");
        holder.servings.setText(recipies_array.get(position).servings+"Servings");
        holder.likes.setText(recipies_array.get(position).aggregateLikes+"Likes");
        holder.time.setText(recipies_array.get(position).preparationMinutes+"Minutes");
        Picasso.get().load(recipies_array.get(position).image).into(holder.dish_image);
        holder.random_recipie_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipieClickListener.OnRecipieClick((recipies_array.get(holder.getAdapterPosition()).id));
            }
        });
    }




    @Override
    public int getItemCount() {
        return recipies_array.size();
    }
}
class RandomRecipieViewHolder extends RecyclerView.ViewHolder
{

    TextView dish_name;
    ImageView dish_image;
    TextView servings;
    TextView likes;
    TextView time;
    CardView random_recipie_card;

    public RandomRecipieViewHolder(@NonNull View itemView) {
        super(itemView);
       dish_name=itemView.findViewById(R.id.recipie_name_id);
       dish_image=itemView.findViewById(R.id.recipie_image_id);
       servings=itemView.findViewById(R.id.serve_id);
       likes=itemView.findViewById(R.id.like_id);
       time=itemView.findViewById(R.id.time_id);
       random_recipie_card=itemView.findViewById(R.id.r_card);
    }
}
