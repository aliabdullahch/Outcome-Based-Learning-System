package com.example.smdproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class M2 extends AppCompatActivity {
    private EditText searchEditText;
    private Button searchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_m2);
        searchEditText = findViewById(R.id.search_edit_text);
        searchButton = findViewById(R.id.search_button);
        BottomNavigationView bottomNavigationView=findViewById(R.id.bottom_bar);
        bottomNavigationView.setSelectedItemId(R.id.origin_id);
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
                        startActivity(new Intent(getApplicationContext(),M1.class));
                        overridePendingTransition(0,0);
                        return true;
                    }
                    case R.id.origin_id:
                    {
                        return true;
                    }
                }
                return false;
            }
        });

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dishName = searchEditText.getText().toString().trim();

                // Determine the origin and image resource ID for the dish
                String origin;
                int imageResId;
                switch (dishName.toLowerCase()) {
                    case "veggie and hummus sandwich":
                        imageResId = R.drawable.hummus;
                        origin = "1. According to history, the sandwich we know and love today was created in 1762 in England. \n2. Pressly’s sister and associate, Dot Thompson, had invented the sandwiches, chopping the vegetables finely and draining them twice, using no gelatin, only mayonnaise. \n3. You might call her the fairy godmother of Charlotte’s favorite vegetable sandwich, an idea born in her kitchen in 1941. ";
                        break;
                    case "cheese burger stuffed baked potato":
                        imageResId = R.drawable.burger;
                        origin = "1. It was the head of the Northern Pacific Railway who started serving baked Idaho potatoes to diners in 1908, with a selection of fillings and topping to make them more interesting, more exciting. \n2. Considerable evidence suggests that either the United States or Germany (the city of Hamburg) was the first country where two slices of bread and a ground beef patty were combined into a cheeseburger. \n3. In this way they were originated and now combined together to make a new dish. ";
                        break;
                    case "pbj english muffin":
                        imageResId = R.drawable.muffin;
                        origin = "1. Who Invented English Muffins? As you might expect, we have a British man to thank for the English muffin, though this tasty bakery treat was born here in the United States. \n2. In 1874, Samuel Bath Thomas emigrated from Plymouth, England, to New York and started making thinner, pre-cut crumpets while he worked in a bakery. \n3. By 1880, Thomas had caught the American entrepreneurial spirit, opening a bakery in New York City. Eventually, grocery stores and hotels were buying baked goods from him. \n4. In 1926, Thomas trademarked his interpretation of a crumpet as the English muffin. However, the first known use of the term “English muffin” was in 1894. ";
                        break;
                    case "tuna lunch salad":
                        imageResId = R.drawable.tuna;
                        origin = "1. Tuna salad has been eaten for over 100 years. \n2. The first written reference to tuna salad, in America, appeared in 1907, and by 1914 dozens of recipes had been published \n3. Tuna salad, especially with celery, is similar to chicken salad while also being more convenient (due to the use of canned tuna), a fact that helped its early rise in popularity. ";
                        break;
                    case "beetroot hummus":
                        imageResId = R.drawable.beetroot;
                        origin = "1. This recipe is Syrian in origin, though it is also eaten in Lebanon and throughout the Levant. \n2. It can also be made like muhammara, with ground toasted walnuts and pomegranate molasses added to the mixture in lieu of tahini. \n3. Mainly originated from Lebanese. ";
                        break;
                    case "salmon pate":
                        imageResId = R.drawable.salmon;
                        origin = "1. Delicious salmon pate in a tube originated from Royal Sweden. \n2. Mainly Produced in Scotland with Farmed Salmon from Scotland and Norway. \n3. An ancient history that has seen the influence of the Phoenicians, Greeks and Romans, who contributed to the birth and growth of this wonderful city from where this recipe originated. ";
                        break;
                    case "penne ratatouille":
                        imageResId = R.drawable.penne;
                        origin = "1. Ratatouille is a French Provençal dish of stewed vegetables that originated in Nice and is sometimes referred to as ratatouille niçoise. \n2. While Penne is a type of pasta with a short, tubular shape. Originally from Genoa in the Liguria region of Italy. \n3. Ratatouille, the classic vegetable stew of Provence, is featured in all the small restaurants along its coast. \n4. Ratatouille is generally served with a pasta sauce, tossing it with cooked penne before garnishing it with olive oil, olives, grated Parmesan cheese, and parsley or basil. ";
                        break;
                    case "avocado and chicken salad":
                        imageResId = R.drawable.avocado_salad;
                        origin = "1. The chicken salad that's beloved today originated in Wakefield, Rhode Island. In 1863, Liam Gray, founder of Town Meats, was the first to mix leftover chicken with grapes, mayonnaise and tarragon. \n2. Back in the early salad eating days (circa 1st century CE), ancient Greeks and Romans gathered and layered raw vegetables, drizzling vinegar, oil, and herbs over top to create the world's first salad. \n3. Now a days avocado added in this chicken salad is a healthy recipe very famous. ";
                        break;
                    case "avocado devilled eggs":
                        imageResId = R.drawable.eggs;
                        origin = "1. The mashing of yolks to create a stuffing for the eggs was first seen in the 13th century in Spain. \n2. By the 15th century, deviled eggs, closer to what we know them as, were commonly found across Europe. \n3. In some regions of the South and the Midwest, deviled eggs are also called salad or dressed eggs when they are served at a church function, to avoid the term deviled. ";
                        break;
                    case "quinoa cake":
                        imageResId = R.drawable.cake;
                        origin = "1. Quinoa is an Andean plant which originated in the area surrounding Lake Titicaca in Peru and Bolivia. \n2. Quinoa was cultivated and used by pre-Columbian civilizations and was replaced by cereals on the arrival of the Spanish, despite being a local staple food at the time. \n3. Known as the “mother of all grains,” quinoa was revered by the Incas as a sacred plant and used in religious festivals and ceremonial offerings to their deities. Traditionally, quinoa seeds have been roasted and ground to make flour, added to cakes to make quinoa cakes. ";
                        break;
                    case "kimchi fried rice":
                        imageResId = R.drawable.kimchi;
                        origin = "1. Kimchi fried rice is a variety of bokkeum-bap (fried rice), a popular dish in South Korea. \n2. Kimchi is the most important traditional fermented food in Korea. Historically, the tradition of making kimchi among Koreans started as a necessity of storing and preserving vegetables during the long harsh cold winters when many people died of starvation. \n3. It forms an essential part of Korean meals, transcending class and regional differences. The collective practice of Kimjang reaffirms Korean identity and is an excellent opportunity for strengthening family cooperation. ";
                        break;
                    case "miso soup":
                        imageResId = R.drawable.miso;
                        origin = "1. It is thought that miso originated as a fermented food in ancient China. \n2. It is most likely that it was introduced to Japan via mainland China and the Korean Peninsula in the Asuka period during the 7th Century. \n3. Miso soup is made from miso paste as well as a traditional Japanese fish stock called Dashi. \n4. At a restaurant called Aji no Sanpei, it's rumored that a drunk customer requested noodles to be placed in their miso soup. This may be how miso ramen got its start. This happened in 1953 in snowy Sapporo, Hokkaido. The restaurant, Aji no Sanpei, is credited with creating miso ramen. ";
                        break;
                    case "biryani":
                        imageResId = R.drawable.biryani;
                        origin = "1. The word “biryani” comes from the Persian word “birian” which means “fried before cooking.” \n2. One could conclude that the biryani originated in Iran (previously known as Persia). \n3. Another interesting story traces the origins of the dish to Mumtaz Mahal (1593-1631), Shah Jahan's queen who inspired the Taj Mahal. \n4. It is said that Mumtaz once visited the army barracks and found the Mughal soldiers looking weak and undernourished. She asked the chef to prepare a special dish that combined meat and rice to provide balanced nutrition to the soldiers – and the result was biryani of course! ";
                        break;
                    case "chicken korma":
                        imageResId = R.drawable.korma;
                        origin = "1. Finding its roots in the Mughal cuisine, Murgh Korma can be traced back to the 16th century and to the Mughal incursions into the region. \n2. Kormas were often prepared in the Mughal court kitchens and it is said to have been served to Shah Jahan and his guests at the inauguration of the Taj Mahal. \n3. Boneless chicken pieces are braised in a rich, creamy sauce made from yoghurt and nuts (almonds and cashews are favourites), with delicate, aromatic flavours, such as cardamom, rosewater and saffron. The word korma is derived from the Turkish word kavurma, which literally means 'cooked meat'. ";
                        break;
                    case "pancakes":
                        imageResId = R.drawable.pancake;
                        origin = "1. The first recorded mention of pancakes was in 600 BC from a poet in ancient Greece. This breakfast treat was made of wheat flour, olive oil, honey, and curdled milk which is a little different from the pancakes we know today. \n2. 600 BC - The first recorded mention of pancakes dates back to ancient Greece and comes from a poet who described warm pancakes in one of his writings. 1100 AD – Shrove Tuesday (Pancake Day) becomes a traditional way to use up dairy products before lent – the pancake breakfast is born. \n3. Nonetheless, pancakes were once so-called because they were cakes cooked in pans. It seems to follow that knowing that fact about pancakes, or “pancakes,” is different from knowing what pancakes are called, that their name is a combination of expressions for pans and cakes, and that pancakes are cakes cooked in pans. ";
                        break;
                    case "pizza":
                        imageResId = R.drawable.pizza;
                        origin = "1. Pizza was first invented in Naples, Italy as a fast, affordable, tasty meal for working-class Neapolitans on the go. \n2. While we all know and love these slices of today, pizza actually didn't gain mass appeal until the 1940s, when immigrating Italians brought their classic slices to the United States. \n3. Based on etymology, the “Vocabolario Etimologico della Lingua Italiana” reveals that pizza comes from the dialectal pinza from the Latin pinsere, which means to pound or stamp. Other etymologists suggest it is related to the Lombardic word bizzo or pizzo, which means mouthful, and is related to the English word bite. \n4. Many people credit baker Raffaele Esposito from the Naples region of Italy for first creating the dish. Others believe that the history of pizza dates far further back than Esposito's era of the late 1800s. Much of the debate comes down to what you consider a real pizza and the evolution of food over the centuries.";
                        break;
                    case "halim":
                        imageResId = R.drawable.haleem;
                        origin = "1. Haleem originated as an Arabic dish with meat and pounded wheat as the chief ingredients. \n2. It was introduced to Hyderabad by the Arab diaspora during the rule of the sixth Nizam, Mahbub Ali Khan, and later became an integral part of Hyderabadi cuisine during the rule of the seventh Nizam, Mir Osman Ali Khan. \n3. Haleem traces its origins back to a dish mentioned in Saif al-Dawlah Al-Hamdani's Kitab al-Tabikh (The Book of Recipes), written in 10th century Syria. ";
                        break;
                    case "nihari":
                        imageResId = R.drawable.nihari;
                        origin = "1. The word “Nihar” originated from the Arabic word, “Nahar” which means “morning”. \n2. It was originally eaten by Nawabs in the Mughal Empire as a breakfast item after their morning prayers. \n3. Nihari was developed in Old Delhi, India, during the reign of the Mughal Empire. ";
                        break;
                    case "kebab":
                        imageResId = R.drawable.kebab;
                        origin = "1. Kebab derives from a Persian term for the dish that passed into both Arabic (as kabāb) and Turkish (as kebap). \n2. Kebabs are thought to have originated among transhumant peoples in Central Asia, whose meat-heavy diet was transformed somewhat in an urban context where vegetables were more readily available. \n3. They are thought to have originated in Turkey when soldiers used to grill chunks of freshly hunted animals skewered on swords over open field fires. \n4. This origin is backed up by a Turkish script of Kyssa-I Yusuf in 1377 and is now the oldest known source where kebab is stated as a food item. ";
                        break;
                    case "dal":
                        imageResId = R.drawable.daal;
                        origin = "1. Lentil dishes have been known to Indians since very early times. Archaeological evidence indicates that the Indian dal made its first appearance in the Indus Valley Civilization, where lentils – of all kinds – were a staple food. \n2. Whilst lentil dishes have been prominent in India for quite some time, archaeological evidence suggests that the history of daal dates back to the Indus Valley Civilisation. \n3. The Better India credits the creation of dal makhani, one of the most decadent and delicious dals, to Kundan Lal Gujaral, founder of the restaurant Moti Mahal. He added tomatoes and cream to the traditional black gram dal preparation, birthing a new classic. \n4. There are different stories for every type of dal. ";
                        break;
                    default:
                        origin = "Unknown";
                        imageResId = R.drawable.unknown;
                        break;
                }

                // Create an Intent to start the DishDetailActivity and pass the dish name, origin, and image resource ID as extras
                Intent intent = new Intent(M2.this, M21.class);
                intent.putExtra("dishName", dishName);
                intent.putExtra("origin", origin);
                intent.putExtra("imageResId", imageResId);
                startActivity(intent);

            }
        });
    }
}