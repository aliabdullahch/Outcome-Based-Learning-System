package com.example.smdproject;

import android.content.Context;

import com.example.smdproject.Models.RandomRecipieApiResponse;
import com.example.smdproject.Models.RecipieDetailResponse;
import com.example.smdproject.listeners.RandomRecipieResponseListener;
import com.example.smdproject.listeners.RecipieDetailResponseListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager
{
    public RequestManager(Context context) {
        this.context = context;
    }

    Context context;
    Retrofit retrofit =new Retrofit.Builder().baseUrl("https://api.spoonacular.com/").addConverterFactory(GsonConverterFactory.create()).build();

    public void getRandomRecipies(RandomRecipieResponseListener listener,List <String> tags)
    {
        CallRandomRecipies callRandomRecipies=retrofit.create(CallRandomRecipies.class);
        Call<RandomRecipieApiResponse> call=callRandomRecipies.callRandomRecipie(context.getString(R.string.apiKey),"90",tags);
    call.enqueue(new Callback<RandomRecipieApiResponse>() {
        @Override
        public void onResponse(Call<RandomRecipieApiResponse> call, Response<RandomRecipieApiResponse> response) {
            if(!response.isSuccessful())
            {
                listener.didError(response.message());
            return;
            }
            listener.didFetch(response.body(), response.message());
        }

        @Override
        public void onFailure(Call<RandomRecipieApiResponse> call, Throwable t) {
            listener.didError(t.getMessage());

        }
    });


    }
public void getRecipieDetail(RecipieDetailResponseListener recipieDetailResponseListener,int id)
{
    CallRecipieDetails callRecipieDetails=retrofit.create(CallRecipieDetails.class);
    Call<RecipieDetailResponse> call=callRecipieDetails.callRecipieDetail(id,context.getString(R.string.apiKey));
    call.enqueue(new Callback<RecipieDetailResponse>() {
        @Override
        public void onResponse(Call<RecipieDetailResponse> call, Response<RecipieDetailResponse> response) {
            if(!response.isSuccessful())
            {
                recipieDetailResponseListener.didError(response.message());
                return;
            }
            recipieDetailResponseListener.didFetch(response.body(), response.message());
        }

        @Override
        public void onFailure(Call<RecipieDetailResponse> call, Throwable t) {
            recipieDetailResponseListener.didError(t.getMessage());

        }
    });

}








    private interface CallRandomRecipies
    {
        @GET("recipes/random")
        Call<RandomRecipieApiResponse>callRandomRecipie(

                @Query("apiKey") String name,
                @Query("number") String number,
                @Query("tags")  List<String> tags
                );

    }
    private interface CallRecipieDetails
    {
        @GET("recipes/{id}/information")
        Call<RecipieDetailResponse>callRecipieDetail(
                @Path("id") int id,
                @Query("apiKey") String name
        );


    }

}
