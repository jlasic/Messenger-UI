package edoe.test.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import edoe.test.dpToPx

class MessageDecorator(val divider: Int = 10f.dpToPx()) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val position = parent.getChildAdapterPosition(view)

        if (position != 0) {
            val prevType = parent.adapter!!.getItemViewType(position - 1)
            val viewType = parent.adapter!!.getItemViewType(position)

            if (prevType != viewType) {
                outRect.top =divider
            }
        }

    }
}