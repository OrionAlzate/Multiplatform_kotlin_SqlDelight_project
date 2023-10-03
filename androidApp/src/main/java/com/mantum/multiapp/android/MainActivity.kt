package com.mantum.multiapp.android

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.cash.sqldelight.db.SqlDriver
import com.mantum.database.Database
import com.mantum.multiapp.Greeting
import commantumdatabase.UserQueries
import database.DriverFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(Greeting().greet())
                }
            }
        }


        leerDatos(DriverFactory(this).createDriver())

    }
}


@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}



fun leerDatos(driver: SqlDriver){
    val database = Database(driver)
    val userQueries :UserQueries = database.userQueries


    /*userQueries.setUsuarios(1,"Luis","perez")
    userQueries.setUsuarios(2,"Sofia","Reyes")
    userQueries.setUsuarios(3,"Arnold","Diaz")*/
    Log.i("dato", userQueries.getAllUsers().executeAsList().toString() )

}