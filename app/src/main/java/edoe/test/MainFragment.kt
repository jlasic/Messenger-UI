package edoe.test

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import edoe.test.databinding.MainFragmentBinding

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        with(binding) {
            toolbarBack.setOnClickListener { }
            toolbarVideoCall.setOnClickListener { }
            toolbarAudioCall.setOnClickListener { }
            toolbarMore.setOnClickListener { }
        }

        with(binding) {
            inputAdd.setOnClickListener { }
            inputSend.setOnClickListener {
                etInput.text = null
                etInput.hideKeyboard()
            }
        }

        Glide.with(requireContext())
            .load(R.drawable.avatar_strange).transform(RoundedCorners(20f.dpToPx()))
            .into(binding.toolbarAvatar)
    }


    private fun EditText.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}