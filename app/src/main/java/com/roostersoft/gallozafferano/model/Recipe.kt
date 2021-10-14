package com.roostersoft.gallozafferano.model

data class Recipe(val title: String, val body: String) {
    constructor(recipe: RecipeWithId) : this(recipe.title, recipe.body)
}

data class RecipeWithId(val _id: String, val title: String, val body: String)

data class RecipeWithIdAndImage(val imageUrl: String, val _id: String, val title: String, val body: String){
    constructor(recipe: RecipeWithId) : this(nextImg().first(), recipe._id, recipe.title, recipe.body)

    companion object {
        private fun nextImg() = sequence {
            val urls = listOf(
                "https://upload.wikimedia.org/wikipedia/commons/thumb/4/41/Foto.Linzertorte.JPG/800px-Foto.Linzertorte.JPG",
                "https://upload.wikimedia.org/wikipedia/commons/6/67/Italiano_sandwich_01.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/d/d9/Max%27s_Roasted_Chicken_-_Evan_Swigart.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/a3/Eq_it-na_pizza-margherita_sep2005_sml.jpg/800px-Eq_it-na_pizza-margherita_sep2005_sml.jpg",
                "https://upload.wikimedia.org/wikipedia/commons/thumb/a/ac/Pasta_Puttanesca.jpg/1024px-Pasta_Puttanesca.jpg"
            )

            var i = 0
            while (true) {
                yield(urls[i])
                i = (i + 1) % urls.size
            }
        }
    }
}