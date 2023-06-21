import 'package:cook_king/FriendPostView.dart';
import'package:flutter/material.dart';
import'mock_fooderlich_service.dart';
import 'ExploreData.dart';
import'ExploreRecipie.dart';
import'Post.dart';
import'Card1.dart';
import'Card2.dart';
import'Card3.dart';
import'AuthorCard.dart';
import'CircleImage.dart';
import'TodayRecipieListView.dart';
import'FriendPostView.dart';
class ExploreScreen extends StatelessWidget
{
  final mock_service = MockFooderlichService();
  @override
  Widget build (BuildContext context)
  {
    return FutureBuilder(
      future: mock_service.getExploreData(),
      builder:( context,snapshot)
    {
     if(snapshot.connectionState ==ConnectionState.done)
       {
       final raw_data=snapshot.data as ExploreData;
       return ListView(
         scrollDirection:Axis.vertical,
         children:[
           TodayRecipieListView(raw_data.todayRecipies),
           const SizedBox(
             height:5,
           ),
           FriendPostView(friend_posts: raw_data.friendPosts),
         ]
       );
       }
     else if(snapshot.hasError)
       {
         return Center(
           child:Text("${snapshot.error}")
         );
       }else
         {
           return Center(
             child:CircularProgressIndicator(),
           );
         }

    },

    );
  }

}

