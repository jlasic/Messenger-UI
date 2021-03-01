package edoe.test.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import edoe.test.MainViewModel
import edoe.test.R
import edoe.test.databinding.MainFragmentBinding
import edoe.test.dpToPx


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
            toolbarBack.setOnClickListener { activity?.onBackPressed() }
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

        val messagesAdapter = MessagesAdapter()
        binding.rvMessages.adapter = messagesAdapter
        binding.rvMessages.layoutManager = LinearLayoutManager(context)
        viewModel.messagesLD.observe(viewLifecycleOwner) {
            messagesAdapter.messages = it
            binding.rvMessages.smoothScrollToPosition(messagesAdapter.itemCount)
        }

        // keyboard opened
        binding.rvMessages.addOnLayoutChangeListener { _, _, _, _, bottom, _, _, _, oldBottom ->
            if (bottom < oldBottom) {
                binding.rvMessages.post { binding.rvMessages.smoothScrollToPosition(messagesAdapter.itemCount) }
            }
        }

        viewModel.start().observe(viewLifecycleOwner) {
            if (it) {
                binding.tvStatus.setText("Active now")
            } else {
                binding.tvStatus.setText("is typing...")
            }
        }
    }

    private fun EditText.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

}