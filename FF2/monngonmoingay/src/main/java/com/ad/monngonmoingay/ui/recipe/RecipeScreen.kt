package com.ad.monngonmoingay.ui.recipe

import android.graphics.drawable.BitmapDrawable
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.ArrowBackIos
import androidx.compose.material.icons.automirrored.outlined.ArrowForwardIos
import androidx.compose.material3.Card
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.palette.graphics.Palette
import coil.ImageLoader
import coil.request.ImageRequest
import coil.request.SuccessResult
import com.ad.monngonmoingay.R
import com.ad.monngonmoingay.data.model.RecipeDetails
import com.ad.monngonmoingay.ui.shared.CoilImage
import com.ad.monngonmoingay.ui.theme.AppTheme

@Composable
fun RecipeScreen(
   viewModel: RecipeViewModel
) {
   val recipeDetails by viewModel.recipeDetails.collectAsStateWithLifecycle(RecipeDetails())
   RecipeScreenContent(
      viewModel = viewModel,
      recipe = recipeDetails,
      modifier = Modifier
         .padding(dimensionResource(R.dimen.padding_small))
         .fillMaxWidth()
   )
}

@Composable
fun RecipeScreenContent(
   viewModel: RecipeViewModel,
   recipe: RecipeDetails,
   modifier: Modifier = Modifier
) {
   val stepIndex by viewModel.stepIndex.collectAsStateWithLifecycle()
   val scrollState = rememberScrollState()
   Column(modifier = modifier.verticalScroll(scrollState)) {

      DynamicBorderImage(
         imageUrl = recipe.imageUrl,
         modifier = modifier.height(250.dp)
      )
      RecipeInfoCard(
         modifier = modifier,
         prepTime = recipe.prepTime,
         cookTime = recipe.cookTime,
         additionalTime = recipe.additionalTime,
         totalTime = recipe.totalTime,
         servings = recipe.servings
      )

      //label
      Text(
         stringResource(R.string.instructions),
         Modifier.padding(
            vertical = dimensionResource(R.dimen.padding_medium),
            horizontal = dimensionResource(R.dimen.padding_small)
         )
      )

      InstructionRow(
         steps = recipe.instructions,
         modifier = modifier,
         onNext = viewModel::onNextStep,
         onPrevious = viewModel::onPreviousStep,
         stepIndex = stepIndex,
         imagePerStep = recipe.imagesPerSteps
      )
   }
}

@Composable
fun RecipeInfoColumn(
   @StringRes title: Int,
   info: String,
   modifier: Modifier = Modifier
) {
   Column(modifier) {
      Text(
         text = stringResource(id = title),
         style = MaterialTheme.typography.titleMedium
      )
      Text(text = info, style = MaterialTheme.typography.bodyMedium)
   }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RecipeInfoCard(
   modifier: Modifier = Modifier,
   prepTime: String = "10 minute",
   cookTime: String = "10 minute",
   additionalTime: String = "10 minute",
   totalTime: String = "10 minute",
   servings: String = "10 people",
) {
   val recipeInfoMap = mapOf(
      R.string.prep_time to prepTime,
      R.string.cook_time to cookTime,
      R.string.additional_time to additionalTime,
      R.string.total_time to totalTime,
      R.string.servings to servings

   )
   Card(modifier) {
      Column {
         HorizontalDivider(thickness = dimensionResource(R.dimen.padding_small))
         FlowRow(
            modifier = modifier,
            maxItemsInEachRow = 3,
            horizontalArrangement = Arrangement.SpaceAround,
         ) {
            recipeInfoMap.forEach { (title, info) ->
               RecipeInfoColumn(
                  title = title,
                  info = info,
                  modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_medium))
               )
            }
         }
      }
   }
}

@Composable
fun DynamicBorderImage(
   imageUrl: String,
   modifier: Modifier = Modifier
) {
   val context = LocalContext.current
   var dominantColor by remember { mutableStateOf(Color.Transparent) }

   // Load bitmap từ URL (hoặc có thể dùng ImageLoader của Coil)
   val imageLoader = ImageLoader(context)
   val request = ImageRequest.Builder(context)
      .data(imageUrl)
      .allowHardware(false) // Bắt buộc để Palette hoạt động
      .build()

   LaunchedEffect(imageUrl) {
      val result = (imageLoader.execute(request) as? SuccessResult)?.drawable
      val bitmap = (result as? BitmapDrawable)?.bitmap

      bitmap?.let {
         Palette.from(it).generate { palette ->
            dominantColor = palette?.dominantSwatch?.rgb?.let { Color(it) } ?: Color.Gray
         }
      }
   }

   CoilImage(
      imageUrl = imageUrl,
      modifier = modifier
         .border(
            width = dimensionResource(R.dimen.border_size),
            color = dominantColor,
         )
   )
}

@Composable
fun InstructionRow(
   steps: List<String>,
   imagePerStep: List<String>,
   modifier: Modifier = Modifier,
   onNext: () -> Unit = {},
   onPrevious: () -> Unit = {},
   stepIndex: Int = 0,
) {
   if (steps.isNotEmpty()) {


      Column(
         modifier = modifier
      ) {

         //button and image
         Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
         ) {

            //previous button
            FilledIconButton(
               onClick = onPrevious,
               enabled = stepIndex != 0,
               modifier = Modifier.weight(0.3f)
            ) {
               Icon(
                  imageVector = Icons.AutoMirrored.Outlined.ArrowBackIos,
                  contentDescription = null
               )
            }

            Column(
               Modifier
                  .weight(2.4f)
            ) {
               //step number
               Text(stringResource(R.string.step, stepIndex + 1))

               //image per step
               CoilImage(
                  imagePerStep[stepIndex],
                  modifier = Modifier
                     .height(200.dp)

               )
            }

            //next button
            FilledIconButton(
               onClick = onNext,
               enabled = stepIndex != steps.size - 1,
               modifier = Modifier.weight(0.3f)
            ) {
               Icon(
                  imageVector = Icons.AutoMirrored.Outlined.ArrowForwardIos,
                  contentDescription = null
               )
            }
         }

         //instruction text
         Text(
            text = steps[stepIndex],
            modifier = Modifier
               .animateContentSize()
               .fillMaxWidth(),
            textAlign = TextAlign.Center
         )
      }
   }
}


@Preview(device = "id:4.7in WXGA", showSystemUi = true)
@Composable
fun RecipeScreenContentPreview() {
   AppTheme {
//      RecipeScreenContent(
//         recipe = RecipeDetails(
//            imageUrl = "https://images.pexels.com/photos/1256875/pexels-photo-1256875.jpeg?auto=compress&cs=tinysrgb&w=600",
//            prepTime = "10 minute",
//            cookTime = "10 minute",
//            additionalTime = "10 minute",
//            servings = "10 people",
//            totalTime = "10 minute",
//         ),
//         modifier = Modifier
//            .padding(dimensionResource(R.dimen.padding_small))
//            .fillMaxWidth()
//      )
      InstructionRow(
         listOf(
            "abc",
            "def",
            "ghi"
         ),
         modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_small))
            .fillMaxWidth(),
         onNext = {},
         onPrevious = {},
         stepIndex = 0,
         imagePerStep = listOf("", "", "")
      )
   }
}


