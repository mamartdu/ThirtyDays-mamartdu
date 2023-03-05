package com.example.thirtydaysapp.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

/**
 * This class has as parameters the components to be used in this project.
 *
 * @param [days] It will use a string resource with the day number. This is from 1 to 30.
 * @param [titleDays] It will use a string resource with the title of the activity.
 * @param [imageDays] It will use an image resource that will be used as decoration.
 * @param [descriptionDays] It will use a string resource that describes the activity and lack of
 * creativity of the author of this practice project.
 */
data class DaysClass(
    @StringRes val days: Int,
    @StringRes val titleDays: Int,
    @DrawableRes val imageDays: Int,
    @StringRes val descriptionDays: Int
)