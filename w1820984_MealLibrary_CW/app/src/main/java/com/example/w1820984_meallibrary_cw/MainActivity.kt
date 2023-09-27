package com.example.w1820984_meallibrary_cw

//////////////////
//VIDEO LINK: https://drive.google.com/file/d/1fDzvlwKclYpu0HrKJvOVeW4UiHNW-Btc/view?usp=share_link
//////////////////

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.StringReader

class MainActivity : AppCompatActivity() {
    val mealtxt = """{ 
        "meals": [
            {
            "Meal":"Sweet and Sour Pork",
            "DrinkAlternate":null,
            "Category":"Pork",
            "Area":"Chinese",
            "Instructions":"Preparation\r\n1. Crack the egg into a bowl. Separate the egg white and yolk.\r\n\r\nSweet and Sour Pork\r\n2. Slice the pork tenderloin into ips.\r\n\r\n3. Prepare the marinade using a pinch of salt, one teaspoon of starch, two teaspoons of light soy sauce, and an egg white.\r\n\r\n4. Marinade the pork ips for about 20 minutes.\r\n\r\n5. Put the remaining starch in a bowl. Add some water and vinegar to make a starchy sauce.\r\n\r\nSweet and Sour Pork\r\nCooking Inuctions\r\n1. Pour the cooking oil into a wok and heat to 190\u00b0C (375\u00b0F). Add the marinated pork ips and fry them until they turn brown. Remove the cooked pork from the wok and place on a plate.\r\n\r\n2. Leave some oil in the wok. Put the tomato sauce and white sugar into the wok, and heat until the oil and sauce are fully combined.\r\n\r\n3. Add some water to the wok and thoroughly heat the sweet and sour sauce before adding the pork ips to it.\r\n\r\n4. Pour in the starchy sauce. Stir-fry all the ingredients until the pork and sauce are thoroughly mixed together.\r\n\r\n5. Serve on a plate and add some coriander for decoration.",
            "MealThumb":"https:\/\/www.themealdb.com\/images\/media\/meals\/1529442316.jpg",
            "Tags":"Sweet",
            "Youtube":"https:\/\/www.youtube.com\/watch?v=mdaBIhgEAMo",
            "Ingredient1":"Pork",
            "Ingredient2":"Egg",
            "Ingredient3":"Water",
            "Ingredient4":"Salt",
            "Ingredient5":"Sugar",
            "Ingredient6":"Soy Sauce",
            "Ingredient7":"Starch",
            "Ingredient8":"Tomato Puree",
            "Ingredient9":"Vinegar",
            "Ingredient10":"Coriander",
            "Measure1":"200g",
            "Measure2":"1",
            "Measure3":"Dash",
            "Measure4":"1\/2 tsp",
            "Measure5":"1 tsp ",
            "Measure6":"10g",
            "Measure7":"10g",
            "Measure8":"30g",
            "Measure9":"10g",
            "Measure10":"Dash"
            },
            
            
            {
            "Meal":"Chicken Marengo",
            "DrinkAlternate":null,
            "Category":"Chicken",
            "Area":"French",
            "Instructions":"Heat the oil in a large flameproof casserole dish and stir-fry the mushrooms until they start to soften. Add the chicken legs and cook briefly on each side to colour them a little.\r\nPour in the passata, crumble in the stock cube and stir in the olives. Season with black pepper \u2013 you shouldn\u2019t need salt. Cover and simmer for 40 mins until the chicken is tender. Sprinkle with parsley and serve with pasta and a salad, or mash and green veg, if you like.",
            "MealThumb":"https:\/\/www.themealdb.com\/images\/media\/meals\/qpxvuq1511798906.jpg",
            "Tags":null,
            "Youtube":"https:\/\/www.youtube.com\/watch?v=U33HYUr-0Fw",
            "Ingredient1":"Olive Oil",
            "Ingredient2":"Mushrooms",
            "Ingredient3":"Chicken Legs",
            "Ingredient4":"Passata",
            "Ingredient5":"Chicken Stock Cube",
            "Ingredient6":"Black Olives",
            "Ingredient7":"Parsley",
            "Measure1":"1 tbs",
            "Measure2":"300g",
            "Measure3":"4",
            "Measure4":"500g",
            "Measure5":"1",
            "Measure6":"100g ",
            "Measure7":"Chopped",
            "Source":"https:\/\/www.bbcgoodfood.com\/recipes\/3146682\/chicken-marengo",
            "ImageSource":null,
            "CreativeCommonsConfirmed":null,
            "dateModified":null
            },
            
            {
            "Meal":"Beef Banh Mi Bowls with Sriracha Mayo, Carrot & Pickled Cucumber",
            "DrinkAlternate":null,
            "Category":"Beef",
            "Area":"Vietnamese",
            "Instructions":"Add'l ingredients: mayonnaise, siracha\r\n\r\n1\r\n\r\nPlace rice in a fine-mesh sieve and rinse until water runs clear. Add to a small pot with 1 cup water (2 cups for 4 servings) and a pinch of salt. Bring to a boil, then cover and reduce heat to low. Cook until rice is tender, 15 minutes. Keep covered off heat for at least 10 minutes or until ready to serve.\r\n\r\n2\r\n\r\nMeanwhile, wash and dry all produce. Peel and finely chop garlic. Zest and quarter lime (for 4 servings, zest 1 lime and quarter both). Trim and halve cucumber lengthwise; thinly slice crosswise into half-moons. Halve, peel, and medium dice onion. Trim, peel, and grate carrot.\r\n\r\n3\r\n\r\nIn a medium bowl, combine cucumber, juice from half the lime, \u00bc tsp sugar (\u00bd tsp for 4 servings), and a pinch of salt. In a small bowl, combine mayonnaise, a pinch of garlic, a squeeze of lime juice, and as much sriracha as you\u2019d like. Season with salt and pepper.\r\n\r\n4\r\n\r\nHeat a drizzle of oil in a large pan over medium-high heat. Add onion and cook, stirring, until softened, 4-5 minutes. Add beef, remaining garlic, and 2 tsp sugar (4 tsp for 4 servings). Cook, breaking up meat into pieces, until beef is browned and cooked through, 4-5 minutes. Stir in soy sauce. Turn off heat; taste and season with salt and pepper.\r\n\r\n5\r\n\r\nFluff rice with a fork; stir in lime zest and 1 TBSP butter. Divide rice between bowls. Arrange beef, grated carrot, and pickled cucumber on top. Top with a squeeze of lime juice. Drizzle with sriracha mayo.",
            "MealThumb":"https:\/\/www.themealdb.com\/images\/media\/meals\/z0ageb1583189517.jpg",
            "Tags":null,
            "Youtube":"",
            "Ingredient1":"Rice",
            "Ingredient2":"Onion",
            "Ingredient3":"Lime",
            "Ingredient4":"Garlic Clove",
            "Ingredient5":"Cucumber",
            "Ingredient6":"Carrots",
            "Ingredient7":"Ground Beef",
            "Ingredient8":"Soy Sauce",
            "Ingredient9":"",
            "Measure1":"White",
            "Measure2":"1",
            "Measure3":"1",
            "Measure4":"3",
            "Measure5":"1",
            "Measure6":"3 oz ",
            "Measure7":"1 lb",
            "Measure8":"2 oz ",
            "Source":"",
            "ImageSource":null,
            "CreativeCommonsConfirmed":null,
            "dateModified":null
            },
            
            {
            "Meal":"Leblebi Soup",
            "DrinkAlternate":null,
            "Category":"Vegetarian",
            "Area":"Tunisian",
            "Instructions":"Heat the oil in a large pot. Add the onion and cook until translucent.\r\nDrain the soaked chickpeas and add them to the pot together with the vegetable stock. Bring to the boil, then reduce the heat and cover. Simmer for 30 minutes.\r\nIn the meantime toast the cumin in a small ungreased frying pan, then grind them in a mortar. Add the garlic and salt and pound to a fine paste.\r\nAdd the paste and the harissa to the soup and simmer until the chickpeas are tender, about 30 minutes.\r\nSeason to taste with salt, pepper and lemon juice and serve hot.",
            "MealThumb":"https:\/\/www.themealdb.com\/images\/media\/meals\/x2fw9e1560460636.jpg",
            "Tags":"Soup",
            "Youtube":"https:\/\/www.youtube.com\/watch?v=BgRifcCwinY",
            "Ingredient1":"Olive Oil",
            "Ingredient2":"Onion",
            "Ingredient3":"Chickpeas",
            "Ingredient4":"Vegetable Stock",
            "Ingredient5":"Cumin",
            "Ingredient6":"Garlic",
            "Ingredient7":"Salt",
            "Ingredient8":"Harissa Spice",
            "Ingredient9":"Pepper",
            "Ingredient10":"Lime",
            "Measure1":"2 tbs",
            "Measure2":"1 medium finely diced",
            "Measure3":"250g",
            "Measure4":"1.5L",
            "Measure5":"1 tsp ",
            "Measure6":"5 cloves",
            "Measure7":"1\/2 tsp",
            "Measure8":"1 tsp ",
            "Measure9":"Pinch",
            "Measure10":"1\/2 ",
            "Source":"http:\/\/allrecipes.co.uk\/recipe\/43419\/leblebi--tunisian-chickpea-soup-.aspx",
            "ImageSource":null,
            "CreativeCommonsConfirmed":null,
            "dateModified":null
            }
        ]
    }
        """

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addMealButton = findViewById<Button>(R.id.addMeals)
        val searchMealByIngredientButton = findViewById<Button>(R.id.searchByIngredient)
        val searchMealButton = findViewById<Button>(R.id.searchMeal)
        val searchFromWebServiceButton = findViewById<Button>(R.id.fromWebService)

        val db = Room.databaseBuilder(this, MealDatabase::class.java,"Meal_Database").build()
        val mealDao = db.mealDao()

        addMealButton.setOnClickListener {
            addMealsToDatabase(mealDao)
            Toast.makeText(this, "Meals added to database", Toast.LENGTH_SHORT).show()
        }

        searchMealByIngredientButton.setOnClickListener {
            val intent = Intent(this, SearchMealsByIngredient::class.java)
            startActivity(intent)
        }

        searchMealButton.setOnClickListener {
            val intent = Intent(this, SearchMeals::class.java)
            startActivity(intent)
        }

        searchFromWebServiceButton.setOnClickListener {
            val intent = Intent(this, MealFromWebService::class.java)
            startActivity(intent)
        }
    }

    fun addMealsToDatabase(mealDao: MealDao){
        runBlocking {
            launch {
                val bf = BufferedReader(StringReader(mealtxt))
                val stb = StringBuilder()
                var line: String? = bf.readLine()
                while (line != null) {
                    stb.append(line + "\n")
                    line = bf.readLine()
                }

                parseJSON(stb, mealDao)
            }
        }
    }

    suspend fun parseJSON(stb: StringBuilder, mealDao: MealDao) {
        //This contains the full JSON returned by the Web Service
        val json = JSONObject(stb.toString())

        val mealList = mutableListOf<Meal>()

        val jsonArray: JSONArray = json.getJSONArray("meals") //extract all the meals from the JSON array
        for (i in 0 until jsonArray.length()) {
            val mealJson: JSONObject = jsonArray[i] as JSONObject //this is a json object

            // extract the meal data
            val mealName = mealJson.optString("Meal", null)
            val drinkAlternative = mealJson.optString("DrinkAlternate", null)

            val category = mealJson.optString("Category", null)
            val area = mealJson.optString("Area", null)
            val instructions = mealJson.optString("Instructions", null)
            val mealThumb = mealJson.optString("MealThumb", null)
            val tag = mealJson.optString("Tags", null)
            val youtube = mealJson.optString("Youtube", null)

            val ingredient1 = mealJson.optString("Ingredient1", null)
            val ingredient2 = mealJson.optString("Ingredient2", null)
            val ingredient3 = mealJson.optString("Ingredient3", null)
            val ingredient4 = mealJson.optString("Ingredient4", null)
            val ingredient5 = mealJson.optString("Ingredient5", null)
            val ingredient6 = mealJson.optString("Ingredient6", null)
            val ingredient7 = mealJson.optString("Ingredient7", null)
            val ingredient8 = mealJson.optString("Ingredient8", null)
            val ingredient9 = mealJson.optString("Ingredient9", null)
            val ingredient10 = mealJson.optString("Ingredient10", null)
            val ingredient11 = mealJson.optString("Ingredient11", null)
            val ingredient12 = mealJson.optString("Ingredient12", null)
            val ingredient13 = mealJson.optString("Ingredient13", null)
            val ingredient14 = mealJson.optString("Ingredient14", null)
            val ingredient15 = mealJson.optString("Ingredient15", null)
            val ingredient16 = mealJson.optString("Ingredient16", null)
            val ingredient17 = mealJson.optString("Ingredient17", null)
            val ingredient18 = mealJson.optString("Ingredient18", null)
            val ingredient19 = mealJson.optString("Ingredient19", null)
            val ingredient20 = mealJson.optString("Ingredient20", null)

            val measure1 = mealJson.optString("Measure1", null)
            val measure2 = mealJson.optString("Measure2", null)
            val measure3 = mealJson.optString("Measure3", null)
            val measure4 = mealJson.optString("Measure4", null)
            val measure5 = mealJson.optString("Measure5", null)
            val measure6 = mealJson.optString("Measure6", null)
            val measure7 = mealJson.optString("Measure7", null)
            val measure8 = mealJson.optString("Measure8", null)
            val measure9 = mealJson.optString("Measure9", null)
            val measure10 = mealJson.optString("Measure10", null)
            val measure11 = mealJson.optString("Measure11", null)
            val measure12 = mealJson.optString("Measure12", null)
            val measure13 = mealJson.optString("Measure13", null)
            val measure14 = mealJson.optString("Measure14", null)
            val measure15 = mealJson.optString("Measure15", null)
            val measure16 = mealJson.optString("Measure16", null)
            val measure17 = mealJson.optString("Measure17", null)
            val measure18 = mealJson.optString("Measure18", null)
            val measure19 = mealJson.optString("Measure19", null)
            val measure20 = mealJson.optString("Measure20", null)

            val source = mealJson.optString("Source", null)
            val imageSource = mealJson.optString("ImageSource", null)
            val creativeCommonsConfirmed = mealJson.optString("CreativeCommonsConfirmed", null)
            val dateModified = mealJson.optString("dateModified", null)


            runBlocking {
                withContext(Dispatchers.IO) {
                    //Create the Meal object and add it to the list
                    val meals = Meal(mealName, drinkAlternative, category, area, instructions, mealThumb, tag, youtube,
                        ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10,
                        ingredient11, ingredient12, ingredient13, ingredient14, ingredient15, ingredient16, ingredient17, ingredient18, ingredient19, ingredient20,
                        measure1, measure2, measure3, measure4, measure5, measure6, measure7, measure8, measure9, measure10,
                        measure11, measure12, measure13, measure14, measure15, measure16, measure17, measure18, measure19, measure20,
                        source, imageSource, creativeCommonsConfirmed, dateModified)
                    mealList.add(meals)
                    mealDao.insertAll(meals)
                }
            }
        }
    }


}