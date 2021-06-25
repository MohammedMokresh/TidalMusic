package com.mokresh.tidalmusic.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.mokresh.tidalmusic.BR
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class KotlinBaseActivity<B : ViewDataBinding, out VM : BaseViewModel>(
    @LayoutRes var layoutId: Int,
    viewModelClass: KClass<VM>
) :
    AppCompatActivity() {
    private var _binding: B? = null

    lateinit var binding: B


    protected open val viewModel: VM by viewModel(viewModelClass)

    protected lateinit var loadingProgressBar: Dialog
    protected var actionbarView: View? = null
    var isActivityPaused = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, layoutId)
        if (_binding != null) {
            lifecycle.addObserver(viewModel)
            binding = _binding!!

            binding.lifecycleOwner = this
            binding.setVariable(BR.lifecycle, this)

            binding.setVariable(BR.vm, viewModel)
        } else {
            finish()
        }

    }


    override fun onDestroy() {
        lifecycle.removeObserver(viewModel)
        binding.unbind()
        _binding?.unbind()
        super.onDestroy()
    }


    override fun onPause() {
        super.onPause()
        isActivityPaused = true
    }

    override fun onResume() {
        super.onResume()
        isActivityPaused = false
    }


}