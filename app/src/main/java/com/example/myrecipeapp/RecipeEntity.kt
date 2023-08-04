package com.example.myrecipeapp


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val title: String,
    val mealType: String,
    val servingSize: String,
    val difficulty: String,
    val ingredients: List<String>,
    val preparationSteps: List<String>
)

