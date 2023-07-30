package com.example.myrecipeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.delay

data class Recipe(
    val title: String,
    val mealType: String,
    val servingSize: String,
    val difficulty: String,
    val ingredients: List<String>,
    val preparationSteps: List<String>
)

val sampleRecipes = listOf(
    Recipe(
        "Pancakes",
        "Breakfast",
        "2 servings",
        "Beginner",
        listOf("1 cup flour", "1 cup milk", "1 egg", "1 tbsp sugar"),
        listOf("Mix all ingredients", "Cook on a pan")
    ),
    Recipe(
        "rice",
        "lunch",
        "1 servings",
        "Beginner",
        listOf("1 cup rice", "2 cup water"),
        listOf("Mix all ingredients", "Cook on a sufuria")
    ),
)

@Composable
fun WelcomePage(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(2000)
        // Navigate to the "recipeApp" destination using NavController
        navController.navigate("recipeApp")
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Welcome to Recipe App",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun Navigation() {
    // Create a NavController
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "welcome") {
        composable("welcome") {
            WelcomePage(navController = navController)
        }
        composable("recipeApp") {
            RecipeApp()
        }
        // Add more composable destinations if needed
    }
}

@Composable
fun RecipeApp() {
    MaterialTheme {
        Column {
            TopAppBar(
                title = { Text("Recipe App") },
                backgroundColor = Color.Blue
            )
            RecipeList(recipes = sampleRecipes)
        }
    }
}

@Composable
fun RecipeList(recipes: List<Recipe>) {
    LazyColumn {
        items(recipes) { recipe ->
            RecipeCard(recipe = recipe)
        }
    }
}

@Composable
fun RecipeCard(recipe: Recipe) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
        ) {
            Text(text = recipe.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = "Type: ${recipe.mealType}")
            Text(text = "Serves: ${recipe.servingSize}")
            Text(text = "Difficulty: ${recipe.difficulty}")
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Ingredients:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            recipe.ingredients.forEach { ingredient ->
                Text(text = "- $ingredient")
            }
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Preparation Steps:", fontSize = 18.sp, fontWeight = FontWeight.Bold)
            recipe.preparationSteps.forEachIndexed { index, step ->
                Text(text = "${index + 1}. $step")
            }
        }
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Navigation()
        }
    }
}
