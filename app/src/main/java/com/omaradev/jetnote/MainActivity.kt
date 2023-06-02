package com.omaradev.jetnote

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.omaradev.jetnote.routing.Screen
import com.omaradev.jetnote.theme.JetNoteTheme
import com.omaradev.jetnote.ui.AppDrawer
import com.omaradev.jetnote.ui.all_notes.AllNotesScreen
import com.omaradev.jetnote.ui.save_note.SaveNoteScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            JetNoteTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()
                val drawerState = scaffoldState.drawerState
                val coroutineScope = rememberCoroutineScope()
                val viewModel:MainViewModel by viewModels()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                    drawerContent =
                    {
                        AppDrawer(Screen.Notes) {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    }, content = {
                        NavHost(
                            navController = navController, startDestination = Screen.Notes.routing
                        ) {
                            composable(Screen.Notes.routing) {
                                AllNotesScreen(onClickSaveNote = {
                                    navController.navigate(Screen.SaveNote.routing)
                                }, onOpenNavDrawer = {
                                    coroutineScope.launch { drawerState.open() }
                                }, viewModel = viewModel)
                            }
                            composable(Screen.SaveNote.routing) {
                                SaveNoteScreen(onClickOnBackIcon = {
                                    navController.navigateUp()
                                }, viewModel = viewModel )
                            }

                        }
                    })
            }
        }
    }
}
