package com.example.newsapp.presentation.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.newsapp.R
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.screen.state.LoginScreenEvent
import com.example.newsapp.presentation.screen.state.RegisterScreenEvent
import com.example.newsapp.presentation.screen.state.RegisterScreenState
import com.example.newsapp.presentation.screen.viewmodel.RegisterScreenViewModel
import com.example.newsapp.presentation.ui.component.StyledButton
import com.example.newsapp.util.Result

@Composable
fun RegisterScreen(
    onNavigateTo: (Screen) -> Unit = {}
) {
    val viewModel = hiltViewModel<RegisterScreenViewModel>()

    val context = LocalContext.current
    LaunchedEffect(viewModel.state.registerResult) {
        viewModel.state.registerResult?.let { registerResult ->
            when(registerResult) {
                is Result.Success<*> -> {
                    onNavigateTo(Screen.Main)
                }
                is Result.Failure<*> -> {
                    Toast.makeText(context, registerResult.msg, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    RegisterView(
        state = viewModel.state,
        onEvent = viewModel::onEvent,
        onNavigateTo = onNavigateTo
    )
}

@Composable
fun RegisterView(
    state: RegisterScreenState = RegisterScreenState(),
    onEvent: (RegisterScreenEvent) -> Unit = {},
    onNavigateTo: (Screen) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 30.sp,
            modifier = Modifier
                .padding(top = 100
                    .dp)
        )

        OutlinedTextField(
            modifier = Modifier.padding(top = 180.dp),
            value = state.username,
            onValueChange = {
                onEvent(RegisterScreenEvent.UsernameUpdated(it))
            },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Person),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_username))
            }
        )
        OutlinedTextField(
            modifier = Modifier.padding(top = 10.dp),
            value = state.email,
            onValueChange = {
                onEvent(RegisterScreenEvent.EmailUpdated(it))
            },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Email),
                    contentDescription = null
                )
            },
            placeholder = {
                Text(text = stringResource(id = R.string.enter_email))
            }
        )
        OutlinedTextField(
            value = state.password,
            onValueChange = {
                onEvent(RegisterScreenEvent.PasswordUpdated(it))
            },
            leadingIcon = {
                Icon(
                    painter = rememberVectorPainter(image = Icons.Outlined.Lock),
                    contentDescription = null
                )
            },
            modifier = Modifier.padding(top = 10.dp),
            visualTransformation = PasswordVisualTransformation(),
            placeholder = {
                Text(text = stringResource(id = R.string.enter_password))
            }
        )
        StyledButton(
            onClick = { onEvent(RegisterScreenEvent.RegisterBtnClicked) },
            modifier = Modifier.padding(top = 100.dp)
        ) {
            Text(
                text = stringResource(id = R.string.register),
                fontSize = 19.sp
            )
        }
        Text(
            text = stringResource(id = R.string.already_have_an_account),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    onNavigateTo(Screen.Login)
                }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {
    RegisterView()
}