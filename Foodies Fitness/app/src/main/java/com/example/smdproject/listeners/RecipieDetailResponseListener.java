package com.example.smdproject.listeners;

import com.example.smdproject.Models.RandomRecipieApiResponse;
import com.example.smdproject.Models.RecipieDetailResponse;

public interface RecipieDetailResponseListener
{
    void didFetch(RecipieDetailResponse response, String message);
    void didError(String message);
}
