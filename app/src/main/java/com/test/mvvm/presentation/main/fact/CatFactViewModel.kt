package com.test.mvvm.presentation.main.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvm.data.Result
import com.test.mvvm.data.model.CatFactModel
import com.test.mvvm.data.repository.CatFactRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatFactViewModel @Inject constructor(
    private val catFactRepositoryImpl: CatFactRepositoryImpl
): ViewModel() {

    private val _catFactState: MutableStateFlow<Result<CatFactModel>> = MutableStateFlow(Result.empty())
    val catFactState: StateFlow<Result<CatFactModel>> = _catFactState

    fun getCatFact() {
        viewModelScope.launch {
            catFactRepositoryImpl.getCatFact().collect { result ->
                _catFactState.value = result
            }
        }
    }
}
