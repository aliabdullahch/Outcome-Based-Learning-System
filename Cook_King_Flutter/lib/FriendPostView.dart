import'package:flutter/material.dart';
import'mock_fooderlich_service.dart';
import'cook_king_theme.dart';
import 'ExploreData.dart';
import'ExploreRecipie.dart';
import'Post.dart';
import'Card1.dart';
import'Card2.dart';
import'Card3.dart';
import'AuthorCard.dart';
import'CircleImage.dart';
import'FriendPostTile.dart';
class FriendPostView extends StatelessWidget
{
  List<Post> friend_posts=[];
  FriendPostView({required this.friend_posts});
  @override
  Widget build (BuildContext context)
  {
    return Padding(
      padding:const EdgeInsets.only(top:5, bottom:16, left:16),
      child:Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children:[
          Text("Social Chef \u{F373}",style:FooderlichTheme.darkTextTheme.headline2),
          const SizedBox(
            height:40
          ),
          Container(
            height:400,
            child:ListView.separated(
              physics: const NeverScrollableScrollPhysics(),
              itemCount:friend_posts.length,
              scrollDirection:Axis.vertical,
              itemBuilder:(context,index)
                {
                  final current_post=friend_posts[index];
                  return FriendPostTile(post_data: current_post);
                },
              separatorBuilder: (context,index)
                {
                  return const SizedBox(height:16);
                }
            ),
          ),
              ],
            ),
          );
  }



}
