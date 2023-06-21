import'package:flutter/material.dart';
import'RecipieScreen.dart';
import'cook_king_theme.dart';
import'main.dart';
import'home.dart';
import'package:provider/provider.dart';
import'TabManger.dart';
class EmptyGroceryScreen extends StatelessWidget {

  EmptyGroceryScreen();
  @override
  Widget build(BuildContext context) {
     return Padding(
        padding:const EdgeInsets.all(40),
        child:
        Center(
          child:Column(
              mainAxisAlignment: MainAxisAlignment.center,
              children:[
                AspectRatio(
                  child: Image.asset('assets/fooderlich_assets/empty_list.png'),
                  aspectRatio:1/1,
                ),
                const SizedBox(height:8.0),
                Text("No Groceries!",style:TextStyle(fontSize:21)),
                const SizedBox(height:16.0),
                Text("Shopping for Ingredients. Tap the + button to add them",style:TextStyle(fontSize:10)),
                MaterialButton(
                  color:Colors.green,
                  shape:RoundedRectangleBorder(
                      borderRadius:BorderRadius.circular(30)),
                  textColor:Colors.white,
                  child:  Text("Browse Recipies"),
                  onPressed: (){
                    
                    Provider.of<TabManager>(context,listen:false).go_to_Recipies();
                  },
                ),
              ]
          ),
        )
    );;
  }
}



