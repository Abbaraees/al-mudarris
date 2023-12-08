package com.example.al_mudarris.presentation.view.components

import android.graphics.drawable.Drawable
import android.graphics.drawable.PaintDrawable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.al_mudarris.R
import com.example.al_mudarris.ui.theme.MyGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ActionMenuItem(
    name: String = "Students",
    paintDrawable: Int = R.drawable.school,
    onClick: () -> Unit = {},
    modifier: Modifier = Modifier
        .width(100.dp)
        .height(100.dp),
) {
    Card(
        onClick = { onClick() },
        colors = CardDefaults.cardColors(containerColor = MyGreen),
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(paintDrawable),
                contentDescription = name,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .width(80.dp)
                    .height(80.dp)
            )
            Text(
                text = name,
                textAlign = TextAlign.Center,
                color = Color.White,
                fontSize = 14.sp,
            )
        }
    }
}


@Preview
@Composable
fun ActionMenuItemPreview() {
    ActionMenuItem()
}