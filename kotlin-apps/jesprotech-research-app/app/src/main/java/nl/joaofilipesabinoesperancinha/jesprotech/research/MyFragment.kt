package nl.joaofilipesabinoesperancinha.jesprotech.research

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModel : ViewModel() {
    private val _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    init {
        _text.value = "Hello from ViewModel!"
    }
}
class MyFragment : Fragment() {
    private lateinit var viewModel: MyViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[MyViewModel::class.java]
        val textView: TextView = view.findViewById(R.id.textView)
        viewModel.text.observe(viewLifecycleOwner) { newText ->
            textView.text = newText
        }
    }
}