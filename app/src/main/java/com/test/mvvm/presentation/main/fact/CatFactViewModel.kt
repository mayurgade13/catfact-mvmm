package com.test.mvvm.presentation.main.fact

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.mvvm.data.Result
import com.test.mvvm.data.model.CatFactModel
import com.test.mvvm.data.repository.CatFactRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatFactViewModel @Inject constructor(
    private val catFactRepository: CatFactRepository
): ViewModel() {

    private val _catFactState: MutableStateFlow<Result<CatFactModel>> = MutableStateFlow(Result.empty())
    val catFactState: StateFlow<Result<CatFactModel>> = _catFactState

    // Single Live Event
//    private val _catFactState: MutableSharedFlow<Result<CatFactModel>> = MutableSharedFlow()
//    val catFactState: SharedFlow<Result<CatFactModel>> = _catFactState

    fun getCatFact() {
        viewModelScope.launch {
            catFactRepository.getCatFact().collect { result ->
                _catFactState.value = result
//                _catFactState.emit(result)
            }
        }
    }
}
