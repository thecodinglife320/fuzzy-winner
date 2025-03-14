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
         origin_id = ITALIAN,
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
      ),
      Recipe(
         title = "Air Fryer Lemon Garlic Parmesan Chicken",
         description = "These simple lemon garlic Parmesan chicken thighs are cooked in the air fryer. Seasoned with lemon zest, garlic, and oregano and coated in Parmesan cheese they come out juicy and tender.",
         image_url = "https://www.allrecipes.com/thmb/uoijC4F_0Nl2Kum0PU8TkbtLqDg=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8726749-Air-Fryer-Lemon-Garlic-Parmesan-Chicken-ddmfs-5770-4x3-beauty-5c66fac06d8040abac905e15b2b9c44a.jpg",
         prep_time = 20,
         cook_time = 12,
         additional_time = 0,
         total_time = 32,
         servings = 4,
         instructions = listOf(
            "Gather all ingredients.",
            "Preheat an air fryer to 400 degrees F (200 degrees C).",
            "Place chicken in a large bowl. Add garlic, lemon zest, paprika, oregano, salt, and crushed red pepper; toss to coat chicken in the seasonings.",
            "Combine Parmesan cheese and panko in a shallow dish. Dredge chicken in Parmesan mixture to coat. Sprinkle any remaining mixture over chicken; pat to adhere.",
            "Coat the chicken generously with cooking spray.",
            "Lightly coat air fryer basket with cooking spray. Place chicken in prepared basket (cook in batches, if needed). Air fry 12 minutes or until an instant-read thermometer inserted in the center reads 170 degrees F (77 degrees C).",
            "Serve immediately."
         ),
         images_per_steps = listOf(
            "https://www.allrecipes.com/thmb/5NC2lKLNVJ0Kvqq9MO_R03PBubc=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8726749-Air-Fryer-Lemon-Garlic-Parmesan-Chicken-ddmfs-5701-4x3-step01-3ca7fac69b964c339eabd57439b73c50.jpg",
            "",
            "https://www.allrecipes.com/thmb/uMcjpizU19NuW2UTjxy1F7ekle8=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8726749-Air-Fryer-Lemon-Garlic-Parmesan-Chicken-ddmfs-5702-4x3-step02-88c7b5ff37454891a383577baf2f5a86.jpg",
            "https://www.allrecipes.com/thmb/FfhWZc8TFizGuGkbV291QSii400=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8726749-Air-Fryer-Lemon-Garlic-Parmesan-Chicken-ddmfs-5703-4x3-step03-17caff8935254a7ab326a21a3e09e95d.jpg",
            "https://www.allrecipes.com/thmb/BMsl0ugh_7Y9wtzb7TkkpJh0f4s=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8726749-Air-Fryer-Lemon-Garlic-Parmesan-Chicken-ddmfs-5704-4x3-step04-865a0a34fd5a4acabd925fca9d39cb03.jpg",
            "https://www.allrecipes.com/thmb/7N-Fs7kgYvwkhBLVCSc6z9FFWvs=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8726749-Air-Fryer-Lemon-Garlic-Parmesan-Chicken-ddmfs-5709-4x3-step05-f4046efc7cd44f689a93ffce5a495582.jpg",
            "https://www.allrecipes.com/thmb/L0nszofuTN57Gh6Z21UWBO7Lp3I=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/8726749-Air-Fryer-Lemon-Garlic-Parmesan-Chicken-ddmfs-5712-4x3-step06-ce6bfb9d6c9443f8bf61537d6d76b8ed.jpg"

         ),
         main_ingredient_id = CHICKEN,
         origin_id = ""
      ),
      Recipe(
         title = "Spaghetti all'Assassina (Assassin's Spaghetti)",
         description = "Caramelized tomato sauce makes this a wonderfully savory dish. The reduction of the tomato broth brings out the sweetness of the tomatoes which balances well with the spiciness of the chili flakes.",
         image_url = "https://www.allrecipes.com/thmb/Nm42X1WNio7YAXQVD_zEwPO8m1A=/0x512/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-hero-4x3-b-f3617177055043949f46673b7d44c701.jpg",
         prep_time = 5,
         cook_time = 25,
         additional_time = 0,
         total_time = 30,
         servings = 4,
         instructions = listOf(
            "Gather all ingredients.",
            "Heat olive oil in a pot over medium heat. Add garlic and sizzle until it starts to turn golden and fragrant, about 1 minute. Stir in tomato puree, water, and salt. Bring to a simmer on medium-high. Once simmering, reduce to low heat and keep warm.",
            "Pour olive oil into a large non-stick skillet over medium-high heat, season with chili flakes and heat until chili flakes start to sizzle, for 1 minute. Add raw spaghetti and toss until well coated with chili oil.",
            "Pour in about 3 cups of tomato broth and, using tongs, move pasta from side to side to evenly distribute tomato broth. Cook, occasionally moving pasta from side to side with tongs, all facing the same direction, until most of the broth has been absorbed or evaporated, and the spaghetti starts frying in the pan.",
            "Turn spaghetti over with tongs, and evenly arrange in the pan. Keep cooking until the pasta starts to brown, and even lightly chars.",
            "Add about 2 more cups of tomato broth, and repeat the process. Continue cooking until the spaghetti is as charred as you like , and cooked to your desired doneness. More broth can be added at the end of the process for a saucier version, as shown in the video, or it can be served relatively dry.",
            "Serve with a drizzle of olive oil and more chili flakes if desired.",
            "Enjoy!"
         ),
         images_per_steps = listOf(
            "https://www.allrecipes.com/thmb/daDajlAz_Rt5u9NVvGxnuLz2CDI=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-4x3-steps-01-e010c1804798486d839361fb1db94be7.jpg",
            "https://www.allrecipes.com/thmb/sSzHBx6rNl2tXoTqAcdyzODZpVI=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-4x3-steps-02-da8304e6464044d69ff9202e48e138a6.jpg",
            "https://www.allrecipes.com/thmb/kSVuFCReVkf1-bNXqEi56BN5fYw=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-4x3-steps-03-ddca28e681164ffca7aa758965e7f157.jpg",
            "https://www.allrecipes.com/thmb/yTeK2rOh4n73rIBTODw3UZGM1x0=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-4x3-steps-04-8e73ca43abeb4cc6bbef44555a970850.jpg",
            "https://www.allrecipes.com/thmb/Qnt41zQ7BTpVDcoNm_9EkWDfujg=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-4x3-steps-05-088d5d0f99fb43d1992451aa3afdbecd.jpg",
            "https://www.allrecipes.com/thmb/pAkb6YUT_BiJ2bIw9az3fLDIucQ=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-4x3-steps-06-9c1fb0930cfd464591034418f0017e59.jpg",
            "https://www.allrecipes.com/thmb/6fxHlnFSvU0UFReQN-7zSqw01us=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-4x3-steps-07-1390707cbaa846db9c14e112916da503.jpg",
            "https://www.allrecipes.com/thmb/d7abApqc-ESWQ2WYOm8p07kune4=/750x0/filters:no_upscale():max_bytes(150000):strip_icc():format(webp)/ALR-8534000-spaghetti-allassassina-assassins-spaghetti-VAT-hero-4x3-b-f3617177055043949f46673b7d44c701.jpg"
         ),
         main_ingredient_id = PASTA,
         origin_id = ITALIAN
      )
   )
   val recipe = recipes[4]
}