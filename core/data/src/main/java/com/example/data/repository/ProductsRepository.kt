package com.example.data.repository

import com.example.data.dto.Category
import com.example.data.dto.Product
import com.example.data.dto.Tag
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.net.URL



interface ProductsRepository {
    suspend fun getProducts() : List<Product>
    suspend fun getCategories() : List<Category>
    suspend fun getTags() : List<Tag>
}

class ProductsRepositoryImpl() : ProductsRepository {


    override suspend fun getProducts() : List<Product> {
        val jsonString = URL("https://anika1d.github.io/WorkTestServer/Products.json").readText()
        val gson = Gson()
        return gson.fromJson(jsonString, Array<Product>::class.java).toList()
    }
    override suspend fun getCategories() : List<Category> {
        val jsonString = URL("https://anika1d.github.io/WorkTestServer/Categories.json").readText()
        val gson = Gson()
        return gson.fromJson(jsonString, Array<Category>::class.java).toList()
    }
    override suspend fun getTags() : List<Tag> {
        val jsonString = URL("https://anika1d.github.io/WorkTestServer/Tags.json").readText()
        val gson = Gson()
        return gson.fromJson(jsonString, Array<Tag>::class.java).toList()
    }
}