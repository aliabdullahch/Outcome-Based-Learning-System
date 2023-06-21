import 'package:flutter/material.dart';
class CircleImage extends StatelessWidget
{
  final double imageRadius=32.0;
  final ImageProvider? imageProvider;
  CircleImage({required this.imageProvider});
  @override
  Widget build(BuildContext context)
  {
    return CircleAvatar(
      radius:this.imageRadius,
      backgroundColor:Colors.white,
      child:CircleAvatar(
        radius:this.imageRadius-3,
        backgroundImage:imageProvider,
      ),
    );
  }

}
