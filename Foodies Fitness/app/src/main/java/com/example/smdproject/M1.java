package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class M1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m1);
        Button hummusButton = findViewById(R.id.hummus_button);
        hummusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Spread one slice of bread with hummus and the other with avocado.\n2. Fill the sandwich with greens, bell pepper, cucumber and carrot. \n3. Slice in half and serve.");
                startActivity(intent);
            }
        });

        Button burgerButton = findViewById(R.id.burger_button);
        burgerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Pierce potatoes all over with a fork. Microwave on Medium, turning once or twice, until soft, about 20 minutes. (Alternatively, bake potatoes at 425 degrees F until tender, 45 minutes to 1 hour.) Transfer to a clean cutting board and let cool slightly.\n2. Holding them with a kitchen towel to protect your hands, make a lengthwise cut to open the potato, but don't cut all the way through. Pinch the ends to expose the flesh. \n3. Top each potato with some mayonnaise, beef, lettuce, tomato, red onion and cheese. Serve warm.");
                startActivity(intent);
            }
        });

        Button muffinButton = findViewById(R.id.muffin_button);
        muffinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Toast a whole wheat English muffin and spread 1 tablespoon natural-style peanut butter on one half and 1 tablespoon less-sugar jam on the other half.\n2. Enjoy with a whole piece of fruit, such as an orange or banana. \n3. Estimated: Carbohydrates 47 grams, protein 10 grams, fiber 7 grams.");
                startActivity(intent);
            }
        });

        Button tunaButton = findViewById(R.id.tuna_button);
        tunaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Mix one 6-ounce can water-packed tuna (drained) with 3 tablespoons light Italian vinaigrette salad dressing. \n2. Then add 1/2 cup grape tomatoes or coarsely chopped tomatoes and 1/8 cup nuts or sliced olives. \n3. Serve on 2 cups of firmly packed spinach leaves. \n4. Enjoy with an ounce of whole-grain crackers.");
                startActivity(intent);
            }
        });

        Button beetrootButton = findViewById(R.id.beetroot_button);
        beetrootButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Put chickpeas, beetroot, garlic (if using), olive oil, ground spices, lemon juice and black pepper into a small food processor; blend together until a coarse paste is formed. \n2. Spoon hummus into a small bowl or onto a serving plate; serve with toasted pitta bread or vegetables. \n3. Serve on 2 cups of firmly packed spinach leaves. \n4. Enjoy with an ounce of whole-grain crackers.");
                startActivity(intent);
            }
        });

        Button salmonButton = findViewById(R.id.salmon_button);
        salmonButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Flake salmon into a bowl. Add quark and mash together. \n2. Add horseradish sauce, lemon zest, herbs and black pepper; mix well. \n3. Serve with crusty bread or toast and garnish with watercress sprigs. \n4. Make it gluten-free by using gluten-free horseradish sauce and serving with gluten-free bread, crackers or vegetable crudites.");
                startActivity(intent);
            }
        });

        Button penneButton = findViewById(R.id.penne_button);
        penneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Cook penne pasta according to instructions on package. Drain pasta but reserve 1 cup of pasta water for later. \n2. Meanwhile, add olive oil to a large high-walled skillet over medium-high heat. Once oil is shimmering, add eggplant and 1/2 teaspoon kosher salt. Cook 8 minutes until eggplant starts to soften and browns in spots. \n3. Add chopped zucchini, squash and onion to the skillet. Cook for another 5 to 6 minutes. If the skillet seems very dry, feel free to add an extra drizzle of oil. It’s okay if some of the eggplant sticks to the skillet though. \n4. Add garlic and cook for 30 seconds before finally adding tomatoes. Use the liquid from the tomatoes to scrape up any bits stuck to the pan. Bring skillet to a simmer, stir ingredients together and reduce heat to low. Simmer for 8 to 10 minutes. \n5. Add drained pasta to skillet and stir to combine. If mixture seems thick or too dry, add in reserved pasta water by the 1/4 cup until sauce reaches the desired consistency. Season ratatouille pasta with salt and pepper, garnish with fresh thyme and serve immediately.");
                startActivity(intent);
            }
        });

        Button avocadoButton = findViewById(R.id.avocado_button);
        avocadoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Butterfly your chicken by laying the cutlet flat and slicing it parallel with a knife. Season with salt and pepper. Place the chicken breasts into a lightly oiled skillet over medium-high heat. Cook until brown and opaque throughout, 2-4 minutes each side. Remove from heat and set aside for a few minutes. Finely dice the chicken breast and toss into a large bowl. \n2. Dice your veggies. Wash, dry, peel and finely dice your red onion, apple, celery and avocado. Toss into bowl with chicken. Gently mash the avocado until all of the ingredients are mixed well. \n3. Sprinkle in cilantro. Add in lime juice and salt and pepper to taste. \n4.  Serve as a sandwich or on a bed of greens with a little drizzle of olive oil! ");
                startActivity(intent);
            }
        });

        Button eggsButton = findViewById(R.id.eggs_button);
        eggsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Cut eggs lengthwise and remove yolks. Set aside half the yolks in bowl and discard the others. \n2. In a medium bowl combine egg yolks, avocado, lime juice, mayonnaise, half of the parsley, cayenne pepper and garlic. \n3. Spoon mixture into egg whites and garnish with other half of chopped parsley. ");
                startActivity(intent);
            }
        });

        Button cakeButton = findViewById(R.id.cake_button);
        cakeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Heat oven to 375 F. Place the potatoes on a greased baking sheet. Bake for 45 minutes or until potatoes are completely soft. Meanwhile, cook quinoa according to package directions; set aside to cool. \n2. In a large bowl, combine cooked potatoes, cooked quinoa, eggs, garlic, cheese, parsley, salt, pepper and nutmeg. \n3. Heat 1 tablespoon of olive oil in a large saucepan. Form half of the quinoa mixture into 1/4-cup patties and place in the pan; cook until cakes are golden brown. Place cooked patties on a baking sheet. Repeat process with remaining oil and quinoa mixture. Bake cakes in the oven for 5 minutes to ensure they are heated through. ");
                startActivity(intent);
            }
        });

        Button kimchiButton = findViewById(R.id.kimchi_button);
        kimchiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. In a nonstick sauté pan or well-seasoned cast-iron skillet, melt butter over medium-low heat, and add onions. Cook, stirring, until the onions start to sizzle, about 2 minutes. Add kimchi and kimchi juice, and stir until it comes to a boil, about 3 minutes. Add Spam, and cook until sauce is nearly dried out, about 5 minutes. \n2. Break up the rice in the pan with a spatula, and stir it to incorporate. Turn heat to medium. Cook, stirring, until the rice has absorbed the sauce and is very hot, about 5 minutes. Stir in soy sauce and sesame oil. Taste, and adjust with more soy sauce, sesame oil or kimchi juice. Turn heat down slightly, but let the rice continue to cook, untouched, to lightly brown while you cook the eggs. \n3. Place a small nonstick sauté pan over medium heat, and add the vegetable oil. When it is hot, add eggs, season with salt and fry to your desired doneness. Serve rice topped with fried eggs, nori and a sprinkle of sesame seeds. ");
                startActivity(intent);
            }
        });

        Button misosoupButton = findViewById(R.id.misosoup_button);
        misosoupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(M1.this, M12.class);
                intent.putExtra("recipe", "1. Combine water and dashi granules in a medium saucepan over medium-high heat; bring to a boil. \n2. Reduce heat to medium and whisk in miso paste. Stir in tofu. \n3. Separate the layers of green onions, and add them to the soup. Simmer gently for 2 to 3 minutes before serving. ");
                startActivity(intent);
            }
        });
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.health_button);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.shared_button:
                    {
                        startActivity(new Intent(getApplicationContext(),Shared_Activity.class));
                        overridePendingTransition(0,0);
                        return true;

                    }
                    case R.id.my_recipes_button:
                    {
                        startActivity(new Intent(getApplicationContext(),My_Recipies_Screen.class));
                        overridePendingTransition(0,0);
                        return true;
                    }
                    case R.id.explore_button:
                    {
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    }
                    case R.id.health_button:
                    {
                        return true;
                    }
                    case R.id.origin_id:
                    {
                        startActivity(new Intent(getApplicationContext(),M2.class));
                        overridePendingTransition(0,0);
                        return true;
                    }
                }
                return false;
            }
        });
    }
}