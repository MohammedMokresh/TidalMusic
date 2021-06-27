package com.mokresh.tidalmusic.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.dansdev.libeventpipe.EventPipe
import com.mokresh.tidalmusic.ext.observe
import com.mokresh.tidalmusic.BR
import com.mokresh.tidalmusic.utils.UIEvent
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.reflect.KClass


abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes val layoutId: Int, viewModelClass: KClass<VM>
) : Fragment() {


    private var _binding: B? = null

    lateinit var binding: B
    abstract fun onUIEventTriggered(event: UIEvent)

    open val viewModel: VM by viewModel(viewModelClass)
    var isFragmentVisible = false
    var isFragmentPaused = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.addObserver(viewModel)
        registerEvent()
    }

    init {
        viewLifecycleOwnerLiveData.observe(this) { viewLifecycleOwner ->
            viewLifecycleOwner.lifecycle.addObserver(object : DefaultLifecycleObserver {
                /** Since we are observing on the view's lifecycle, this is
                 * the equivalent of the Fragment's onDestroyView() method
                 */
                override fun onDestroy(owner: LifecycleOwner) {
                    (_binding as? ViewDataBinding)?.unbind()
                    binding.unbind()
                }
            })
        }

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DataBindingUtil.inflate(inflater, layoutId, container, false)

        return if (_binding != null) {
            binding = _binding!!
            binding.lifecycleOwner = this
            binding.setVariable(BR.lifecycle, this)
            binding.setVariable(BR.vm, viewModel)
            binding.root

        } else {
            null
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(viewModel) {
            observe(progressLiveEvent) { show ->
                if (show) (activity as BaseActivity<*, *>).showProgress()
                else (activity as BaseActivity<*, *>).hideProgress()
            }

            observe(errorMessage) { msg ->
                Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
            }
        }

    }

    fun registerEvent() {
        EventPipe.registerEvent(UIEvent::class.java) { event ->
            onUIEventTriggered(event)
        }
    }

    override fun onDestroy() {
        lifecycle.removeObserver(viewModel)
        EventPipe.unregisterAllEvents()
        super.onDestroy()
    }


    override fun onPause() {
        super.onPause()
        isFragmentPaused = true
    }

    override fun onResume() {
        super.onResume()
        isFragmentPaused = false
    }

    override fun setMenuVisibility(visible: Boolean) {
        isFragmentVisible = visible
        super.setMenuVisibility(visible)
    }

    fun launchOnLifecycleScope(execute: suspend () -> Unit) {
        this.lifecycleScope.launchWhenCreated {
            execute()
        }
    }

}