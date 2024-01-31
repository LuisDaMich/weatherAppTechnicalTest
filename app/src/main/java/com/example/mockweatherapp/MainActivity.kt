package com.example.mockweatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.mockweatherapp.dagger.AppComponent
import com.example.mockweatherapp.model.Data
import com.example.mockweatherapp.model.WeatherDataResponse
import com.example.mockweatherapp.viewModel.ShowWeatherViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var viewModel: ShowWeatherViewModel
    private lateinit var response: WeatherDataResponse
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        fetchApiData()
        setContent {
            CompleteView()
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
        val randNum = Random.nextInt(response.temperature.data.size)
        return response.temperature.data[randNum]
    }
    @Composable
    fun CompleteView(){
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                val clValueContainer = createRef()
                val txtLocationTitle = createRef()
                val txtLocationValue = createRef()
                val txtTemperatureTitle = createRef()
                val txtTemperatureValue = createRef()
                val btnNextLocation = createRef()
                var temperature by remember {
                    mutableStateOf(getString(R.string.temperature_placeholder))
                }
                var location by remember {
                    mutableStateOf(getString(R.string.location_placeholder))
                }
                ConstraintLayout(
                    modifier = Modifier
                        .constrainAs(clValueContainer){
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            top.linkTo(parent.top)
                            bottom.linkTo(parent.bottom)
                    }
                ) {
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
                }
                NextRandomLocationButton(modifier = Modifier.constrainAs(btnNextLocation){
                    top.linkTo(clValueContainer.bottom, margin = 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)

                }) {
                    val data = getAnotherLocation()
                    location = " ${data.place} "
                    temperature = " ${data.value} ${data.unit} "
                }
            }
        }
    }

    @Composable
    fun TextLocationTitle(modifier: Modifier) {
        Text(
            text = getString(R.string.location_title),
            modifier = modifier,
        )
    }

    @Composable
    fun TextLocationValue(modifier: Modifier, text: String) {
        Text(
            text = text,
            modifier = modifier.border(
                width = .2.dp,
                color = Color.Black,
                shape = RectangleShape
            )
        )
    }

    @Composable
    fun TextTemperatureTitle(modifier: Modifier) {
        Text(
            text = getString(R.string.temperature_title),
            modifier = modifier
        )
    }

    @Composable
    fun TextTemperatureValue(modifier: Modifier, text: String) {
        Text(
            text = text,
            modifier = modifier.border(
                width = .2.dp,
                color = Color.Black,
                shape = RectangleShape
            )
        )
    }

    @Composable
    fun NextRandomLocationButton(modifier: Modifier, onClickListener: ()-> Unit) {
        Button(
            onClick = onClickListener,
            modifier = modifier
        ) {
            Text(
                text = getString(R.string.btn_text)
            )
        }
    }
}