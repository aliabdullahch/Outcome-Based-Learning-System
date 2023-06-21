import'package:flutter/material.dart';
import 'cook_king_theme.dart';
import'CircleImage.dart';
class AuthorCard extends StatefulWidget
{
  String authorName;
  String title;
  ImageProvider? imageProvider;
  AuthorCard({required this.authorName,required this.title,required this.imageProvider});
  @override
  _AuthorCardState createState()=>_AuthorCardState();

}
class _AuthorCardState extends State<AuthorCard>
{
  _AuthorCardState();
  bool isFavorite=false;
  @override
  Widget build (BuildContext context)
  {
    return Container(
      padding:const EdgeInsets.all(16),
      child:Row(
        mainAxisAlignment:MainAxisAlignment.spaceBetween,
        children:[
          Row(
            children:[
              CircleImage(imageProvider:widget.imageProvider),
              const SizedBox(width:8),
              Column(
                crossAxisAlignment: CrossAxisAlignment.start,
                children:[
                  Text(widget.authorName,style:FooderlichTheme.lightTextTheme.bodyText1),
                  Text(widget.title,style:FooderlichTheme.lightTextTheme.bodyText1),
                ]
              )
            ]
          ),
          IconButton(
              icon:Icon(isFavorite ? Icons.favorite :Icons.favorite_border),
            iconSize:30,
            color:Colors.pink,
            onPressed:(){
                setState(
                    (){
                      isFavorite=!isFavorite;
                    }
                );
            }

  )
        ]
      ),
    );
  }

}
