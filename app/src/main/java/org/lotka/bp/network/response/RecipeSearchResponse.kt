package org.lotka.bp.network.response

import com.google.gson.annotations.SerializedName
import org.lotka.bp.network.model.RecipeDto

data class RecipeSearchResponse(

    @SerializedName("count")
    var count: Int,

    @SerializedName("results")
    var recipes: List<RecipeDto>,
)