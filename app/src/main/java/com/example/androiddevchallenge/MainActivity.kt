/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.typography

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

@Preview
@Composable
fun ImageForCountdown() {
    MaterialTheme {
        Column(modifier = Modifier.absolutePadding(16.dp, 16.dp, 6.dp, 6.dp)) {
            CountDownTimerTask()
            ImageUp()
            ImageDwn()

        }

    }
}

// Start building your app here!
@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        //  Text(text = "Ready... Set... GO!")
        ImageForCountdown()
    }
}

@Composable
fun CountDownTimerTask() {
    var setProgress: String by remember { mutableStateOf(value = "Count down") }
    var num: Long by remember { mutableStateOf(value = 10) }

    val countDownTimer = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {
            num = millisUntilFinished / 1000
            setProgress = "$num"
            Log.e("timer", "" + millisUntilFinished)


        }

        override fun onFinish() {
            setProgress = "finished"

        }
    }
    Button(onClick = {
        countDownTimer.start()
    }) {
        Text(text = "Start Count Down")

    }
    ProgressImage(setProgress = setProgress)

}

@Composable
fun ImageUp() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.img3),
            contentDescription = null,
            modifier = Modifier
                .height(79.dp)
                .width(69.dp),
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds
        )

    }
}

@Composable
fun ProgressImage(setProgress: String) {

    Text(text = setProgress, modifier = Modifier.padding(6.dp), style = typography.h5)

}

@Composable
fun ImageDwn() {
    Box {
        Image(
            painter = painterResource(id = R.drawable.img11),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .height(79.dp)
                .width(69.dp)
        )


    }
}


@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}
