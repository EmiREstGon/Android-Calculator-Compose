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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator() {
    var display by remember { mutableStateOf(TextFieldValue("0")) }
    var firstOperator by remember { mutableStateOf(0.0) }
    var secondOperator by remember { mutableStateOf(0.0) }
    var result by remember { mutableStateOf(0.0) }
    var formattedResult by remember { mutableStateOf("") }

    Box (
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Calculator",
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(bottom = 28.dp),
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = colorResource(R.color.dark_blue)
            )

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 100.dp),
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

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    Button(
                        modifier = Modifier
                            .weight(3.45f)
                            .height(60.dp),
                        onClick =  {},
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Transparent
                        )
                    ) {
                        Text(
                            text = ""
                        )
                    }

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .height(60.dp),
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
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
                        onClick = {
                            if (display.text != "0") {
                                display = TextFieldValue("(" + display.text + ")")
                            }
                        },
                        //enabled = false,
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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
                }

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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

                    Spacer(modifier = Modifier.width(10.dp))

                    Button(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .height(60.dp),
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