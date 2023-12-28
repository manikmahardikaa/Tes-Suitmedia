package com.example.testkm1.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.testkm1.data.repository.Repository


class ThirdScreenViewModel(private val repository: Repository): ViewModel() {
    val getUsersPaging = repository.getUsersPaging().cachedIn(viewModelScope)

}