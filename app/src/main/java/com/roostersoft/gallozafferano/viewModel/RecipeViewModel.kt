package com.roostersoft.gallozafferano.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.roostersoft.gallozafferano.model.RecipeWithId
import com.roostersoft.gallozafferano.model.RecipeWithIdAndImage

class RecipeViewModel: ViewModel() {

    private val _recipes = MutableLiveData<List<RecipeWithIdAndImage>>()
    val recipes: LiveData<List<RecipeWithIdAndImage>>
        get() = _recipes
    init {
        _recipes.postValue(listOf(
            RecipeWithIdAndImage(RecipeWithId("0000", "Carbonara", "Pasta condita con tuorlo d'uovo e guanciale")),
            RecipeWithIdAndImage(RecipeWithId("0001", "Pasta alla gricia", "Pasta preparata con pasta, pecorino, pepe (ingredienti della pasta cacio e pepe), guanciale.")),
            RecipeWithIdAndImage(RecipeWithId("0002", "Risotto alla crema di scampi", "Risotto preparato con scampi freschi del mediterraneo e prezzemolino fresco")),
            RecipeWithIdAndImage(RecipeWithId("0003", "Pollo arrosto", "Pollo arrostito lentamente al forno, aggiungi rosmarino prima della cottura per quel tocco in più")),
            RecipeWithIdAndImage(RecipeWithId("0004", "Filetto al pepe verde", "Filetto di manzo insaporito al pepe verde con cremina gustosa")),
            RecipeWithIdAndImage(RecipeWithId("0005", "Margherita", "Pizza semplice: pomodoro e mozzarella. Aggiungete del basilico per quel tocco in più")),
            RecipeWithIdAndImage(RecipeWithId("0006", "Millefoglie", "Dolce con vari strati di pasta sfoglia farciti con crema")),
            RecipeWithIdAndImage(RecipeWithId("0007", "Torta Sacher", "Torta al cioccolato farcita con confettura di albicocche")),
            RecipeWithIdAndImage(RecipeWithId("0000", "Carbonara due", "come la carbonara ma molto più ricca, usate due uova")),
            ))
    }

}