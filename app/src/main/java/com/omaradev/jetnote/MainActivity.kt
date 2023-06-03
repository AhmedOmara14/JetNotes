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
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
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
                val viewModel: MainViewModel by viewModels()

                Scaffold(modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState,
                    drawerContent = {
                        AppDrawer(Screen.Notes) {
                            coroutineScope.launch {
                                drawerState.close()
                            }
                        }
                    },
                    content = {
                        NavHost(
                            navController = navController, startDestination = Screen.Notes.routing
                        ) {
                            composable(Screen.Notes.routing) {
                                AllNotesScreen(onClickSaveNote = {
                                    navController.navigate(Screen.SaveNote.routing + ("/${0}"))
                                }, onOpenNavDrawer = {
                                    coroutineScope.launch { drawerState.open() }
                                }, viewModel = viewModel, onClickNoteItem = { note ->
                                    navController.navigate(Screen.SaveNote.routing + ("/${note.id}"))
                                })
                            }
                            composable(
                                route = Screen.SaveNote.routing + "/{noteId}",
                                arguments = listOf(navArgument("noteId") {
                                    defaultValue = 0
                                    type = NavType.IntType
                                    nullable = false
                                })
                            ) {
                                it.arguments?.getInt("noteId")?.let { it1 ->
                                    SaveNoteScreen(
                                        onClickOnBackIcon = {
                                            navController.navigateUp()
                                        },
                                        viewModel = viewModel,
                                        noteId = it1
                                    )
                                }
                            }
                        }
                    })
            }
        }
    }
}
