package com.example.focus

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import androidx.core.content.ContextCompat.startActivity
import com.example.focus.Model.Permissions.PermissionFunctions
import com.example.focus.ui.theme.FocusTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (!PermissionFunctions(this, packageName).isPackageUsageStatsPermissionEnabled()) {
            startActivity(Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS))
        }


        setContent {
            FocusTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    //MainScreen(this, packageName)
                    Greeting("TUDOR")
                    println("SUNT IN MAIN")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    FocusTheme {
        Greeting("Android")
    }
}


@Composable
fun MainScreen(context : Context, packageName : String) {
    val isPermissionEnabled = remember { PermissionFunctions(context, packageName).isPackageUsageStatsPermissionEnabled() }
    val navController = rememberNavController()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Display info
        Text("This is the info screen.")

        // Display button
        IconButton(
            onClick = {
                if (isPermissionEnabled) {
                    // Start the intent
                    // Your logic here
                } else {
                    // Navigate to usage access settings
                    val intent = Intent(Settings.ACTION_USAGE_ACCESS_SETTINGS)
                    context.startActivity(intent)
                }
            }
        ) {
            Icon(
                if (isPermissionEnabled) {
                    Icons.Default.PlayArrow
                } else {
                    Icons.Default.Settings
                },
                contentDescription = "Start Intent or Open Settings"
            )
        }
    }
}