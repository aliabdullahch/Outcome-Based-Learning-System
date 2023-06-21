import'package:flutter/material.dart';
import'package:flutter/painting.dart';
enum Importance{low,medium,high}
class GroceryItem
{
  String id;
  String name;
   Importance importance;
   Color colour;
   int quantity;
   DateTime date=DateTime.now();
   bool isComplete;
  GroceryItem({ this.id='',
   this.name='',
   this.importance=Importance.low,
   this.colour=Colors.blue,
   this.quantity=0,
   this.isComplete=false });

GroceryItem copyWith({String? id ,String? name='',Importance? importance=Importance.low,Color? colo,int? quant,DateTime? dat,bool? Iscomp})
{
  return GroceryItem(
    id: id ?? this.id,
    name:name ??this.name,
    importance:importance ?? this.importance,
    colour:colo?? this.colour,
    quantity: quant?? quantity,
    isComplete:Iscomp?? this.isComplete,
  );
}

}
