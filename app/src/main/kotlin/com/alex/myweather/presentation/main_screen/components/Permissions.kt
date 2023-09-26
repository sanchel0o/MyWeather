package com.alex.myweather.presentation.main_screen.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.alex.myweather.R
import com.alex.myweather.core.ui_utils.preview.MyWeatherPreview
import com.alex.myweather.core.ui_utils.theme.LARGE_CORNER_RADIUS
import com.alex.myweather.core.ui_utils.theme.MEDIUM_CORNER_RADIUS
import com.alex.myweather.core.ui_utils.theme.MEDIUM_PADDING
import com.alex.myweather.core.ui_utils.theme.SMALL_PADDING
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.accompanist.permissions.shouldShowRationale

@RequiresApi(Build.VERSION_CODES.P)
@Composable
@MyWeatherPreview
private fun Preview() = MyWeatherPreview {
    RequestPermissionCard(
        permissionState = true,
        onClick = { }
    )
}


@RequiresApi(Build.VERSION_CODES.P)
@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermissions(
    content: @Composable () -> Unit
) {

    val foregroundPermissionState =
        rememberPermissionState(android.Manifest.permission.FOREGROUND_SERVICE)

    if (foregroundPermissionState.status.isGranted) {
        content()
    } else {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            RequestPermissionCard(
                permissionState = foregroundPermissionState.status.shouldShowRationale,
                onClick = { foregroundPermissionState.launchPermissionRequest() }
            )
        }
    }
}

@Composable
fun RequestPermissionCard(
    permissionState: Boolean,
    onClick: () -> Unit
) {
    val generalModifier = Modifier
        .padding(
            horizontal = MEDIUM_PADDING.dp,
            vertical = SMALL_PADDING.dp
        )
        .fillMaxWidth()

    Card(
        modifier = Modifier
            .padding(MEDIUM_PADDING.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(MEDIUM_CORNER_RADIUS.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val textToShow = if (permissionState) {
                stringResource(id = R.string.foreground_service_explanation)
            } else {
                stringResource(id = R.string.service_not_avalible)
            }

            Card(
                modifier = generalModifier,
                shape = RoundedCornerShape(LARGE_CORNER_RADIUS.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.error)
            ) {

                Text(
                    modifier = generalModifier,
                    textAlign = TextAlign.Justify,
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onError,
                    text = textToShow
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = MEDIUM_PADDING.dp,
                        end = MEDIUM_PADDING.dp,
                        bottom = SMALL_PADDING.dp
                    ),
                onClick = { onClick() }
            ) {
                Text(
                    style = MaterialTheme.typography.labelSmall,
                    text = stringResource(id = R.string.give_permission)
                )
            }
        }

    }

}