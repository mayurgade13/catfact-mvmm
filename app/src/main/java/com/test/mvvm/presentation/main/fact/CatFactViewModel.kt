package com.test.mvvm.presentation.main.fact

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvm.data.Result
import com.test.mvvm.data.api.ApiService
import com.test.mvvm.data.model.CatFactModel
import com.test.mvvm.data.repository.CatFactRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CatFactViewModel: ViewModel() {

    private val _catFactState: MutableStateFlow<Result<CatFactModel>> = MutableStateFlow(Result.loading(null))
    val catFactState = _catFactState

    fun getCatFact(catFactRepository: CatFactRepository, apiService: ApiService) {
        viewModelScope.launch {
            catFactRepository.getCatFact(apiService).collect { result ->
                _catFactState.value = result
            }
        }
    }
}
