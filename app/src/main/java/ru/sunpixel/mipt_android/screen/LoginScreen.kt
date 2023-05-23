package ru.sunpixel.mipt_android.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import ru.sunpixel.mipt_android.R

@Composable
fun LoginView(loginViewModel: LoginViewModel, navController: NavController) {
    val viewState by loginViewModel.viewState.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {

        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(id = R.string.logo_description),
            modifier = Modifier
                .padding(top = 47.dp)
                .height(203.dp)
                .width(188.dp),
        )
        Text(
            text = stringResource(id = R.string.title),
            fontFamily = FontFamily(Font(R.font.viga)),
            fontSize = 40.sp,
            color = colorResource(id = R.color.light_green),
            fontWeight = FontWeight.W400
        )
        Text(
            text = stringResource(id = R.string.subtitle),
            fontSize = 13.sp,
            fontWeight = FontWeight.W600,
            color = Color(0xFF09051C),
            fontFamily = FontFamily(Font(R.font.inter))
        )
        Text(
            text = stringResource(id = R.string.login_to_your_account),
            modifier = Modifier.padding(top = 60.dp, bottom = 40.dp),
            fontWeight = FontWeight.W400,
            fontSize = 20.sp,
            color = Color(0xFF09051C),
            fontFamily = FontFamily(Font(R.font.benton_sans))
        )

        TextField(
            value = viewState.email,
            onValueChange = {
                    loginViewModel.obtainEvent(LoginEvent.EmailChanged(it), navController)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text( text = stringResource(R.string.email_placeholder)) },
            singleLine = true,
            modifier = Modifier
                .padding(bottom = 12.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                .height(57.dp)
                .width(325.dp)
        )

        TextField(
            value = viewState.password,
            onValueChange = {
                    loginViewModel.obtainEvent(LoginEvent.PasswordChanged(it), navController)
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.Gray,
                disabledTextColor = Color.Transparent,
                backgroundColor = Color.White,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            placeholder = { Text( text = stringResource(R.string.password_placeholder)) },
            singleLine = true,
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier
                .padding(bottom = 87.dp)
                .border(1.dp, Color.LightGray, RoundedCornerShape(15.dp, 15.dp, 15.dp, 15.dp))
                .height(57.dp)
                .width(325.dp)
        )

        TextButton(
            onClick = {loginViewModel.obtainEvent(LoginEvent.ForgotPasswordClicked, navController)},
            modifier = Modifier.padding(bottom = 20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.forgot_password_btn),
                color = colorResource(id = R.color.light_green),
                fontWeight = FontWeight.W400,
                fontSize = 12.sp,
                fontFamily = FontFamily(Font(R.font.benton_sans))
            )
        }

        Button(
            onClick = { loginViewModel.obtainEvent(LoginEvent.LoginClicked, navController) },
            shape = RoundedCornerShape(15.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = colorResource(id = R.color.light_green)),
            modifier = Modifier
                .height(57.dp)
                .width(141.dp)
        )
        {
            Text(
                text = stringResource(id = R.string.login_btn),
                color = Color.White,
                fontWeight = FontWeight.W400,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.benton_sans))
            )
        }

    }
}