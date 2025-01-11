package org.jesperancinha.atd

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OnViewCreatedFragment : Fragment() {

    private val myFlow = flowOf("Item 1", "Item 2", "Item 3")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launch {
            myFlow.collectWithLifecycle(viewLifecycleOwner.lifecycle, Lifecycle.State.STARTED) { value ->
                println("Collected: $value")
            }
        }
    }
}
