package com.ad.monngonmoingay.data.model

object DataSource {
   val recipes = listOf(
      Recipe(
         title = "Italian Chicken Marinade",
         description = "This Italian dressing CHICKEN marinade is a super simple but delicious way to add flavor before grilling.",
         image_url = "https://www.allrecipes.com/thmb/vom5-lRLPCHBskR-k4wDau1GMqw=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/665982-italian-chicken-marinade-wannabe-chefette-4x3-1-ac0b567c5bd0457e96e6a15317d9ab57.jpg",
         prep_time = 15,
         cook_time = 15,
         additional_time = 360,
         servings = 4,
         instructions = listOf(
            "Whisk salad dressing, garlic powder, and salt together in a shallow baking dish; add CHICKEN breasts and turn to coat. Cover the dish with plastic wrap and marinate in the refrigerator, 4 hours to overnight.",
            "Preheat an outdoor grill for high heat and lightly oil the grate.",
            "Remove CHICKEN from marinade and shake off excess; discard remaining marinade.",
            "Cook CHICKEN on the preheated grill until no longer pink in the center and the juices run clear, about 7 to 8 minutes on each side. An instant-read thermometer inserted into the center should read at least 165 degrees F (74 degrees C)."
         ),
         main_ingredient_id = CHICKEN,
         origin_id = ITALIAN
      ),
      Recipe(
         title = "Deborah's Grilled Chicken",
         description = "Grilled chicken breasts topped with a savory tomato-basil topping and Parmesan. Great over angel hair pasta!",
         image_url = "https://www.allrecipes.com/thmb/a-PdqaNRBRijCBQ0pPN9cVEdomI=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/1009278-8c7490ce25d443c58d77d6731c9faf8e.jpg",
         prep_time = 30,
         cook_time = 15,
         additional_time = 45,
         servings = 6,
         instructions = listOf(
            "Preheat an outdoor grill for medium-high heat and lightly oil the grate.",
            ""
         ),
         main_ingredient_id = CHICKEN,
         origin_id = ITALIAN
      ),
      Recipe(
         title = "Japanese Chicken Wings",
         description = "These Japanese chicken wings are egged and fried in butter, then baked in a tangy sauce of soy sauce, water, sugar, vinegar, garlic powder, and salt. Delicious, sticky chicken wings!",
         image_url = "https://www.allrecipes.com/thmb/fQBAdzMIIGo7qcHz2wFVp1QgIe0=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8808338-japanese-chicken-wings-jen-ben-1x1-1-b820b7448691431ab3b95b9ee67a19f7.jpg",
         prep_time = 15,
         cook_time = 40,
         additional_time = 55,
         servings = 6,
         instructions = listOf(
            "Preheat the oven to 350 degrees F (175 degrees C).",
            "Place beaten egg in a small bowl. Place flour in a shallow bowl.",
            "Cut wings in half. Dip each piece in egg, then press in flour to coat.",
            "Melt butter in a large, deep skillet over medium-high heat. Fry coated wings in hot butter until deep brown on both sides. Place in a shallow roasting pan.",
            "Make sauce: Mix together sugar, vinegar, soy sauce, water, salt, and garlic powder in a medium bowl until combined. Pour over wings.",
            "Bake in the preheated oven for 30 to 45 minutes, basting wings frequently with sauce in the roasting pan. An instant-read thermometer inserted into the centers of wings near the bone should read 165 degrees F (74 degrees C)."
         ),
         main_ingredient_id = CHICKEN,
         origin_id = JAPAN
      )
   )
   val recipe = recipes[2]
}