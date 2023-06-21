import 'package:provider/provider.dart';
import'package:flutter/material.dart';
class TabManager extends ChangeNotifier
{
  int selected_tab=0;
  void go_to_tab(int index)
  {
    selected_tab=index;
    notifyListeners();
  }
  void go_to_Recipies()
  {
    selected_tab = 1;
    notifyListeners();
  }


}

