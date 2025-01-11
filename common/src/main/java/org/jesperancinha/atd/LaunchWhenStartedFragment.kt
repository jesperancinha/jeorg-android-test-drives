package org.jesperancinha.atd

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.flowOf

class LaunchWhenStartedFragment : Fragment() {

    private val myFlow = flowOf("Item 1", "Item 2", "Item 3")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        @Suppress("DEPRECATION")
        lifecycleScope.launchWhenStarted {
            myFlow.collect { value ->
                println("Collected: $value")
            }
        }
    }
}
