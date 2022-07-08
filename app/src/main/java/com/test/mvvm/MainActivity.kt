package com.test.mvvm

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.mvvm.data.Result
import com.test.mvvm.data.RetrofitBuilder
import com.test.mvvm.data.repository.CatFactRepository
import com.test.mvvm.presentation.main.fact.CatFactViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewmodel: CatFactViewModel = ViewModelProvider(this)[CatFactViewModel::class.java]

        lifecycleScope.launchWhenStarted {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.catFactState.collect { result ->
                    when(result.status) {
                        Result.STATUS.SUCCESS -> {
                            Log.d("####", "Success - "+result.data?.fact)
                        }
                        Result.STATUS.ERROR -> {
                            Log.d("####", "Error...")
                        }
                        Result.STATUS.LOADING -> {
                            Log.d("####", "Loading...")
                        }
                    }
                }
            }
        }
        viewmodel.getCatFact(
            CatFactRepository(),
            RetrofitBuilder.apiService
        )
    }
}
