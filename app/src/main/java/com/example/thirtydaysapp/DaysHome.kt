package com.example.thirtydaysapp

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thirtydaysapp.data.DaysFakeData
import com.example.thirtydaysapp.model.DaysClass
import com.example.thirtydaysapp.ui.theme.ThirtyDaysAppTheme

/**
 * Entry point.
 */
@Composable
fun MainScreen() {
    ThirtyDaysAppTheme {
        DaysHome(lastDays = DaysFakeData.onlyDays)
    }
}

/**
 * Composable that displays all the content in the screen.
 * A LazyColumn will be used to display the entire item view on the screen.
 */
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DaysHome(
    lastDays: List<DaysClass>,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            DaysTopBar()
        }
    ) {
        LazyColumn(
            modifier = modifier.background(
                MaterialTheme.colors.background
            )
        ) {
            items(lastDays) { days ->
                DaysCard(daysInformation = days)
            }
        }
    }
}

/**
 * Composable that shows a Card component, it will contain the following:
 *
 * - Title with the day and its number.
 * - Subtitle with the name of the tasks.
 * - An expansion button.
 * - An illustrative image.
 * - A text describing the task.
 *
 * @param [daysInformation] Data class call.
 * @param [modifier] to beautify this function.
 */
@Composable
fun DaysCard(
    daysInformation: DaysClass,
    modifier: Modifier = Modifier
) {
    // Animation "expanded"
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = modifier
            .padding(8.dp),
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 6.dp
    ) {
        Column(
            modifier = Modifier
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                DaysAndTitle(
                    numberText = daysInformation.days,
                    titleText = daysInformation.titleDays
                )
                Spacer(modifier = Modifier.weight(1f))
                ExpandedButton(
                    onClick = {
                        expanded = !expanded },
                    expanded = expanded
                )
            }
            if (expanded) {
                DaysImage(
                    illustrativeImage = daysInformation.imageDays
                )
                DescriptionDays(
                    descriptionText = daysInformation.descriptionDays
                )
            }
        }
    }
}

/**
 * Composable that shows the day and the title of the letter.
 * It is one of the components to use in the composable card.
 *
 * @param [numberText] will display the day and its number using an assigned string resource.
 * @param [titleText] will display the title using an assigned string resource.
 * @param [modifier] to beauty this function
 */
@Composable
fun DaysAndTitle(
    @StringRes numberText: Int,
    @StringRes titleText: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(
                top = 10.dp,
                start = 10.dp,
                bottom = 10.dp
            )
    ) {
        Text(
            text = stringResource(id = numberText),
            style = MaterialTheme.typography.h2,
            //color = MaterialTheme.colors.onPrimary,
            modifier = modifier.padding(top = 8.dp)
        )
        Spacer(
            modifier = Modifier
                .height(20.dp)
        )
        Text(
            text = stringResource(id = titleText),
            //color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h3
        )
    }
}

/**
 * Composable showing an illustrative image.
 * It is one of the components to use in the composable card.
 *
 * @param [illustrativeImage] will display an illustrative image using the assigned drawable resource.
 * @param [modifier] to beauty this function.
 */
@Composable
fun DaysImage(
    @DrawableRes illustrativeImage: Int,
    modifier: Modifier = Modifier
) {
    Image(
        painter = painterResource(id = illustrativeImage),
        // Image description is null as it is for illustrative purposes.
        contentDescription = null,
        modifier = modifier
            .fillMaxWidth()
            .aspectRatio(16f / 9f)
    )
}

/**
 * Composable showing card description.
 * It is one of the components to use in the composable card.
 *
 * @param [descriptionText] will display the description using an assigned string resource.
 * @param [modifier] to beauty this function.
 */
@Composable
fun DescriptionDays(
    @StringRes descriptionText: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                top = 20.dp,
                start = 10.dp,
                end = 10.dp,
                bottom = 20.dp
            )
    ) {
        Text(
            text = stringResource(id = descriptionText),
            style = MaterialTheme.typography.body1,
            //color = MaterialTheme.colors.onPrimary,
            textAlign = TextAlign.Justify
        )
    }
}

/**
 * Composable that show the Top Bar.
 *
 * @param [modifier] to beauty this function
 */
@Composable
fun DaysTopBar(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = stringResource(id = R.string.top_bar),
            //color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h1,
            modifier = modifier
                .padding(
                    start = 10.dp,
                    bottom = 10.dp,
                    top = 10.dp,

                )
        )
    }
}

/**
 * Composable showing the expand button.
 * Every time the user clicks on it,
 * the card will expand and show the image along with the description.
 *
 * @param [onClick] button click event. Will fire if expanded is true or false.
 * @param [expanded] It serves as a parameter for the [onClick]. This will be true or false
 * @param [modifier] to beauty this function.
 */
@Composable
fun ExpandedButton(
    onClick: () -> Unit,
    expanded: Boolean,
    modifier: Modifier = Modifier
) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            tint = MaterialTheme.colors.onPrimary,
            contentDescription = null
        )
    }
}

@Preview
@Composable
fun AppPreview() {
    ThirtyDaysAppTheme() {
        MainScreen()
    }
}

@Preview
@Composable
fun AppPreviewDark() {
    ThirtyDaysAppTheme(darkTheme = true) {
        MainScreen()
    }
}