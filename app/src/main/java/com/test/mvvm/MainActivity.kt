package com.test.mvvm

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.test.mvvm.data.Result
import com.test.mvvm.databinding.ActivityMainBinding
import com.test.mvvm.presentation.main.fact.CatFactViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val viewmodel: CatFactViewModel = ViewModelProvider(this)[CatFactViewModel::class.java]

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewmodel.catFactState.collect { result ->
                    when(result.status) {
                        Result.STATUS.SUCCESS -> {
                            binding.loadingView.visibility = View.GONE
                            binding.tvFact.text = result.data?.fact
                        }
                        Result.STATUS.ERROR -> {
                            binding.loadingView.visibility = View.GONE
                            // Temporary showing alert
                            val alert = AlertDialog.Builder(this@MainActivity)
                            alert.setTitle(getString(R.string.error_something_went_wrong))
                            alert.setPositiveButton(R.string.button_ok) { _: DialogInterface?, _: Int -> }
                            alert.show()
                        }
                        Result.STATUS.LOADING -> {
                            binding.loadingView.visibility = View.VISIBLE
                        }
                        else -> { }
                    }
                }
            }
        }
        binding.btnGetCatFact.setOnClickListener {
            viewmodel.getCatFact()
        }
    }
}
