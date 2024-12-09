package com.learning.ad.ff1.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.learning.ad.ff1.data.State
import com.learning.ad.ff1.databinding.StateItemLayoutBinding

class StateAdapter(
   private val states: List<State>,
   private val onClick: (State) -> Unit
) : RecyclerView.Adapter<StateAdapter.ViewHolder>() {
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder(StateItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
   override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      val state = states[position]
      holder.bind(state)
      holder.itemView.setOnClickListener {
         onClick(state)
      }
   }
   override fun getItemCount() = states.size
   class ViewHolder(private val binding: StateItemLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
      fun bind(state: State) {
         binding.stateTv.text = state.name
         binding.titleTv.text = state.title
      }
   }
}
