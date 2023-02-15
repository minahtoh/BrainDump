package com.example.braindump.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupMenu
import android.widget.PopupWindow
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.braindump.R
import com.example.braindump.databinding.DumpCardviewBinding
import com.example.braindump.model.Dump
import com.example.braindump.model.formattedDate

class DumpsRecycler: ListAdapter<Dump,DumpsRecycler.TheViewHolder>(DiffCallback) {
    inner class TheViewHolder(private val binding: DumpCardviewBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(dump: Dump){
            binding.apply {
                dumpText.text = dump.dump
                dateOfDump.text = dump.formattedDate()
                feelingsTitleText.setImageResource(
                    when(dump.feelings) {
                         1 -> { R.drawable.crying_face_96 }
                         2 -> { R.drawable.frowning_face_96}
                         3 -> { R.drawable.slightly_smiling_face_96}
                         4 -> { R.drawable.smiling_face_with_smiling_eyes_96}
                        else -> { R.drawable.grinning_face_with_big_eyes_96}
                    }
                )


            }
        }
    }
    companion object DiffCallback : DiffUtil.ItemCallback<Dump>(){
        override fun areItemsTheSame(oldItem: Dump, newItem: Dump): Boolean {
            return oldItem.date == newItem.date
        }

        override fun areContentsTheSame(oldItem: Dump, newItem: Dump): Boolean {
            return oldItem.hashCode() == newItem.hashCode()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TheViewHolder {
        return TheViewHolder(DumpCardviewBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TheViewHolder, position: Int) {
        val dump = getItem(position)
        holder.bind(dump)
        holder.itemView.setOnClickListener {
            onItemClickListener?.let { it(dump) }
        }
        val popup = PopupWindow(holder.itemView.context)
//        popup.contentView = binding
        holder.itemView.setOnLongClickListener {
            popup.showAsDropDown(it)
            true
        }
    }

    private var onItemClickListener: ((Dump) -> Unit)? = null
    fun setOnItemClickListener(listener:(Dump) -> Unit){
        onItemClickListener = listener
    }
}