package com.example.al_mudarris.presentation.view.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.al_mudarris.ui.theme.MyGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssessmentCard() {
    Card(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth()
            .height(90.dp),
        border = BorderStroke(1.dp, MyGreen)
    ) {
        Column(
            modifier = Modifier.fillMaxHeight()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Name:")
                    Text(text = "Assessment 1", fontWeight = FontWeight.Bold)
                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ){
                    Text(text = "Release Date:")
                    Text(text = "11/12/2023", fontWeight = FontWeight.Bold)
                }
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Due Date:")
                    Text(text = "11/12/2023", fontWeight = FontWeight.Bold)
                }

            }
        }
    }
}

@Preview
@Composable
fun AssessmentCardPreview() {
    AssessmentCard()
}