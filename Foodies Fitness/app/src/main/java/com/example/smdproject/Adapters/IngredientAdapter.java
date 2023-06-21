package com.example.smdproject.Adapters;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import android.view.LayoutInflater;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import com.example.smdproject.Models.ExtendedIngredient;
import com.example.smdproject.Models.Ingredient;
import com.example.smdproject.R;
import com.example.smdproject.listeners.RecipieDetailResponseListener;
import com.squareup.picasso.Picasso;


public class IngredientAdapter extends RecyclerView.Adapter<IngredientViewHolder> {
    Context context;
    ArrayList<ExtendedIngredient> ingredients;

    public IngredientAdapter(Context context, ArrayList<ExtendedIngredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientViewHolder(LayoutInflater.from(context).inflate(R.layout.ingredient_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientViewHolder holder, int position) {
        holder.quant.setText(ingredients.get(position).original);
        holder.ingre_name.setText(ingredients.get(position).name);

    }


    @Override
    public int getItemCount() {
        return ingredients.size();
    }
}
class IngredientViewHolder extends RecyclerView.ViewHolder
{
    TextView quant;
    ImageView ingre_img;
    TextView ingre_name;


    public IngredientViewHolder(@NonNull View itemView) {
        super(itemView);
        quant=itemView.findViewById(R.id.ingredient_quant);
        ingre_name=itemView.findViewById(R.id.ingredient_name);

    }
}

