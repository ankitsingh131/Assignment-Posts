package com.assignment.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.assignment.arch.SingleLiveEvent

/**
 * Author: Ankit Singh
 * Package: com.assignment.presentation.viewModel
 * Project: EITC du Assignment
 **/

class SharedViewModel: ViewModel() {

    val fetchFavoritePosts = SingleLiveEvent<Void?>()
}