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
class TodayRecipieListView extends StatelessWidget
{
  List<ExploreRecipie> recipies=[];
  TodayRecipieListView(this.recipies);
  @override
  Widget build (BuildContext context)
  {
    return Padding(
      padding:const EdgeInsets.only(top:16,bottom:16,left:16),
      child:Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children:[
          Text("Explore Today's Recipie",style:FooderlichTheme.darkTextTheme.headline2),
          const SizedBox(
            height:16
          ),
          Container(
            height:400,
            color:Colors.transparent,
            child:ListView.separated(
              scrollDirection: Axis.horizontal,
                itemBuilder: (context,index)
            {
              final recipie=recipies[index];
              return buildCard(recipie);
            },
                separatorBuilder: (context,index)
                {
                  return const SizedBox(
                    width:16,

                  );
                },
                itemCount:recipies.length),
             // decoration:const BoxDecoration(
             //   borderRadius: BorderRadius.all(Radius.circular(16)),
             // )
          )
        ]
      )
    );
  }
  Widget buildCard(ExploreRecipie r)
  {
    if(r.cardType==RecipeCardType.card1)
      {
        return Card1(recipie:r);
      }else if(r.cardType==RecipeCardType.card2)
        {
          return Card2(recipie:r);
        }else {
      return Card3(recipie:r);
    }

  }


}
