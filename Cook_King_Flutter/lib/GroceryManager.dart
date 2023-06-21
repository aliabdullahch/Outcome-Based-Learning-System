import'package:flutter/material.dart';
import 'package:provider/provider.dart';
import'cook_king_theme.dart';
import'home.dart';
import'RecipieScreen.dart';
import'home.dart';
import'TabManger.dart';
import 'GroceryItem.dart';
class GroceryManager extends ChangeNotifier
{
  List<GroceryItem> _groceryItems=[];
  void updateList(GroceryItem obj, int index)
  {
    _groceryItems[index]=obj;
    notifyListeners();
  }
  void addItem(GroceryItem obj)
  {
    _groceryItems.add(obj);
    notifyListeners();
  }
  void removeItem(int index)
  {
    _groceryItems.removeAt(index);
    notifyListeners();
  }
  void setComplete(int index, bool Change)
  {
    _groceryItems[index]=_groceryItems[index].copyWith(Iscomp:Change);
    notifyListeners();
  }
  bool isEmptyGroceryList ()
  {
    return _groceryItems.isEmpty;
  }

}
