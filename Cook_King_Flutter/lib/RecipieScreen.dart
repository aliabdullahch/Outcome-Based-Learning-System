import'mock_fooderlich_service.dart';
import'package:flutter/material.dart';
import'GridListView.dart';
import'SimpleRecipie.dart';
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
class SimpleRecipieScreen extends StatelessWidget
{
  final mock=MockFooderlichService();
  @override
  Widget build (BuildContext context)
  {
    return FutureBuilder(
      future: mock.getSimpleRecipies(),
      builder:(context,snapshot)
        {
          if(snapshot.connectionState==ConnectionState.done)
            {
              if(snapshot.data ==null)
                {
                  return const Center (
                    child:Text("The list is  null!"),
                  );
                 // return SimpleRecipieList(simple_recipies: snapshot.data as List<SimpleRecipie>);
                }else
                  {
                     return SimpleRecipieList(simple_recipies: snapshot.data as List<SimpleRecipie>);
                  }

            }else
              {
                return const Center(
                    child:CircularProgressIndicator());
              }
        }
    );
  }

}
