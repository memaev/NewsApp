package com.example.newsapp.presentation.screen.login

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.newsapp.R
import com.example.newsapp.presentation.navigation.Screen
import com.example.newsapp.presentation.ui.component.StyledButton
import com.example.newsapp.domain.util.Result

@Composable
fun LoginScreen(
    onNavigateTo: (Screen) -> Unit
) {
    val viewModel = hiltViewModel<LoginScreenViewModel>()
    val state by viewModel.state.collectAsStateWithLifecycle()

    val context = LocalContext.current
    LaunchedEffect(state.loginResult) {
        state.loginResult?.let { loginResult ->
            when(loginResult) {
                is Result.Success<*> -> {
                    onNavigateTo(Screen.Main)
                }
                is Result.Failure<*> -> {
                    Toast.makeText(context, loginResult.msg, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    LoginView(
        state = state,
        onNavigateTo = onNavigateTo,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun LoginView(
    onNavigateTo: (Screen) -> Unit = {},
    state: LoginScreenState = LoginScreenState(),
    onEvent: (LoginScreenEvent) -> Unit = {}
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
        Image(
            painter = painterResource(id = R.drawable.login_image_news_app),
            contentDescription = "News app login image",
            modifier = Modifier
                .size(160.dp)
                .padding(top = 20.dp)
        )

        OutlinedTextField(
            modifier = Modifier.padding(top = 180.dp),
            value = state.email,
            onValueChange = {
                onEvent(LoginScreenEvent.EmailUpdated(it))
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
                onEvent(LoginScreenEvent.PasswordUpdated(it))
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
            onClick = { onEvent(LoginScreenEvent.LoginBtnClicked) },
            modifier = Modifier.padding(top = 50.dp)
        ) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 19.sp
            )
        }
        Text(
            text = stringResource(id = R.string.no_account_register),
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {
                    onNavigateTo(Screen.Register)
                }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun LoginScreenPreview() {
    LoginView()
}