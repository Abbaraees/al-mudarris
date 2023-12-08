package com.example.al_mudarris.presentation.view.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputField(
    isPasswordField: Boolean = false,
    label: String,
    placeholder: String,
    leadingIcon: ImageVector,
    value: String,
    onValueChange: (String) -> Unit

) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = placeholder) },
        leadingIcon = {
            Icon(imageVector = leadingIcon, contentDescription = "")
        },
//        colors = TextFieldDefaults.outlinedTextFieldColors(
//            containerColor = Color(0xFFFEFFFE),
//            textColor = MyGreen,
//            focusedBorderColor = MyGreen,
//            unfocusedBorderColor = MyGreen,
//            unfocusedLeadingIconColor = MyGreen,
//            placeholderColor = MyGreen
//        ),
        modifier = Modifier.padding(vertical = 10.dp),
        visualTransformation = if (isPasswordField) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        }


    )
}