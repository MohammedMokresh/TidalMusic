package com.mokresh.tidalmusic.base

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.dansdev.libeventpipe.EventPipe
import com.mokresh.tidalmusic.BR
import com.mokresh.tidalmusic.ext.observe
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseActivity<B : ViewDataBinding, out VM : BaseViewModel>(
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

            with(viewModel) {
                observe(progressLiveEvent) { show ->
                    if (show) showProgress()
                    else hideProgress()
                }

                observe(errorMessage) { msg ->
                    Toast.makeText(this@BaseActivity, msg, Toast.LENGTH_SHORT).show()
                }
            }

        } else {
            finish()
        }

    }

    fun showProgress() = BaseProgress().show(supportFragmentManager, "PROGRESS")

    fun hideProgress() =
        supportFragmentManager.fragments.filterIsInstance<BaseProgress>().forEach { it.dismiss() }


    override fun onDestroy() {
        lifecycle.removeObserver(viewModel)
        binding.unbind()
        _binding?.unbind()
        EventPipe.unregisterAllEvents()
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