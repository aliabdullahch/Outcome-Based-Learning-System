import 'package:flutter/material.dart';
import 'package:flutter/services.dart';
import'SimpleRecipie.dart';
import 'ExploreData.dart';
import'ExploreRecipie.dart';
import 'Post.dart';
import 'dart:convert';
class MockFooderlichService
{
   Future<ExploreData> getExploreData() async
  {
    final todayRecipies= await _getTodayRecipies();
    final friendPosts=  await _getFriendsFeed();
    return   ExploreData(todayRecipies,friendPosts);
  }
  Future <List<ExploreRecipie>>  _getTodayRecipies() async
  {
    //Giving the delay
    await Future.delayed(const Duration(milliseconds:1000));
    //Loading the data
    final String_data= await _loadAsset('assets/sample_data/sample_explore_recipes.json');
    //converting the data into Dart Map
    final Map<String,dynamic> json=jsonDecode(String_data);

    if(json['recipes']!=null)
      {
        List<ExploreRecipie> my_recipes=[];
        json['recipes'].forEach((v){my_recipes.add(ExploreRecipie.fromJson(v));});
        return my_recipes;
      }
    else
        {
          return [];
        }
  }
   Future<List<Post>> _getFriendsFeed() async{
    // Putting in the delay
    await Future.delayed(const Duration(milliseconds:1000));
    // Getting the data
    final string_data=await _loadAsset('assets/sample_data/sample_friends_feed.json');
    final Map<String,dynamic> json=jsonDecode(string_data);

    if(json['feed']!=null)
      {
        List<Post> my_posts=[];
        json['feed'].forEach((v){my_posts.add(Post.fromJson(v));});
        return my_posts;
      }
    else
        {
          return [];
        }

  }
   Future<List<SimpleRecipie>> getSimpleRecipies() async
  {
    // Putting the delay
    await Future.delayed(const Duration(milliseconds:1000));
    // Taking the Dat i the String out
    final string_data=await _loadAsset('assets/sample_data/sample_recipes.json');
    // Converting the data into the map format
    final Map<String,dynamic> json = jsonDecode(string_data);
    //now checking the each map in the recipes form

    if(json['recipes']!=null)
      {
        final List<SimpleRecipie> recipies=[];
        json['recipes'].forEach((v){ recipies.add(SimpleRecipie.fromJson(v));});
        return recipies;
      }
    else
        {
          return [];
        }
  }
   Future<String> _loadAsset(String path) async
  {
    return  rootBundle.loadString(path);
  }


}
