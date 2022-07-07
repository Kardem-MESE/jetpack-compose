package com.example.workshop

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
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
                    MainScreen()
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

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    WorkshopTheme {
    }
}

@Composable
fun MainScreen(){
    val navController= rememberNavController()
    Scaffold(
        topBar = {TopBar()},
        bottomBar = {BottomNavigationBar(navController)}
    ) {
        Navigation(navController)
    }
}

@Composable
fun TopBar(){
    TopAppBar(
        title = { Text(text = "Bottom Navigation", fontSize = 18.sp)},
        backgroundColor = Color.Blue,
        contentColor = Color.Black
    )
}

@Composable
fun BottomNavigationBar(navController: NavController){
    val items = listOf(
        NavigationItems.Home,
        NavigationItems.Profile,
        NavigationItems.Settings
    )
    BottomNavigation(
        backgroundColor = Color.Blue,
        contentColor = Color.Black
    ){
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach{items ->
            BottomNavigationItem(
                icon = { Icon(painter = painterResource(id = items.icon), contentDescription = items.title )},
                label = { Text(text = items.title)},
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected =currentRoute == items.route,
                onClick = {
                    navController.navigate(items.route){
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route = route){
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

@Composable
fun MainPage(navController: NavController) {

    val movies = remember { Details.MovieDetailsList }
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(
            movies
        ) {
            MovieCard(mve = it)
        }
    }
}

@Composable
fun ProfileScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Profile Screen",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun SettingsScreen(){
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Settings Screen",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun Navigation(navController: NavHostController){
    NavHost(navController, startDestination = NavigationItems.Home.route){
        composable(NavigationItems.Home.route){
            MainPage(navController)
        }
        composable(NavigationItems.Profile.route){
            ProfileScreen()
        }
        composable(NavigationItems.Settings.route){
            SettingsScreen()
        }
    }
}

