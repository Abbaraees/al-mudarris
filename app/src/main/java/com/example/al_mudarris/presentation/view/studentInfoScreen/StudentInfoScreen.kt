package com.example.al_mudarris.presentation.view.studentInfoScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PersonRemove
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.al_mudarris.presentation.view.components.DeleteStudentDialog
import com.example.al_mudarris.presentation.view.components.MyTopAppBar
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenEvents.StudentInfoEvent
import com.example.al_mudarris.presentation.view.studentInfoScreen.screenStates.StudentInfoState
import com.example.al_mudarris.ui.theme.MyGreen
import com.example.al_mudarris.ui.theme.MyRed
import kotlin.reflect.KFunction1

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentInfoScreen(
    studentId: Int,
    navController: NavHostController,
    state: State<StudentInfoState>,
    onEvent: KFunction1<StudentInfoEvent, Unit>
) {
    onEvent(StudentInfoEvent.LoadStudentInfo(studentId))

    Scaffold(
        topBar = { MyTopAppBar(title = "Student Info")}
    ) {paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.value.showDeleteDialog) {
                DeleteStudentDialog(navController, state = state, onEvent,)
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .border(1.dp, MyGreen, RoundedCornerShape(5.dp))
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                ) {
                    Column(
                        Modifier
                            .fillMaxWidth(.5f)
                            .fillMaxHeight()
                            .padding(4.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(text = "Name:")
                            Text(
                                state.value.name,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(text = "Gender:")
                            Text(
                                state.value.gender,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(text = "DOB")
                            Text(
                                state.value.dob,
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }

                    Column(
                        Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(4.dp),
                        verticalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(text = "Presents:")
                            Text(
                                "${state.value.presents}",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(text = "Absents:")
                            Text(
                                "${state.value.absents}",
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Row(
                            Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(text = "Average Score")
                            Text(
                                "${state.value.averageScores}",
                                fontWeight = FontWeight.Bold
                            )
                        }
                    }
                }

                Column(
                    Modifier.padding(4.dp)
                ) {
                    Text(text = "Comment:")
                    Column(
                        Modifier
                            .border(1.dp, Color.LightGray, RoundedCornerShape(5.dp))
                            .height(50.dp)
                            .fillMaxWidth()
                            .padding(top = 4.dp)
                    ) {
                        Text(
                            "${state.value.comment}",
                            fontSize = 12.sp,
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                }

                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = { /*TODO*/ },
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MyGreen,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .size(50.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(25.dp))
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Edit,
                            contentDescription = "Update Student Info",
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                        )
                    }
                    IconButton(
                        onClick = { onEvent(StudentInfoEvent.ShowDeleteDialog) },
                        colors = IconButtonDefaults.filledIconButtonColors(
                            containerColor = MyRed,
                            contentColor = Color.White
                        ),
                        modifier = Modifier
                            .size(50.dp)
                            .padding(8.dp)
                            .clip(RoundedCornerShape(25.dp))
                        
                    ) {
                        Icon(
                            imageVector = Icons.Filled.PersonRemove,
                            contentDescription = "Delete Student",
                            modifier = Modifier
                                .clip(RoundedCornerShape(25.dp))
                        )

                    }
                }
            }
            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    "Assessments",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                    color = MyGreen,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth()
                )

                LazyColumn {
                   item {
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
                    }

                    items(30) {
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                "Assessment $it",
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 8.dp)
                            )
                            Text(
                                "20",
                                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun StudentInfoPreview() {
//    StudentInfoScreen(studentId = 1, state = state, loadStudent = viewModel::loadStudent)
}