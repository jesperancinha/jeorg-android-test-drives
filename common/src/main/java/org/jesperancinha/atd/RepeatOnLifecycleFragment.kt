package org.jesperancinha.atd

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RepeatOnLifecycleFragment : Fragment() {

    private val myFlow = flow {
        emit("Hello, Flow!")
        kotlinx.coroutines.delay(1000)
        emit("Another value")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Collecting the flow in a lifecycle-aware manner
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                myFlow.collect { value ->
                    println("Collected: $value")
                }
            }
        }
    }
}
