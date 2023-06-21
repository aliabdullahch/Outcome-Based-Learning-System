package com.example.smdproject.listeners;

import com.example.smdproject.Models.RandomRecipieApiResponse;

public interface RandomRecipieResponseListener
{
void didFetch(RandomRecipieApiResponse response,String message);
void didError(String message);
}
