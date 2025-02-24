package com.ad.bookshelf.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ad.bookshelf.R
import com.ad.bookshelf.model.Book
import com.ad.bookshelf.ui.screen.HomeScreen
import com.ad.bookshelf.ui.stateholder.BookShelfViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfApp(){
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { BookShelfAppBar(scrollBehavior = scrollBehavior) },
        content = {
            val bookShelfViewModel: BookShelfViewModel = viewModel(factory = BookShelfViewModel.Factory)
            HomeScreen(
                bookShelfUiState = bookShelfViewModel.bookShelfUiState,
                modifier = Modifier.fillMaxSize(),
                retryAction = {bookShelfViewModel.searchBooks()}
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BookShelfAppBar(scrollBehavior: TopAppBarScrollBehavior, modifier: Modifier = Modifier){
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = stringResource(R.string.app_name)
            )
        },
        modifier = modifier,
        scrollBehavior = scrollBehavior
    )
}