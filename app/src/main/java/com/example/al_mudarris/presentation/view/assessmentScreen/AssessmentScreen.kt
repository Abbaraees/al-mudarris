package com.example.al_mudarris.presentation.view.assessmentScreen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ContentPasteSearch
import androidx.compose.material.icons.filled.PostAdd
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.al_mudarris.navigations.AssessmentDest
import com.example.al_mudarris.navigations.AssessmentDetailDest
import com.example.al_mudarris.presentation.view.assessmentScreen.screenEvents.AssessmentEvent
import com.example.al_mudarris.presentation.view.assessmentScreen.screenStates.AssessmentState
import com.example.al_mudarris.presentation.view.components.AddAssessmentDialog
import com.example.al_mudarris.presentation.view.components.MyTopAppBar
import com.example.al_mudarris.ui.theme.MyGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AssessmentScreen(
    navController: NavHostController,
    state: State<AssessmentState>,
    onEvent: (AssessmentEvent) -> Unit
) {
    val scrollBehaviour = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehaviour.nestedScrollConnection),
        topBar = {
            MyTopAppBar("Assessments", scrollBehaviour)
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { onEvent(AssessmentEvent.ShowAddAssessmentDialog) }) {
                Icon(imageVector = Icons.Default.PostAdd, contentDescription = "", tint = MyGreen)
            }
        }
    ) {paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (state.value.isAdding) {
                AddAssessmentDialog(state, onEvent)
            }
            Column(
                Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.End
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    leadingIcon = {
                        Icon(imageVector = Icons.Default.ContentPasteSearch, contentDescription = "search icon")
                    },
                    placeholder = {
                        Text(text = "Search")
                    },
                    modifier = Modifier
                        .fillMaxWidth(.7f)
                        .padding(8.dp)
                )

                if (state.value.assessments.isEmpty()) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            "No Assessment added yet!",
                            fontSize=20.sp,
                            color = MyGreen
                        )
                    }
                }
                else {
                    LazyColumn(
                        Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        items(state.value.assessments) {assessment ->
                            Text(
                                text = assessment.title,
                                color = MyGreen,
                                modifier = Modifier
                                    .padding(vertical = 8.dp)
                                    .fillMaxWidth()
                                    .clickable {
                                        navController.navigate(AssessmentDetailDest.route + "/${assessment.id}")
                                    }
                            )
                            Divider(thickness = 1.dp, color = Color.DarkGray)
                        }
                    }
                }
            }
        }
    }

}


@Preview(showSystemUi = true)
@Composable
fun StudentsScreenPreview() {
//    AssessmentScreen(navController, state, viewModel::onEvent)
}