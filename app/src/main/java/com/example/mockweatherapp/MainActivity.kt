package com.example.mockweatherapp

import android.os.Bundle
import android.util.Log
import android.view.View.OnClickListener
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mockweatherapp.model.Data
import com.example.mockweatherapp.model.WeatherDataResponse
import com.example.mockweatherapp.viewModel.ShowWeatherViewModel
import javax.inject.Inject
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    val viewModel = ShowWeatherViewModel()
    private lateinit var response: WeatherDataResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchApiData()
        setContent {
            CompleteView("location", "temperature")
        }
    }

    private fun fetchApiData() {
        viewModel.fetchDataFromApi(
            onSuccess = { response ->
                this@MainActivity.response = response
            },
            onError = {}
        )
    }

    private fun getAnotherLocation():Data {
        val rand = Random
        val randNUm = rand.nextInt(0, response.temperature.data.size)
        return response.temperature.data[randNUm]
    }
    @Composable
    fun CompleteView(location:String, temperature: String){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val txtLocationTitle = createRef()
                val txtLocationValue = createRef()
                val txtTemperatureTitle = createRef()
                val txtTemperatureValue = createRef()
                val btnNextLocation = createRef()
                var temperature by remember {
                    mutableStateOf("")
                }
                var location by remember {
                    mutableStateOf("")
                }
                TextLocationTitle(modifier = Modifier.constrainAs(txtLocationTitle){
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                })
                TextLocationValue(modifier = Modifier.constrainAs(txtLocationValue){
                    top.linkTo(parent.top)
                    start.linkTo(txtLocationTitle.end, margin = 40.6.dp)
                }, location)
                TextTemperatureTitle(modifier = Modifier.constrainAs(txtTemperatureTitle){
                    top.linkTo(txtLocationTitle.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                })
                TextTemperatureValue(modifier = Modifier.constrainAs(txtTemperatureValue){
                    top.linkTo(txtLocationValue.bottom, margin = 16.dp)
                    start.linkTo(txtTemperatureTitle.end, margin = 16.dp)
                }, temperature)
                NextRandomLocationButton(modifier = Modifier.constrainAs(btnNextLocation){
                    top.linkTo(txtTemperatureTitle.bottom, margin = 16.dp)

                }) {
                    Log.e("onClick", "aaa")
                    val data = getAnotherLocation()
                    location = data.place
                    temperature = "${data.value} ${data.unit}"
                }
            }
        }
    }

    @Composable
    fun TextLocationTitle(modifier: Modifier) {
        Text(
            text = "Location",
            modifier = modifier,
        )
    }

    @Composable
    fun TextLocationValue(modifier: Modifier, text: String) {
        Text(
            text = text,
            modifier = modifier.background(MaterialTheme.colorScheme.secondary)
        )
    }

    @Composable
    fun TextTemperatureTitle(modifier: Modifier) {
        Text(
            text = "Temperature",
            modifier = modifier
        )
    }

    @Composable
    fun TextTemperatureValue(modifier: Modifier, text: String) {
        Text(
            text = text,
            modifier = modifier.background(MaterialTheme.colorScheme.secondary)
        )
    }

    @Composable
    fun NextRandomLocationButton(modifier: Modifier, onClickListener: ()-> Unit) {
        Button(
            onClick = onClickListener ,
            modifier = modifier
        ) {
            Text(
                text = "Next Random Location"
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        CompleteView("Mexico", "26C")
    }
}