import'package:flutter/material.dart';
import 'package:google_fonts/google_fonts.dart';
import'package:provider/provider.dart';
import'GroceryItem.dart';
import'GroceryManager.dart';
class GroceryItemScreen extends StatefulWidget {
  final Function onCreate;
   final Function onUpdate;
   final GroceryItem original;
   final bool isUpdating;

   const GroceryItemScreen({ required this.onCreate,required this.onUpdate,required this.original}):isUpdating=(original!=null);


  @override
  State<GroceryItemScreen> createState() => _GroceryItemScreenState();
}

class _GroceryItemScreenState extends State<GroceryItemScreen>
{
final  _nameController = TextEditingController();
String _name='';
DateTime _dueDate=DateTime.now();
TimeOfDay _timeofDay=TimeOfDay.now();
Importance _importance=Importance.low;
Color _currentColor=Colors.green;
int _sliderValue=0;

@override
void initState()
{
  if(widget.original!=null)
    {
      _nameController.text = widget.original.name;
      _name=widget.original.name;
      _dueDate=widget.original.date;
      _currentColor=widget.original.colour;
      _sliderValue=widget.original.quantity;
    }


}
Widget buildNameField()
{
  return Column(
    crossAxisAlignment: CrossAxisAlignment.start,
    children:[
      Text("Item Name",style:GoogleFonts.lato(fontWeight:FontWeight.w600,fontSize:20)),
      TextField(
        //controller:_nameController,
        cursorColor: _currentColor,
        decoration:InputDecoration(
          hintText:'e.g. Apple, Orange, Mango',
          enabledBorder: const UnderlineInputBorder(
            borderSide:BorderSide(color:Colors.white),
          ),
          focusedBorder: UnderlineInputBorder(
            borderSide:BorderSide(color:_currentColor)
          ),
          border:UnderlineInputBorder(
            borderSide:BorderSide(color:_currentColor),
          )
        ),
      ),
    ]
  );
}
Widget buildImportanceField()
{
  return Column(
    crossAxisAlignment:CrossAxisAlignment.start,
    children:[
      Text("Importance",style:GoogleFonts.lato(fontWeight:FontWeight.w600,fontSize:20),),
      Wrap(
        spacing:16.0,
        children:[
          ChoiceChip(
            selectedColor: Colors.black,
            selected:_importance==Importance.low,
            label:Text("Low"),
            onSelected:(selected)
              {
                setState((){_importance=Importance.low;});
              }
          ),
          ChoiceChip(
            selectedColor:Colors.black,
            selected:_importance==Importance.medium,
            label:Text("Medium"),
            onSelected:(selected){
              setState((){
                _importance=Importance.medium;
              });
            }
          ),
          ChoiceChip(
            selectedColor:Colors.black,
            label:Text("High"),
            selected:_importance==Importance.high,
            onSelected: (selected){
              setState((){
                _importance=Importance.high;
              });
            },

          ),

        ]
      ),
    ]
  );
}

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar:AppBar(
        actions:[
          IconButton(
            icon:Icon(Icons.check),
            onPressed: (){}
          )
        ],
        title:Text("GroceryItem",style:GoogleFonts.lato(fontWeight:FontWeight.w600)),
      ),
          body:Container(
            padding:const EdgeInsets.all(16.0),
            child:ListView(
              children:[
                buildNameField(),
                buildImportanceField(),


              ]
            ),

    )

    );
  }
}
