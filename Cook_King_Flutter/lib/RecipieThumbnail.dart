import 'SimpleRecipie.dart';
import'mock_fooderlich_service.dart';
import'cook_king_theme.dart';
import'package:flutter/material.dart';
class RecipieThumbnail extends StatelessWidget
{
  SimpleRecipie temp_recipie;
  RecipieThumbnail({required this.temp_recipie});
  @override
  Widget build (BuildContext context)
  {
    return Container(
      child:Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children:[
          Expanded(
            child:ClipRRect(
      child:Image.asset("${temp_recipie.dishimage}"),
      borderRadius:BorderRadius.all(Radius.circular(10),
      )
            )
          ),
          const SizedBox(height:8),
          Text(temp_recipie.title,style:FooderlichTheme.darkTextTheme.bodyText1),
          Text("${temp_recipie.duration}",style:FooderlichTheme.darkTextTheme.bodyText2),
        ]
      )
    );
  }

}
