import'package:flutter/material.dart';
import'ExploreData.dart';
import'ExploreRecipie.dart';
import'Post.dart';
import'SimpleRecipie.dart';

class ExploreData
{
  final List<ExploreRecipie> todayRecipies;
  final List<Post> friendPosts;
 ExploreData(this.todayRecipies,this.friendPosts);

}
