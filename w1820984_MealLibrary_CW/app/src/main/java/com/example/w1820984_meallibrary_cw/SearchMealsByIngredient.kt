package com.example.w1820984_meallibrary_cw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class SearchMealsByIngredient : AppCompatActivity() {
    lateinit var backButton: Button
    lateinit var enteredIngredientText: EditText
    lateinit var retrieveMealButton: Button
    lateinit var saveToDB: Button
    lateinit var showMeals: TextView
    private var savedText: String? = null

    private val mealList = mutableListOf<Meal>()
    private var mealsRetrievedSuccess = false
    var allMeals = java.lang.StringBuilder()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meals_by_ingredient)

        backButton = findViewById(R.id.backButton)
        enteredIngredientText= findViewById(R.id.enteredIngredient)
        retrieveMealButton = findViewById(R.id.retrieveMeal)
        saveToDB = findViewById(R.id.saveToDB)
        showMeals = findViewById(R.id.showMeals)

        if (savedInstanceState != null) {
            savedText = savedInstanceState.getString("saved_text")
            showMeals.text = savedText
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        retrieveMealButton.setOnClickListener {
            mealsRetrievedSuccess = true
            val ingredientToSearch = enteredIngredientText.text.toString()
            if (ingredientToSearch.isNotBlank()) {
                searchForIngredient(ingredientToSearch)
            }
        }

        saveToDB.setOnClickListener {
            if (mealsRetrievedSuccess)
                saveDisplayedMealsToDB()
        }
    }

    fun searchForIngredient(ingredientToSearch: String) {
        // collecting all the JSON string
        var stb = StringBuilder()
        val url_string = "https://www.themealdb.com/api/json/v1/1/filter.php?i=$ingredientToSearch"
        val url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection

        runBlocking {
            withContext(Dispatchers.IO){
                var bf = BufferedReader(InputStreamReader(con.inputStream))
                var line: String? = bf.readLine()

                if (line == "{\"meals\":null}"){
                    runOnUiThread {
                        showMeals.text = "Meals not found using this ingredient! Make sure to enter a valid ingredient"
                        mealsRetrievedSuccess = false
                    }
                } else {
                    while (line != null) {
                        stb.append(line + "\n")
                        line = bf.readLine()
                    }
                    storeMeals(stb)
                }
            }
        }
    }

    ////Reference: Tutorial 7 code
    suspend fun storeMeals(stb: java.lang.StringBuilder) {
        // this contains the full JSON returned by the Web Service
        val json = JSONObject(stb.toString())
        // Information about all the books extracted by this function
        var retrievedMeals = mutableListOf<String>()
        val jsonArray: JSONArray = json.getJSONArray("meals") // extract all the meals from the JSON array

        for (i in 0 until jsonArray.length()) {
            val mealJson: JSONObject = jsonArray[i] as JSONObject // this is a json object

            // extract the meal id
            val idMeal = mealJson["idMeal"] as String
            retrievedMeals.add(idMeal)
        }
    ////Reference: Tutorial 7 code


        for (idMeal in retrievedMeals) {
            // collecting all the JSON string
            var stb = StringBuilder()
            val url_string = "https://www.themealdb.com/api/json/v1/1/lookup.php?i=$idMeal"
            val url = URL(url_string)
            val con: HttpURLConnection = url.openConnection() as HttpURLConnection

            runBlocking {
                withContext(Dispatchers.IO) {
                    var bf = BufferedReader(InputStreamReader(con.inputStream))
                    var line: String? = bf.readLine()

                    while (line != null) {
                        stb.append(line + "\n")
                        line = bf.readLine()
                    }
                    parseJSON(stb)
                }
            }
        }

        //Display all retrieved meals by ingredient
        runOnUiThread {
            showMeals.text = allMeals
            allMeals.setLength(0)
        }
    }


    suspend fun parseJSON(stb: java.lang.StringBuilder) {
        // this contains the full JSON returned by the Web Service
        val json = JSONObject(stb.toString())

        // Information about all the meals extracted by this function
        var jsonArray:JSONArray = json.getJSONArray("meals")

        // extract all the meals from the JSON array
        for (i in 0 until jsonArray.length()) {
            val mealJson: JSONObject = jsonArray.getJSONObject(i) // this is a json object

            // extract the meal data
            val mealName = mealJson.optString("strMeal", null)
            val drinkAlternative = mealJson.optString("strDrinkAlternate", null)
            val category = mealJson.optString("strCategory", null)
            val area = mealJson.optString("strArea", null)
            val instructions = mealJson.optString("strInstructions", null)
            val mealThumb = mealJson.optString("strMealThumb", null)
            val tag = mealJson.optString("strTags", null)
            val youtube = mealJson.optString("strYoutube", null)

            val ingredient1 = mealJson.optString("strIngredient1", null)
            val ingredient2 = mealJson.optString("strIngredient2", null)
            val ingredient3 = mealJson.optString("strIngredient3", null)
            val ingredient4 = mealJson.optString("strIngredient4", null)
            val ingredient5 = mealJson.optString("strIngredient5", null)
            val ingredient6 = mealJson.optString("strIngredient6", null)
            val ingredient7 = mealJson.optString("strIngredient7", null)
            val ingredient8 = mealJson.optString("strIngredient8", null)
            val ingredient9 = mealJson.optString("strIngredient9", null)
            val ingredient10 = mealJson.optString("strIngredient10", null)
            val ingredient11 = mealJson.optString("strIngredient11", null)
            val ingredient12 = mealJson.optString("strIngredient12", null)
            val ingredient13 = mealJson.optString("strIngredient13", null)
            val ingredient14 = mealJson.optString("strIngredient14", null)
            val ingredient15 = mealJson.optString("strIngredient15", null)
            val ingredient16 = mealJson.optString("strIngredient16", null)
            val ingredient17 = mealJson.optString("strIngredient17", null)
            val ingredient18 = mealJson.optString("strIngredient18", null)
            val ingredient19 = mealJson.optString("strIngredient19", null)
            val ingredient20 = mealJson.optString("strIngredient20", null)

            val measure1 = mealJson.optString("strMeasure1", null)
            val measure2 = mealJson.optString("strMeasure2", null)
            val measure3 = mealJson.optString("strMeasure3", null)
            val measure4 = mealJson.optString("strMeasure4", null)
            val measure5 = mealJson.optString("strMeasure5", null)
            val measure6 = mealJson.optString("strMeasure6", null)
            val measure7 = mealJson.optString("strMeasure7", null)
            val measure8 = mealJson.optString("strMeasure8", null)
            val measure9 = mealJson.optString("strMeasure9", null)
            val measure10 = mealJson.optString("strMeasure10", null)
            val measure11 = mealJson.optString("strMeasure11", null)
            val measure12 = mealJson.optString("strMeasure12", null)
            val measure13 = mealJson.optString("strMeasure13", null)
            val measure14 = mealJson.optString("strMeasure14", null)
            val measure15 = mealJson.optString("strMeasure15", null)
            val measure16 = mealJson.optString("strMeasure16", null)
            val measure17 = mealJson.optString("strMeasure17", null)
            val measure18 = mealJson.optString("strMeasure18", null)
            val measure19 = mealJson.optString("strMeasure19", null)
            val measure20 = mealJson.optString("strMeasure20", null)

            val source = mealJson.optString("strSource", null)
            val imageSource = mealJson.optString("strImageSource", null)
            val creativeCommonsConfirmed = mealJson.optString("strCreativeCommonsConfirmed", null)
            val dateModified = mealJson.optString("dateModified", null)


            //Display meal details
            allMeals.append("Meal: \"$mealName\" \n")
            allMeals.append("DrinkAlternative: \"$drinkAlternative\" \n")
            allMeals.append("Category: \"$category\" \n")
            allMeals.append("Area: \"$area\" \n")
            allMeals.append("Instructions: \"$instructions\" \n")
            allMeals.append("MealThumb: \"$mealThumb\" \n")
            allMeals.append("Tag: \"$tag\" \n")
            allMeals.append("Youtube: \"$youtube\" \n")

            for (i in 1..20) {
                val ingredient = mealJson.getString("strIngredient$i")
                if (ingredient != "null" && ingredient.isNotBlank()) {
                    allMeals.append("Ingredient$i\": \"$ingredient\" \n")
                }
            }
            for (i in 1..20) {
                val measure = mealJson.getString("strMeasure$i")
                if (measure != "null" && measure.isNotBlank()) {
                    allMeals.append("Measure$i\": \"$measure\" \n")
                }
            }

            allMeals.append("\n\n\n")

            // create the Meal object and add it to the list
            val meals = Meal(mealName, drinkAlternative, category, area, instructions, mealThumb, tag, youtube,
                ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6, ingredient7, ingredient8, ingredient9, ingredient10,
                ingredient11, ingredient12, ingredient13, ingredient14, ingredient15, ingredient16, ingredient17, ingredient18, ingredient19, ingredient20,
                measure1, measure2, measure3, measure4, measure5, measure6, measure7, measure8, measure9, measure10,
                measure11, measure12, measure13, measure14, measure15, measure16, measure17, measure18, measure19, measure20,
                source, imageSource, creativeCommonsConfirmed, dateModified)
            mealList.add(meals)
        }
    }


    fun saveDisplayedMealsToDB(){
        val db = Room.databaseBuilder(applicationContext, MealDatabase::class.java, "Meal_Database").build()
        val mealDao = db.mealDao()

        runBlocking {
            withContext(Dispatchers.IO) {
                for (meal in mealList){
                    mealDao.insertAll(meal)
                }
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        savedText = showMeals.text.toString()
        outState.putString("saved_text", savedText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedText = savedInstanceState.getString("saved_text")
        showMeals.text = savedText
    }
}
