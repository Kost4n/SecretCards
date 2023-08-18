package estomp.esporte.lukalinat.database.adapter

import android.graphics.Rect
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import estomp.esporte.lukalinat.R
import estomp.esporte.lukalinat.database.entity.Record

class RecordAdapter(
    private val onRecordClickListener: OnRecordClickListener
) : RecyclerView.Adapter<RecordAdapter.RecordViewHolder>() {
    private var itemList = mutableListOf<Record>()

    inner class RecordViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val recordImageView = itemView.findViewById<ImageView>(R.id.record_image)

        fun bind(record: Record, position: Int) {
            recordImageView.setImageResource(record.imageId)

            itemView.setOnClickListener {
                onRecordClickListener.onRecordClick(record, position)
            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecordViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.record_row, parent, false)

        return RecordViewHolder(view)
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecordViewHolder, position: Int) =
        holder.bind(itemList[position], position)

    fun update(items: MutableList<Record>) {
        itemList = items
        notifyDataSetChanged()
    }
    interface OnRecordClickListener {
        fun onRecordClick(record: Record, position: Int)
    }
}

class RecycleItemDecoration(
    val spaceHeight: Int
): RecyclerView.ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View,
                                parent: RecyclerView, state: RecyclerView.State) {
        outRect.bottom = spaceHeight
    }
}