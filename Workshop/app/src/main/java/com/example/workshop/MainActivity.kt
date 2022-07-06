package com.example.workshop

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.workshop.ui.theme.WorkshopTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorkshopTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background

                ) {
                    Way()
                }
            }
        }
    }
}
@Composable
fun Way(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "mainpage" ){
        composable("mainpage"){
            MainPage(navController)
        }
        composable("listpage"){
            DetailsContent(navController)
        }
    }
}

@Composable
fun MainPage(navController: NavController){
        Column(modifier = Modifier.fillMaxSize()
            , horizontalAlignment = Alignment.CenterHorizontally
            , verticalArrangement = Arrangement.SpaceEvenly) {
            Text(text = "MOVIE LIST", fontSize = 36.sp , fontWeight = FontWeight.Bold)
            Image(painter = painterResource(id = R.drawable.movie_resim), contentDescription = "açıklama" )
            Button(onClick = { navController.navigate("ListPage") }
                , modifier = Modifier.size(width = 250.dp, height = 50.dp)) {
                Text(text = "CLICK!!")
            }
        }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WorkshopTheme {
    }
}