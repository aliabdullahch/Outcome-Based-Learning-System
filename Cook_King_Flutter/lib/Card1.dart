import'package:flutter/material.dart';
import'ExploreRecipie.dart';
import"cook_king_theme.dart";
class Card1 extends StatelessWidget
{
  final ExploreRecipie recipie;
  Card1({required this.recipie});
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
        decoration:BoxDecoration(
          image:DecorationImage(
            image:AssetImage(recipie.backgroundImage),
            fit:BoxFit.cover,
          ),
          borderRadius:BorderRadius.all(Radius.circular(16)),
        ),
        child:Stack(
          children:[
            Text(recipie.subtitle,style:FooderlichTheme.darkTextTheme.bodyText1),
            Positioned(
              child:Text(recipie.title,style:FooderlichTheme.darkTextTheme.headline2),
              top:20,
            ),
            Positioned(
              child:Text(recipie.message,style:FooderlichTheme.darkTextTheme.bodyText2),
              bottom:30,
              right:0,
            ),
            Positioned(
              child:Text(recipie.authorName,style:FooderlichTheme.darkTextTheme.bodyText1),
              right:0,
              bottom:10
            ),
          ]
        ),

      ),
    );
  }



}
