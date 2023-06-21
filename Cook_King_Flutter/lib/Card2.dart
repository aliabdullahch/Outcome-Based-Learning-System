import'package:flutter/material.dart';
import'cook_king_theme.dart';
import'ExploreRecipie.dart';
import'AuthorCard.dart';
class Card2 extends StatelessWidget
{
  final ExploreRecipie recipie;
  Card2({required this.recipie});
  @override
  Widget build (BuildContext context)
  {
    return Center(
      child:Container(
        padding:const EdgeInsets.all(16),
        constraints: BoxConstraints.expand(
          width:300,
          height:400,
        ),
          decoration: BoxDecoration(
          image:DecorationImage(
          image:AssetImage(recipie.backgroundImage),
      fit:BoxFit.cover,
      ),
        borderRadius:BorderRadius.all(Radius.circular(16)),
      ),
        child:Column(
          children:[
            AuthorCard(
              authorName:recipie.authorName,
              title:recipie.title,
              imageProvider: AssetImage(recipie.profileImage),
            ),
            Expanded(
              child:Stack(
                children:[
                  Positioned(
                    child:Text(recipie.title,style:FooderlichTheme.lightTextTheme.headline1),
                    bottom:16,
                    right:16,
                  ),
                  Positioned(
                    left:16,
                    bottom:70,
                    child:RotatedBox(
                      quarterTurns:3,
                      child:Text(recipie.subtitle,style:FooderlichTheme.lightTextTheme.headline1),
                      ),
                    )
                ]
              )
            ),
          ]
        )
      )
    );

  }

}
