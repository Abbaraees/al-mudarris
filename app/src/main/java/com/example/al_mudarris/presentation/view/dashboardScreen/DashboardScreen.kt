package com.example.al_mudarris.presentation.view.dashboardScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.al_mudarris.R
import com.example.al_mudarris.navigations.AssessmentDest
import com.example.al_mudarris.navigations.Students
import com.example.al_mudarris.presentation.view.components.ActionMenuItem
import com.example.al_mudarris.presentation.view.components.AssessmentCard
import com.example.al_mudarris.presentation.view.components.MyTopAppBar
import com.example.al_mudarris.ui.theme.MyGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(navController: NavHostController) {
    val studentStats =listOf(
        "Total Students:" to "20",
        "Present Today:" to "19",
        "Absent Today:" to "1"
    )
    Scaffold(
        topBar = {
            MyTopAppBar(title = "Al Mudarris")
        },

    ) { paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Welcome, Muhammad Lawal",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = MyGreen
            )

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth()
            ) {
                ActionMenuItem(
                    name = "Students",
                    paintDrawable =
                    R.drawable.school
                ) {
                    navController.navigate(Students.route)
                }
                ActionMenuItem(name = "Attendance", paintDrawable = R.drawable.attendance_icon)
                ActionMenuItem(name = "Assessment", paintDrawable = R.drawable.assessment_icon) {
                    navController.navigate(AssessmentDest.route)
                }
            }
            Divider(
                color = MyGreen,
                modifier = Modifier.padding(vertical = 16.dp)
            )

//            Start of Students statistics
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Students",
                    color = MyGreen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                studentStats.forEach{
                    Row(
                        Modifier
                            .fillMaxWidth(0.5f)
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = it.first)
                        Text(text = it.second, fontWeight = FontWeight.Bold)
                    }
                }

            }
//            End of students statistics
            Divider(
                color = MyGreen,
                modifier = Modifier.padding(vertical = 16.dp)
            )
            Column(
                Modifier.fillMaxWidth(),

            ) {
                Text(
                    text = "Assessments",
                    color = MyGreen,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )
                Text(
                    text = "Upcoming Assessments: 0",
                    fontSize = 14.sp,
                    modifier = Modifier
                        .padding(vertical = 8.dp)
                )
                Text(
                    text = "Recent Assessments",
                    color = MyGreen,
                    fontSize = 16.sp
                )
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    AssessmentCard()
                    Spacer(modifier = Modifier.padding(vertical = 4.dp))
                    AssessmentCard()
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(navController = rememberNavController())
}