# Mock Weather App

Mock Weather App is a simple Android application that retrieves mock weather responses for various locations, displays the temperature information in a `TextView`, and allows users to get another random location's weather by clicking a button.

## Features

- Display temperature information for different locations in a TextView.
- Fetch a new random location's weather by clicking a button.

## Getting Started

To get started with the Mock Weather App, follow the steps below:

https://github.com/LuisDaMich/weatherAppTechnicalTest.git

Open the project in Android Studio.

Build and run the app on an Android emulator or device.

## API Response Model

{
    "temperature": {
        "data": [
            {
            "place":  "Mexico City",
            "value": 26,
            "unit": "c"
            }
        ],
        "recordTime": "2023-11-09T17:00:00+08:00"
    }
}

## Usage

Launch the app on an Android device or emulator.

View the temperature information for different locations displayed in the TextView.

Click the "Get Another Location" button to fetch and display the temperature for a new random location.