package com.mahesaiqbal.moviescompose.ui.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.mahesaiqbal.moviescompose.R

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    navigateBack: () -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        val context = LocalContext.current

        Box {
            OutlinedButton(
                onClick = { navigateBack() },
                shape = CircleShape,
                contentPadding = PaddingValues(0.dp),
                colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Black),
                modifier = Modifier
                    .padding(16.dp)
                    .size(50.dp)
                    .semantics(mergeDescendants = true) {
                        contentDescription = context.resources.getString(R.string.button_back)
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(16.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.photo),
                contentDescription = stringResource(R.string.profile_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(300.dp)
                    .padding(16.dp)
                    .clip(CircleShape)
            )
            Text(
                text = stringResource(R.string.profile_name),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                ),
                modifier = Modifier.padding(8.dp)
            )
            Text(
                text = stringResource(R.string.profile_email),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.h5.copy(
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}
