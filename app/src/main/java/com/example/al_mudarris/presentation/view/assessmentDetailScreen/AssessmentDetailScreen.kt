package com.example.al_mudarris.presentation.view.assessmentDetailScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.al_mudarris.navigations.AddAssessmentScoreDest
import com.example.al_mudarris.presentation.view.assessmentDetailScreen.screenEvents.AssessmentDetailEvent
import com.example.al_mudarris.presentation.view.assessmentDetailScreen.screenStates.AssessmentDetailState
import com.example.al_mudarris.presentation.view.components.MyTopAppBar
import com.example.al_mudarris.ui.theme.MyGreen
import com.example.al_mudarris.ui.theme.MyRed
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssessmentDetailScreen(
    modifier: Modifier = Modifier,
    state: State<AssessmentDetailState>,
    onEvent: KFunction1<AssessmentDetailEvent, Unit>,
    assessmentId: Int,
    navController: NavHostController
) {
    // Load the assessment detail
    onEvent(AssessmentDetailEvent.LoadAssessmentDetail(assessmentId))

    Scaffold(
        topBar = { MyTopAppBar(title = "Assessment")}
    ) {paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, MyGreen, RoundedCornerShape(5.dp))
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .padding(4.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Row(
                        Modifier
                            .fillMaxWidth(.75f),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(text = "Title:")
                        Text(
                            state.value.title,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(text = "Date Release:")
                        Text(
                            state.value.releaseDate,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(text = "Due Release:")
                        Text(
                            state.value.dueDate,
                            fontWeight = FontWeight.Bold
                        )
                    }

                    Row(
                        Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(text = "Average class score:")
                        Text(
                            "${state.value.averageScore}",
                            fontWeight = FontWeight.Bold
                        )
                    }


                    Column(
                        Modifier.padding(4.dp)
                    ) {
                        Text(text = "Description:")
                        Column(
                            Modifier
                                .border(1.dp, Color.LightGray, RoundedCornerShape(5.dp))
                                .height(80.dp)
                                .fillMaxWidth()
                                .padding(top = 4.dp)
                        ) {
                            Text(
                                state.value.description,
                                fontSize = 12.sp,
                                modifier = Modifier.padding(4.dp)
                            )
                        }

                    }

                    Row(
                        Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            onClick = {
                                navController.navigate(AddAssessmentScoreDest.route + "/${assessmentId}")
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MyGreen,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp)
                        ) {
                            Text(
                                text = "Add Scores",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                        Button(
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFFFB300),
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp)
                        ) {
                            Text(
                                text = "Edit",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }

                        Button(
                            onClick = {  },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MyRed,
                                contentColor = Color.White
                            ),
                            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
                            modifier = Modifier
                                .width(100.dp)
                                .padding(4.dp)

                        ) {
                            Text(
                                text = "Delete",
                                fontSize = 12.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }

            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    "Students",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyGreen,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Name",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 32.dp)
                    )
                    Text(
                        "Score",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                    )
                }

                if (state.value.studentScores.isEmpty()) {
                    Box(
                        modifier = Modifier.fillMaxWidth(),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            "No score Added Yet",
                            fontSize = 16.sp,
                            color = MyGreen
                        )
                    }
                }
                else {
                    LazyColumn {
                        items(state.value.studentScores) { score ->
                            Row(
                                Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    score.student.name,
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                                )
                                Text(
                                    "${score.scores.score}",
                                    modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
    
}

@Preview(showSystemUi = true)
@Composable
fun AssessmentDetailPreview() {
//    AssessmentDetailScreen(
//        state = viewModel.state,
//        onEvent = viewModel::onEvent,
//        assessmentId = assessmentId
//    )
}