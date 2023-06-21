import'package:flutter/material.dart';
import'dart:core';
import 'dart:io';
class Ingredient {
  String imageUrl;
  String title;
  String source;

  Ingredient({
    required this.imageUrl,
    required this.title,
    required this.source,
  });
  factory Ingredient.fromJson(Map<String,dynamic> json)
  {
    return Ingredient(
      imageUrl:json['imageUrl']??'',
      title:json['title']?? '',
      source:json['source']?? ''
    );
  }

}

