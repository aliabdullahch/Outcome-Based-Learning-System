import 'package:cook_king/GroceryManager.dart';
import'package:flutter/material.dart';
import 'package:provider/provider.dart';
import'cook_king_theme.dart';
import'home.dart';
import'RecipieScreen.dart';
import'home.dart';
import'TabManger.dart';
import'GroceryManager.dart';

void main()
{
  runApp(const Cook_King());
}

class Cook_King extends StatelessWidget
{
  const Cook_King();
  @override
  Widget build (BuildContext context)
  {
      return MaterialApp(
        title:'Cook King',
        theme:FooderlichTheme.dark(),
        home:MultiProvider(
          providers:[
            ChangeNotifierProvider<TabManager>(create:(context)=>TabManager(),),
            ChangeNotifierProvider<GroceryManager>(create:(context)=>GroceryManager(),),
          ],
          child:Home()
        ),
    );
  }

}

