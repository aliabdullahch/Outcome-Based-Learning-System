import'package:flutter/material.dart';
class Post
{
  String id;
  String profileImageUrl;
  String comment;
  String foodPictureUrl;
  String timeStamp;
  Post({required this.id,required this.profileImageUrl,required this.comment,required this.foodPictureUrl,required this.timeStamp});
  factory Post.fromJson(Map<String,dynamic> json)
  {
    return Post(
      id:json['id']??'',
      profileImageUrl:json['profileImageUrl']?? '',
      comment: json['comment']??'',
      foodPictureUrl:json['foodPictureUrl']?? '',
      timeStamp:json['timeStamp']?? ''
    );
  }

}
