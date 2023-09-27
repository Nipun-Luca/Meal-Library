package com.example.w1820984_meallibrary_cw

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import android.widget.*
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedInputStream
import java.net.HttpURLConnection
import java.net.URL

class SearchMeals : AppCompatActivity() {
    lateinit var viewText: SpannableStringBuilder
    lateinit var viewMealsText: TextView
    private var savedText: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_meals)

        val db = Room.databaseBuilder(applicationContext, MealDatabase::class.java, "Meal_Database").build()
        val mealDao = db.mealDao()

        val backButton = findViewById<Button>(R.id.backButton)
        val enteredMealText = findViewById<EditText>(R.id.enteredMeal)
        val searchButton = findViewById<Button>(R.id.searchButton)
        viewMealsText = findViewById(R.id.viewMeals)


        if (savedInstanceState != null) {
            savedText = savedInstanceState.getString("saved_text")
            viewMealsText.text = savedText
        }

        backButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        searchButton.setOnClickListener {
            val mealToSearch = enteredMealText.text.toString()

            runBlocking {
                withContext(Dispatchers.IO) {
                    val allMeals = mealDao.getAll()
                    val builder = SpannableStringBuilder()
                    for (i in allMeals) {
                        if (i.name.lowercase().contains(mealToSearch.lowercase())) {
                            displayMeals(i, builder)
                        } else if (i.ingredient1?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient2?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient3?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient4?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient5?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient6?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient7?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient8?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient9?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient10?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient11?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient12?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient13?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient14?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient15?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient16?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient17?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient18?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient19?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        } else if (i.ingredient20?.lowercase()?.contains(mealToSearch.lowercase()) == true) {
                            displayMeals(i, builder)
                        }
                    }
                    viewText = builder
                }
            }
            viewMealsText.text = viewText
        }
    }

    suspend fun displayMeals(i: Meal, builder: SpannableStringBuilder){
        //Display meal details
        builder.append("Meal: \"${i.name}\" \n")
        builder.append("DrinkAlternative: \"${i.drinkAlternate}\" \n")
        builder.append("Category: \"${i.category}\" \n")
        builder.append("Area: \"${i.area}\" \n")
        builder.append("Instructions: \"${i.instructions}\" \n")
        builder.append("Tag: \"${i.tags}\" \n")
        builder.append("Youtube: \"${i.youtube}\" \n")

        if (i.ingredient1.toString() != "null" && i.ingredient1.toString().isNotBlank())
            builder.append("Ingredient1: \"${i.ingredient1}\" \n")
        if (i.ingredient2.toString() != "null" && i.ingredient2.toString().isNotBlank())
            builder.append("Ingredient2: \"${i.ingredient2}\" \n")
        if (i.ingredient3.toString() != "null" && i.ingredient3.toString().isNotBlank())
            builder.append("Ingredient3: \"${i.ingredient3}\" \n")
        if (i.ingredient4.toString() != "null" && i.ingredient4.toString().isNotBlank())
            builder.append("Ingredient4: \"${i.ingredient4}\" \n")
        if (i.ingredient5.toString() != "null" && i.ingredient5.toString().isNotBlank())
            builder.append("Ingredient5: \"${i.ingredient5}\" \n")
        if (i.ingredient6.toString() != "null" && i.ingredient6.toString().isNotBlank())
            builder.append("Ingredient6: \"${i.ingredient6}\" \n")
        if (i.ingredient7.toString() != "null" && i.ingredient7.toString().isNotBlank())
            builder.append("Ingredient7: \"${i.ingredient7}\" \n")
        if (i.ingredient8.toString() != "null" && i.ingredient8.toString().isNotBlank())
            builder.append("Ingredient8: \"${i.ingredient8}\" \n")
        if (i.ingredient9.toString() != "null" && i.ingredient9.toString().isNotBlank())
            builder.append("Ingredient9: \"${i.ingredient9}\" \n")
        if (i.ingredient10.toString() != "null" && i.ingredient10.toString().isNotBlank())
            builder.append("Ingredient10: \"${i.ingredient10}\" \n")
        if (i.ingredient11.toString() != "null" && i.ingredient11.toString().isNotBlank())
            builder.append("Ingredient11: \"${i.ingredient11}\" \n")
        if (i.ingredient12.toString() != "null" && i.ingredient12.toString().isNotBlank())
            builder.append("Ingredient12: \"${i.ingredient12}\" \n")
        if (i.ingredient13.toString() != "null" && i.ingredient13.toString().isNotBlank())
            builder.append("Ingredient13: \"${i.ingredient13}\" \n")
        if (i.ingredient14.toString() != "null" && i.ingredient14.toString().isNotBlank())
            builder.append("Ingredient14: \"${i.ingredient14}\" \n")
        if (i.ingredient15.toString() != "null" && i.ingredient15.toString().isNotBlank())
            builder.append("Ingredient15: \"${i.ingredient15}\" \n")
        if (i.ingredient16.toString() != "null" && i.ingredient16.toString().isNotBlank())
            builder.append("Ingredient16: \"${i.ingredient16}\" \n")
        if (i.ingredient17.toString() != "null" && i.ingredient17.toString().isNotBlank())
            builder.append("Ingredient17: \"${i.ingredient17}\" \n")
        if (i.ingredient18.toString() != "null" && i.ingredient18.toString().isNotBlank())
            builder.append("Ingredient18: \"${i.ingredient18}\" \n")
        if (i.ingredient19.toString() != "null" && i.ingredient19.toString().isNotBlank())
            builder.append("Ingredient19: \"${i.ingredient19}\" \n")
        if (i.ingredient20.toString() != "null" && i.ingredient20.toString().isNotBlank())
            builder.append("Ingredient20: \"${i.ingredient20}\" \n")

        if (i.measure1.toString() != "null" && i.measure1.toString().isNotBlank())
            builder.append("Measure1: \"${i.measure1}\" \n")
        if (i.measure2.toString() != "null" && i.measure2.toString().isNotBlank())
            builder.append("Measure2: \"${i.measure2}\" \n")
        if (i.measure3.toString() != "null" && i.measure3.toString().isNotBlank())
            builder.append("Measure3: \"${i.measure3}\" \n")
        if (i.measure4.toString() != "null" && i.measure4.toString().isNotBlank())
            builder.append("Measure4: \"${i.measure4}\" \n")
        if (i.measure5.toString() != "null" && i.measure5.toString().isNotBlank())
            builder.append("Measure5: \"${i.measure5}\" \n")
        if (i.measure6.toString() != "null" && i.measure6.toString().isNotBlank())
            builder.append("Measure5: \"${i.measure6}\" \n")
        if (i.measure7.toString() != "null" && i.measure7.toString().isNotBlank())
            builder.append("Measure7: \"${i.measure7}\" \n")
        if (i.measure8.toString() != "null" && i.measure8.toString().isNotBlank())
            builder.append("Measure8: \"${i.measure8}\" \n")
        if (i.measure9.toString() != "null" && i.measure9.toString().isNotBlank())
            builder.append("Measure9: \"${i.measure9}\" \n")
        if (i.measure10.toString() != "null" && i.measure10.toString().isNotBlank())
            builder.append("Measure10: \"${i.measure10}\" \n")
        if (i.measure11.toString() != "null" && i.measure11.toString().isNotBlank())
            builder.append("Measure11: \"${i.measure11}\" \n")
        if (i.measure12.toString() != "null" && i.measure12.toString().isNotBlank())
            builder.append("Measure12: \"${i.measure12}\" \n")
        if (i.measure13.toString() != "null" && i.measure13.toString().isNotBlank())
            builder.append("Measure13: \"${i.measure13}\" \n")
        if (i.measure14.toString() != "null" && i.measure14.toString().isNotBlank())
            builder.append("Measure14: \"${i.measure14}\" \n")
        if (i.measure15.toString() != "null" && i.measure15.toString().isNotBlank())
            builder.append("Measure15: \"${i.measure15}\" \n")
        if (i.measure16.toString() != "null" && i.measure16.toString().isNotBlank())
            builder.append("Measure16: \"${i.measure16}\" \n")
        if (i.measure17.toString() != "null" && i.measure17.toString().isNotBlank())
            builder.append("Measure17: \"${i.measure17}\" \n")
        if (i.measure18.toString() != "null" && i.measure18.toString().isNotBlank())
            builder.append("Measure18: \"${i.measure18}\" \n")
        if (i.measure19.toString() != "null" && i.measure19.toString().isNotBlank())
            builder.append("Measure19: \"${i.measure19}\" \n")
        if (i.measure20.toString() != "null" && i.measure20.toString().isNotBlank())
            builder.append("Measure20: \"${i.measure20}\" \n")

        ////Reference: https://developer.android.com/reference/kotlin/android/text/style/ImageSpan
        val bitmap = loadBitmap(i.mealThumb, 50, 50)
        val imageSpan = ImageSpan(BitmapDrawable(resources, bitmap))
        builder.append("MealThumb: \n")
        builder.setSpan(imageSpan, builder.length - 1, builder.length, 0)
        ////Reference: https://developer.android.com/reference/kotlin/android/text/style/ImageSpan
        builder.append("\n\n\n")
    }

    ////Reference: https://developer.android.com/reference/kotlin/android/text/style/ImageSpan
    suspend fun loadBitmap(imageURL: String, width: Int, height: Int): Bitmap {
        return withContext(Dispatchers.IO) {
            val url = URL(imageURL)
            val con = url.openConnection() as HttpURLConnection
            val bufferStream = BufferedInputStream(con.inputStream)
            val bitmap = BitmapFactory.decodeStream(bufferStream)

            val scaledBitmap = Bitmap.createScaledBitmap(bitmap, width, height, true)

            bufferStream.close()
            bitmap.recycle()
            scaledBitmap
        }
    }
    ////Reference: https://developer.android.com/reference/kotlin/android/text/style/ImageSpan


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)

        savedText = viewMealsText.text.toString()
        outState.putString("saved_text", savedText)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        savedText = savedInstanceState.getString("saved_text")
        viewMealsText.text = savedText
    }
}