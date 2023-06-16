package com.example.android_calculator_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.FocusRequester.Companion.createRefs
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.android_calculator_compose.ui.theme.AndroidCalculatorComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidCalculatorComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Calculator()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun Calculator() {
    var display by remember { mutableStateOf(TextFieldValue("0")) }
    var firstOperator by remember { mutableStateOf(0.0) }
    var secondOperator by remember { mutableStateOf(0.0) }
    var result by remember { mutableStateOf(0.0) }
    var formattedResult by remember { mutableStateOf("") }

    ConstraintLayout (
        modifier = Modifier.fillMaxSize()
    ) {
        // Texts
        val textFieldDisplay = createRef()
        val textTitle = createRef()

        // Functionality Buttons
        val buttonBack = createRef()
        val buttonC = createRef()
        val buttonParenthesis = createRef()
        val buttonPercentage = createRef()
        val buttonDivision = createRef()
        val buttonMultiplication = createRef()
        val buttonSum = createRef()
        val buttonSubtract = createRef()
        val buttonDot = createRef()
        val buttonEqual = createRef()

        // Number Buttons
        val button1 = createRef()
        val button2 = createRef()
        val button3 = createRef()
        val button4 = createRef()
        val button5 = createRef()
        val button6 = createRef()
        val button7 = createRef()
        val button8 = createRef()
        val button9 = createRef()
        val button0 = createRef()
        val button000 = createRef()

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textTitle) {
                    top.linkTo(parent.top)
                },
            text = "Calculator",
            textAlign = TextAlign.Center,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = colorResource(R.color.dark_blue)
        )

        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(textFieldDisplay) {
                    top.linkTo(textTitle.bottom)
                    bottom.linkTo(buttonBack.top)
                },
            value = display,
            onValueChange = {
                display = it

            },
            textStyle = TextStyle(
                textAlign = TextAlign.End,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.dark_blue)
            ),
            readOnly = true,
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = colorResource(R.color.light_blue)
            )
        )

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonBack) {
                    top.linkTo(textTitle.bottom, margin = 260.dp)
                    end.linkTo(parent.end, margin = 10.dp)
                },
            onClick =  {
                if (display.text.isNotEmpty()) {
                    display = TextFieldValue(display.text.substring(0, display.text.length - 1))

                    if (display.text.isEmpty()) {
                        display = TextFieldValue("0")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.red)
            )
        ) {
            Text(
                text = "<-",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonC) {
                    start.linkTo(parent.start, margin = 10.dp)
                    top.linkTo(buttonBack.bottom, margin = 10.dp)
                },
            onClick =  {
                if (display.text != "0") {
                    display = TextFieldValue("0")
                    firstOperator = 0.0
                    secondOperator = 0.0
                    formattedResult = ""
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.red)
            )
        ) {
            Text(
                text = "C",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonParenthesis) {
                    start.linkTo(buttonC.end, margin = 10.dp)
                    top.linkTo(buttonC.top)
                    bottom.linkTo(buttonC.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue("(" + display.text + ")")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.light_blue)
            )
        ) {
            Text(
                text = "()",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonPercentage) {
                    start.linkTo(buttonParenthesis.end, margin = 10.dp)
                    top.linkTo(buttonC.top)
                    bottom.linkTo(buttonC.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    firstOperator = display.text.toDouble()
                    result = firstOperator / 100
                    display = TextFieldValue(result.toString())
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.light_blue)
            )
        ) {
            Text(
                text = "%",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonDivision) {
                    start.linkTo(buttonBack.start, margin = 10.dp)
                    top.linkTo(buttonC.top)
                    end.linkTo(buttonBack.end, margin = 10.dp)
                    bottom.linkTo(buttonC.bottom)
                },
            onClick = {
                if (display.text != "0" && !display.text.contains("/") && !display.text.contains("+") && !display.text.contains("-") && !display.text.contains("x")) {
                    firstOperator = display.text.toDouble()
                    display = TextFieldValue(display.text + " / ")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.light_blue)
            )
        ) {
            Text(
                text = "/",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button1) {
                    start.linkTo(buttonC.start)
                    top.linkTo(buttonC.bottom, margin = 10.dp)
                    end.linkTo(buttonC.end)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "1")
                } else {
                    display = TextFieldValue("1")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "1",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button2) {
                    start.linkTo(buttonParenthesis.start)
                    top.linkTo(button1.top)
                    end.linkTo(buttonParenthesis.end)
                    bottom.linkTo(button1.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "2")
                } else {
                    display = TextFieldValue("2")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "2",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button3) {
                    start.linkTo(buttonPercentage.start)
                    top.linkTo(button1.top)
                    end.linkTo(buttonPercentage.end)
                    bottom.linkTo(button1.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "3")
                } else {
                    display = TextFieldValue("3")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "3",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonMultiplication) {
                    start.linkTo(buttonDivision.start)
                    top.linkTo(button1.top)
                    end.linkTo(buttonDivision.end)
                    bottom.linkTo(button1.bottom)
                },
            onClick = {
                if (display.text != "0" && !display.text.contains("x") && !display.text.contains("+") && !display.text.contains("-") && !display.text.contains("/")) {
                    firstOperator = display.text.toDouble()
                    display = TextFieldValue(display.text + " x ")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.light_blue)
            )
        ) {
            Text(
                text = "x",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button4) {
                    start.linkTo(buttonC.start)
                    top.linkTo(button1.bottom, margin = 10.dp)
                    end.linkTo(buttonC.end)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "4")
                } else {
                    display = TextFieldValue("4")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "4",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button5) {
                    start.linkTo(buttonParenthesis.start)
                    top.linkTo(button4.top)
                    end.linkTo(buttonParenthesis.end)
                    bottom.linkTo(button4.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "5")
                } else {
                    display = TextFieldValue("5")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "5",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button6) {
                    start.linkTo(buttonPercentage.start)
                    top.linkTo(button4.top)
                    end.linkTo(buttonPercentage.end)
                    bottom.linkTo(button4.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "6")
                } else {
                    display = TextFieldValue("6")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "6",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonSum) {
                    start.linkTo(buttonDivision.start)
                    top.linkTo(button4.top)
                    end.linkTo(buttonDivision.end)
                    bottom.linkTo(button4.bottom)
                },
            onClick = {
                if (display.text != "0" && !display.text.contains("+") && !display.text.contains("/") && !display.text.contains("-") && !display.text.contains("x")) {
                    firstOperator = display.text.toDouble()
                    display = TextFieldValue(display.text + " + ")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.light_blue)
            )
        ) {
            Text(
                text = "+",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button7) {
                    start.linkTo(buttonC.start)
                    top.linkTo(button4.bottom, margin = 10.dp)
                    end.linkTo(buttonC.end)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "7")
                } else {
                    display = TextFieldValue("7")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "7",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button8) {
                    start.linkTo(buttonParenthesis.start)
                    top.linkTo(button7.top)
                    end.linkTo(buttonParenthesis.end)
                    bottom.linkTo(button7.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "8")
                } else {
                    display = TextFieldValue("8")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "8",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button9) {
                    start.linkTo(buttonPercentage.start)
                    top.linkTo(button7.top)
                    end.linkTo(buttonPercentage.end)
                    bottom.linkTo(button7.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "9")
                } else {
                    display = TextFieldValue("9")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "9",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonSubtract) {
                    start.linkTo(buttonDivision.start)
                    top.linkTo(button7.top)
                    end.linkTo(buttonDivision.end)
                    bottom.linkTo(button7.bottom)
                },
            onClick = {
                if (display.text != "0" && !display.text.contains("-") && !display.text.contains("+")  && !display.text.contains("/")  && !display.text.contains("x")) {
                    firstOperator = display.text.toDouble()
                    display = TextFieldValue(display.text + " - ")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.light_blue)
            )
        ) {
            Text(
                text = "-",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonDot) {
                    start.linkTo(buttonC.start)
                    top.linkTo(button7.bottom, margin = 10.dp)
                    end.linkTo(buttonC.end)
                },
            onClick = {
                if (display.text.matches(Regex(".*\\d$")) && !display.text.contains(".")) {
                    display = TextFieldValue(display.text + ".")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = ".",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button0) {
                    start.linkTo(buttonParenthesis.start)
                    top.linkTo(buttonDot.top)
                    end.linkTo(buttonParenthesis.end)
                    bottom.linkTo(buttonDot.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    display = TextFieldValue(display.text + "0")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "0",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(button000) {
                    start.linkTo(buttonPercentage.start)
                    top.linkTo(buttonDot.top)
                    end.linkTo(buttonPercentage.end)
                    bottom.linkTo(buttonDot.bottom)
                },
            onClick = {
                if (display.text != "000") {
                    display = TextFieldValue(display.text + "000")
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.dark_blue)
            )
        ) {
            Text(
                text = "000",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }

        Button(
            modifier = Modifier
                .size(85.dp, 60.dp)
                .constrainAs(buttonEqual) {
                    start.linkTo(buttonDivision.start)
                    top.linkTo(buttonDot.top)
                    end.linkTo(buttonDivision.end)
                    bottom.linkTo(buttonDot.bottom)
                },
            onClick = {
                if (display.text != "0") {
                    if (display.text.contains("+")) {
                        if (display.text.split(" + ")[1].isNotEmpty()) {
                            secondOperator = display.text.split(" + ")[1].toDouble()
                            result = firstOperator + secondOperator
                        }
                    } else if (display.text.contains("-")) {
                        if (display.text.split(" - ")[1].isNotEmpty()) {
                            secondOperator = display.text.split(" - ")[1].toDouble()
                            result = firstOperator - secondOperator
                        }
                    } else if (display.text.contains("x")) {
                        if (display.text.split(" x ")[1].isNotEmpty()) {
                            secondOperator = display.text.split(" x ")[1].toDouble()
                            result = firstOperator * secondOperator
                        }
                    } else if (display.text.contains("/")) {
                        if (display.text.split(" / ")[1].isNotEmpty()) {
                            secondOperator = display.text.split(" / ")[1].toDouble()
                            result = firstOperator / secondOperator
                        }
                    }

                    formattedResult = if (result.toInt().toDouble() == result) {
                        result.toInt().toString() // Si el resultado es un número entero, convertirlo a cadena sin el decimal .0
                    } else {
                        result.toString() // Si el resultado tiene decimales, mostrarlo tal como es
                    }

                    display = TextFieldValue(formattedResult)
                }
            },
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.light_blue)
            )
        ) {
            Text(
                text = "=",
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroidCalculatorComposeTheme {
        Calculator()
    }
}