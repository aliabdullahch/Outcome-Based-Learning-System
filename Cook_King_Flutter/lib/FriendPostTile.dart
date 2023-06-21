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
class FriendPostTile extends StatelessWidget
{
  Post post_data;
  FriendPostTile({ required this.post_data});
  @override
  Widget build (BuildContext context)
  {
    return Row(
      crossAxisAlignment:CrossAxisAlignment.start,
      mainAxisAlignment:MainAxisAlignment.start,
      children:[
        CircleImage(imageProvider:AssetImage(post_data.profileImageUrl)),
        const SizedBox(
          width:16,
        ),
        Expanded(
          child:Column(
            children:[
              Text(post_data.comment,style:FooderlichTheme.darkTextTheme.bodyText2),
            ]
          ),
        ),

      ]
    );
  }

}
