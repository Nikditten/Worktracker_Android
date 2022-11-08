package dtu.amd.worktracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import dagger.hilt.android.AndroidEntryPoint
import dtu.amd.worktracker.navigation.Destination
import dtu.amd.worktracker.navigation.NavGraph
import dtu.amd.worktracker.navigation.NavigationBar
import dtu.amd.worktracker.ui.theme.WorktrackerTheme
import dtu.amd.worktracker.view.AddView
import dtu.amd.worktracker.view.EditWorkView
import dtu.amd.worktracker.viewmodel.AddViewModel
import dtu.amd.worktracker.viewmodel.EditViewModel
import dtu.amd.worktracker.viewmodel.MainViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorktrackerTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    NavGraph()
                }
            }
        }
    }
}