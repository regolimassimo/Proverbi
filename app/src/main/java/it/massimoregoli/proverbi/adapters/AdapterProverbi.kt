package it.massimoregoli.proverbi.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.chip.Chip
import it.massimoregoli.proverbi.MainActivity
import it.massimoregoli.proverbi.ViewModel.ProverbiVM
import it.massimoregoli.proverbi.databinding.ItemProverbiBinding
import it.massimoregoli.proverbi.persistence.Flags
import it.massimoregoli.proverbi.persistence.Proverbi

class AdapterProverbi(val model: ProverbiVM): RecyclerView.Adapter<AdapterProverbi.ViewHolder>() {
    var proverbi: List<Proverbi> = emptyList()
    init{
        if (model.lista.value != null)
            proverbi = model.lista.value!!
    }

    class ViewHolder(binding: ItemProverbiBinding): RecyclerView.ViewHolder(binding.root) {
        val tvText: TextView = binding.textView
        val chLike: Chip = binding.chLike
        val tvCountry: TextView = binding.tvCountry

        var proverbio: Proverbi = Proverbi(0, "", "", 0, 0)
            set(value) {
                field = value
                chLike.isChecked = field.preferito == 1
                tvText.text = field.testo
                tvCountry.text = Flags.COUNTRIES[field.lingua]
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        val binding = ItemProverbiBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.proverbio = proverbi[position]
        holder.chLike.setOnClickListener {
            proverbi[position].preferito = if (proverbi[position].preferito == 1) 0 else 1
            Log.w(MainActivity.TAG, "BINGO!" + proverbi[position].id)
            model.update(proverbi[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int {
        return proverbi.size
    }

    override fun getItemId(position: Int): Long {
        return proverbi[position].id.toLong()
    }

}