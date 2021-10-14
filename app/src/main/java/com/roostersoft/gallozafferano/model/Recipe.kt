package com.roostersoft.gallozafferano.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(val title: String, val body: String) : Parcelable {
    constructor(recipe: RecipeWithId) : this(recipe.title, recipe.body)
}

@Parcelize
data class RecipeWithId(val _id: String, val title: String, val body: String) : Parcelable

@Parcelize
data class RecipeWithIdAndImage(val imageUrl: String, val _id: String, val title: String, val body: String) :
    Parcelable {
    constructor(recipe: RecipeWithId) : this(nextImg(), recipe._id, recipe.title, recipe.body)

    companion object {
        private var i = 0

        fun resetImagesCycle() {
            i = 0
        }

        fun nextImg(): String {
            val urls = listOf(
                "https://www.informacibo.it/wp-content/uploads/2018/04/carbonara.jpg",
                "http://cdn.cook.stbm.it/thumbnails/ricette/1/1259/hd750x421.jpg",
                "https://www.ricetteperbimby.it/foto-ricette/risotto-alla-crema-di-scampi-bimby.jpg",
                "https://primochef.it/wp-content/uploads/2019/03/SH_pollo_arrosto.jpg.webp",
                "https://www.giallozafferano.it/images/184-18454/Filetto-al-pepe-verde_650x433_wm.jpg",
                "https://www.unmondodisapori.it/wp-content/uploads/2017/10/margherita.jpg",
                "https://blog.giallozafferano.it/ricottaegrano/wp-content/uploads/2019/05/millefoglie-ok2.jpg-1-di-1.jpg",
                "https://cdn.robadadonne.it/wp-content/uploads/sites/4/2015/03/Sacher-1.jpg"
            )
            val url = urls[i]
            i = (i + 1) % urls.size
            return url
        }
    }
}
