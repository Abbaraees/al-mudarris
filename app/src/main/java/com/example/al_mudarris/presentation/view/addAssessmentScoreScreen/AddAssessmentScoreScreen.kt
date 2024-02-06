package com.example.al_mudarris.presentation.view.addAssessmentScoreScreen

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.al_mudarris.navigations.AddAssessmentScoreDest
import com.example.al_mudarris.navigations.AssessmentDetailDest
import com.example.al_mudarris.presentation.view.components.MyTopAppBar
import com.example.al_mudarris.ui.theme.MyGreen
import com.example.al_mudarris.ui.theme.MyRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddAssessmentScoreScreen(
    viewModel: AddAssessmentScoreViewModel,
    navController: NavHostController
) {
    val state = viewModel.state.collectAsState()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { MyTopAppBar(title = "Add Assessment Score") }
    ) {paddingValues ->
        Column(
            Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Text(
                text = "Score for: ${state.value.assessment.title}",
                color = MyGreen,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {

                // Header
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        "Student Name",
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        "Score",
                        fontWeight = FontWeight.SemiBold
                    )
                }

                // List of student name and score box
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    items(state.value.students) { student ->
                        Row(
                            Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = student.name,
                                modifier = Modifier
                                    .fillMaxWidth(0.87f)
                            )
                            ScoreTextField(
                                studentId = student.id,
                                viewModel = viewModel
                            )
                        }
                        Divider(
                            thickness = 1.dp,
                            color = MyGreen,
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                    item {
                        Button(
                            onClick = {
                                viewModel.onEvent(AddAssessmentScoreEvent.SaveScores)
                                navController.popBackStack()
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = MyRed)
                        ) {
                            Text(text = "Save")
                        }
                    }
                }
            }

        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScoreTextField(
    modifier: Modifier = Modifier,
    studentId: Int,
    viewModel: AddAssessmentScoreViewModel,
) {
    val interactionSource = remember {
        MutableInteractionSource()
    }
    val keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
    )
    var score by rememberSaveable {
        mutableStateOf(viewModel.getScore(studentId))
    }

    BasicTextField(
        value = score,
        onValueChange = {
            score = it
            viewModel.setScore(studentId, it)
        },
        textStyle = TextStyle.Default.copy(
            textAlign = TextAlign.Center
        ),
        keyboardOptions = keyboardOptions,
        interactionSource = interactionSource,
        modifier = modifier
            .fillMaxWidth()
            .height(40.dp)
            .border(1.dp, MyGreen, RoundedCornerShape(5.dp)),
    ) {innerTextField ->
        TextFieldDefaults.OutlinedTextFieldDecorationBox(
            value = score,
            innerTextField = { innerTextField() },
            enabled = true,
            singleLine = true,
            interactionSource = interactionSource,
            visualTransformation = VisualTransformation.None,
            contentPadding = PaddingValues(0.dp),
        )
    }
}


@Preview
@Composable
fun AddAssessmentScoreScreenPreview() {
//    AddAssessmentScoreScreen(assessmentId = 0)
}