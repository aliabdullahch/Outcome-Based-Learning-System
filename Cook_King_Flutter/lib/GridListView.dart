import'package:flutter/material.dart';
import'RecipieThumbnail.dart';
import'cook_king_theme.dart';
import'SimpleRecipie.dart';

class SimpleRecipieList extends StatelessWidget
{
  List<SimpleRecipie> simple_recipies=[];
   SimpleRecipieList({required this.simple_recipies});
  @override
  Widget build (BuildContext context)
  {
    return Padding(
      padding:const EdgeInsets.only(top:16,bottom:16,right:16,left:16),
        child:Container(
          color:Colors.transparent,
          child:GridView.builder(
              itemCount:simple_recipies.length,
              gridDelegate:const SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount:2),
              itemBuilder: (context,index)
              {
                final current_recipie=simple_recipies[index];
                return RecipieThumbnail(temp_recipie:current_recipie);
              }
          ),
          ),
        );

  }


}
