import'package:flutter/material.dart';
import'EmptyGroceryScreen.dart';
import'main.dart';
import'GroceryManager.dart';
import'package:provider/provider.dart';
import'GroceryItemScreen.dart';
import'GroceryManager.dart';
import'GroceryItem.dart';
class GroceryScreen extends StatelessWidget
{

  GroceryScreen();
  Widget buildGroceryScreen()
  {
    return Consumer<GroceryManager>(builder:(context,manager,child){
      if(manager.isEmptyGroceryList())
        {
          return EmptyGroceryScreen();
        }
      else
          {
            return GroceryItemScreen(onCreate:(){},onUpdate:(){},original:GroceryItem());
          }
    });

  }


  @override
  Widget build (BuildContext context)
  {
   return Scaffold(
     floatingActionButton:FloatingActionButton(
       child:const Icon(Icons.add),
       onPressed:()
       {
         final g_manager=Provider.of<GroceryManager>(context,listen:false);
          Navigator.push(context,
         MaterialPageRoute(
           builder:(context)=>GroceryItemScreen(
               onCreate:(GroceryItem obj)
               {
                 g_manager.addItem(obj);
               },
             onUpdate:(){},
             original:GroceryItem(),

           ),
         ));
          //Navigator.pop(context);
       }
     ),
     body:   buildGroceryScreen(),
   );

  }


}
