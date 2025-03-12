package com.ad.monngonmoingay.data.model

import com.google.firebase.firestore.DocumentId

data class MainIngredient(
   @DocumentId override val categoryId: String = "",
   override val name: String = "",
   override val img_url: String = ""
) : Category