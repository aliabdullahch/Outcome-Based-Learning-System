import'package:flutter/material.dart';
class SimpleRecipie
{
  String id;
  String dishimage;
  String title;
  String source;
  String duration;
  List<String> information;
  SimpleRecipie({required this.id,required this.dishimage,required this.title,required this.source,required this.duration,required this.information});
  factory SimpleRecipie.fromJson(Map<String,dynamic> json)
  {
    return SimpleRecipie(
      id:json['id'] as String,
      dishimage:json['dishImage'] as String,
      title:json['title']as String,
      source:json['source']as String,
      duration:json['duration']as String,
      information:json['information'].cast<String> () as List<String>,
    );
  }

}
