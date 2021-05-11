package it.massimoregoli.proverbi

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import it.massimoregoli.interfaces.SelectMode
import it.massimoregoli.proverbi.ViewModel.ProverbiVM
import it.massimoregoli.proverbi.adapters.AdapterProverbi
import it.massimoregoli.proverbi.databinding.ActivityMainBinding
import it.massimoregoli.proverbi.persistence.DbProverbi
import it.massimoregoli.proverbi.persistence.Proverbi

@Suppress("SpellCheckingInspection")
class MainActivity : AppCompatActivity() {
    companion object {
        const val TAG = "PR21"
    }
    private lateinit var binding: ActivityMainBinding
    private val model: ProverbiVM by viewModels()
    private lateinit var adapter: AdapterProverbi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createOrOpenDB(this)
        setLiveData()
        setUI(binding)

    }

    private fun setLiveData() {
        val observer = Observer<List<Proverbi>> { list ->
            if (list != null) {
                Log.w(TAG, "SIZE= " + list.size)
                adapter = AdapterProverbi(model)
                binding.rvProverbi.setBackgroundColor(Color.RED)
                binding.rvProverbi.adapter = adapter
            }
        }
        model.lista.observe(this@MainActivity, observer)
    }

    private fun setUI(binding: ActivityMainBinding) {
        val layoutManager = LinearLayoutManager(this@MainActivity)
        binding.rvProverbi.layoutManager = layoutManager
        val listener = MyQueryListener(model)
        binding.etSearch.setOnQueryTextListener(listener)

        binding.etSearch.setQuery("", true)
    }

    private fun createOrOpenDB(context: Context) {
//        val reader = assets.open(FILENAME_IT)
//            .bufferedReader()
        DbProverbi.getInstance(context)
//        CoroutineScope(Dispatchers.IO).launch {
//            val nrec = db.proverdiDAO().records()
//            if (nrec == 0) {
//                db.proverdiDAO().deleteAll()
//                reader.useLines { lines ->
//                    lines.forEach {
//                        val p = Proverbi(it)
//                        p.lingua = "it"
//                        db.proverdiDAO().insert(p)
//                        delay(1)
//                    }
//                }
//            }
//            launch(Dispatchers.Main) {
//                binding.pbWait.visibility = View.GONE
//            }
//        }
    }

    class MyQueryListener(private val model: ProverbiVM) : SearchView.OnQueryTextListener {

        override fun onQueryTextSubmit(p0: String?): Boolean {

            return false
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            model.filter(p0)
            return false
        }

    }


}