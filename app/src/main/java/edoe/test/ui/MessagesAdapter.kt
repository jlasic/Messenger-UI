package edoe.test.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import edoe.test.R
import edoe.test.databinding.ItemMessageMineBinding
import edoe.test.databinding.ItemMessageOtherBinding
import edoe.test.dpToPx
import edoe.test.models.Message

class MessagesAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var messages: List<Message> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    companion object {
        const val OTHER = 0
        const val ME = 1
    }

    override fun getItemViewType(position: Int): Int {
        return when (messages[position].source) {
            Message.Source.ME -> ME
            Message.Source.OTHER -> OTHER
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ME -> MyMessageVH.inflate(parent)
            OTHER -> OtherMessageVH.inflate(parent)
            else -> throw Exception("ViewType not implemented")
        }
    }

    override fun getItemCount(): Int = messages.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is OtherMessageVH -> {
                holder.bind(
                    messages[position],
                    messages.getOrNull(position - 1)?.source == Message.Source.OTHER
                )
            }
            is MyMessageVH -> {
                holder.bind(messages[position])
            }
        }
    }

    class OtherMessageVH(private val binding: ItemMessageOtherBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message, secondMessage: Boolean) = with(binding) {
            tvText.text = message.text
            tvTimestamp.text = message.timestamp

            if (secondMessage) {
                ivAvatar.visibility = View.INVISIBLE
                flMessageContainer.background =
                    ContextCompat.getDrawable(root.context, R.drawable.message_background_other_second)
            } else {
                ivAvatar.visibility = View.VISIBLE
                Glide.with(root.context).load(message.avatar)
                    .transform(RoundedCorners(20f.dpToPx()))
                    .into(ivAvatar)

                flMessageContainer.background =
                    ContextCompat.getDrawable(root.context, R.drawable.message_background_other)
            }
        }

        companion object {
            fun inflate(parent: ViewGroup): OtherMessageVH {
                val binding = ItemMessageOtherBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return OtherMessageVH(binding)
            }
        }
    }

    class MyMessageVH(private val binding: ItemMessageMineBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(message: Message) = with(binding) {
            tvText.text = message.text
        }

        companion object {
            fun inflate(parent: ViewGroup): MyMessageVH {
                val binding = ItemMessageMineBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return MyMessageVH(binding)
            }
        }
    }
}