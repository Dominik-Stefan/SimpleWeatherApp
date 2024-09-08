package hr.tvz.android.simpleweatherapp.fragments.location

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hr.tvz.android.simpleweatherapp.data.RemoteLocation
import hr.tvz.android.simpleweatherapp.databinding.ItemContainerLocationBinding

class LocationAdapter(
    private val onLocationClicked: (RemoteLocation) -> Unit
) : RecyclerView.Adapter<LocationAdapter.LocationViewHolder>() {

    private val locations = mutableListOf<RemoteLocation>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(data: List<RemoteLocation>) {
        locations.clear()
        locations.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        return LocationViewHolder(
            ItemContainerLocationBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(remoteLocation = locations[position])
    }

    override fun getItemCount(): Int {
        return locations.size
    }

    inner class LocationViewHolder(
      private val binding: ItemContainerLocationBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(remoteLocation: RemoteLocation) {
            with(remoteLocation) {
                val location = "$name, $region, $country"
                binding.textRemoteLocation.text = location
                binding.root.setOnClickListener { onLocationClicked(remoteLocation) }
            }
        }
    }
}