package com.example.data.repository

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.net.URL

data class Product(
    val id : Int,
    val name : String,
    val description : String,
    val image : String,
    @SerializedName("price_current")
    val priceCurrent : Double,
    @SerializedName("price_old")
    val priceOld : Double,
    @SerializedName("category_id")
    val categoryId : Int,
    val measure : Int,
    @SerializedName("measure_unit")
    val measureUnit : String,
    @SerializedName("energy_per_100_grams")
    val energyPer100Grams : Double,
    @SerializedName("proteins_per_100_grams")
    val proteinsPer100Grams : Double,
    @SerializedName("fats_per_100_grams")
    val fatsPer100Grams : Double,
    @SerializedName("carbohydrates_per_100_grams")
    val carbohydratesPer100Grams : Double,
    @SerializedName("tag_ids")
    val tagIds : List<Int>
)

interface ProductsRepository {
    suspend fun getProducts() : List<Product>
}

class ProductsRepositoryImpl() : ProductsRepository {


    override suspend fun getProducts() : List<Product> {
        val jsonUrl = URL("https://anika1d.github.io/WorkTestServer/Products.json")
        val jsonString = jsonUrl.readText()
        val gson = Gson()
        return gson.fromJson(jsonString, Array<Product>::class.java).toList()
    }
}