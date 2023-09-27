package com.example.w1820984_meallibrary_cw

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MealFromWebService : AppCompatActivity() {
    lateinit var backButton: Button
    lateinit var enteredMealText: EditText
    lateinit var retrieveMealButton: Button
    lateinit var showMeals: TextView
    private var savedText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_meal_from_web_service)

        backButton = findViewById(R.id.backButton)
        enteredMealText = findViewById(R.id.enteredMeal)
        retrieveMealButton = findViewById(R.id.retrieveMeal)
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
            val mealToSearch = enteredMealText.text.toString()
            if (mealToSearch.isNotBlank()) {
                search(mealToSearch)
            }
        }
    }

    fun search(mealToSearch: String) {
        // collecting all the JSON string
        var stb = StringBuilder()
        val url_string = "https://www.themealdb.com/api/json/v1/1/search.php?s=$mealToSearch"
        val url = URL(url_string)
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection

        runBlocking {
            withContext(Dispatchers.IO) {
                var bf = BufferedReader(InputStreamReader(con.inputStream))
                var line: String? = bf.readLine()

                if (line == "{\"meals\":null}") {
                    runOnUiThread {
                        showMeals.text = "Meals not found in the Web Service! Make sure to enter a valid meal"
                    }
                } else {
                    while (line != null) {
                        stb.append(line + "\n")
                        line = bf.readLine()
                    }
                    parseJSON(stb)
                }
            }
        }
    }

    suspend fun parseJSON(stb: java.lang.StringBuilder) {
        // this contains the full JSON returned by the Web Service
        val json = JSONObject(stb.toString())

        var allMeals = java.lang.StringBuilder()

        // Information about all the meals extracted by this function
        var jsonArray: JSONArray = json.getJSONArray("meals")

        // extract all the meals from the JSON array
        for (i in 0 until jsonArray.length()) {
            val mealJson: JSONObject = jsonArray.getJSONObject(i)

            // extract the meal data
            val mealName = mealJson.optString("strMeal", null)
            val drinkAlternative = mealJson.optString("strDrinkAlternate", null)
            val category = mealJson.optString("strCategory", null)
            val area = mealJson.optString("strArea", null)
            val instructions = mealJson.optString("strInstructions", null)
            val mealThumb = mealJson.optString("strMealThumb", null)
            val tag = mealJson.optString("strTags", null)
            val youtube = mealJson.optString("strYoutube", null)

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
        }

        runOnUiThread {
            showMeals.text = allMeals
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