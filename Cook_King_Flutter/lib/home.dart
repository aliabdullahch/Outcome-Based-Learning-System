import'package:flutter/material.dart';
import'cook_king_theme.dart';
import'ExploreScreen.dart';
import'RecipieScreen.dart';
import'GroceryList.dart';
import'package:provider/provider.dart';
import'main.dart';
import'package:cook_king/TabManger.dart';

class Home extends StatefulWidget{
  Home();
  @override
  _HomeState createState()=>_HomeState();
}
class _HomeState extends State<Home>
{

  static List<Widget> pages=[
    ExploreScreen(),
    SimpleRecipieScreen(),
    GroceryScreen()
  ];
  _HomeState();



  @override
  Widget build (BuildContext context)
  {
return Consumer<TabManager>(builder:(context,tabmanager,child){
  return Scaffold(
    appBar:AppBar(
        title:Text("Cook King",
            style:FooderlichTheme.darkTextTheme.headline6)
    ),
    body:pages[tabmanager.selected_tab],
    bottomNavigationBar: BottomNavigationBar(
        selectedItemColor:Colors.green,
        currentIndex:tabmanager.selected_tab,
        onTap:(index){
          tabmanager.go_to_tab(index);
        },
        items:[
          const BottomNavigationBarItem(
              icon:Icon(Icons.explore),
              label:"Explore"
          ),
          const BottomNavigationBarItem(
            icon:Icon(Icons.book),
            label:"Recipies",
          ),
          const BottomNavigationBarItem(
            icon:Icon(Icons.list),
            label:"To Buy",
          ),
        ]

    ),
  );
});

  }

}
