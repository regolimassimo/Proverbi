package it.massimoregoli.proverbi.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import it.massimoregoli.proverbi.persistence.DaoProverbi
import it.massimoregoli.proverbi.persistence.DbProverbi
import it.massimoregoli.proverbi.persistence.Proverbi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ProverbiVM(application:Application) : AndroidViewModel(application) {
    private var filterData: LiveData<List<Proverbi>>? = null
    var lista:MutableLiveData<MutableList<Proverbi>> = MutableLiveData()
    var filterString = ""

//    fun getProverbi(pDAO: DaoProverbi): LiveData<List<Proverbi>> {
//        if (filterData == null) {
//            filterData = pDAO.loadAll()
//        }
//        lista.value = filterData!!.value
//        return filterData!!
//    }

    fun update(proverbio: Proverbi) {
        val db = DbProverbi.getInstance(getApplication())
        CoroutineScope(Dispatchers.IO).launch {
//            db.proverdiDAO().update(proverbio)
            db.proverdiDAO().delete(proverbio)
            val b = lista.value?.remove(proverbio)
            Log.w("PR21", "VAL " + b)
//            filter(filterString)
        }
    }

    fun filter(p0: String?) {
        filterString = p0!!
        val db = DbProverbi.getInstance(getApplication())
        var theList: MutableList<Proverbi>
        CoroutineScope(Dispatchers.IO).launch {
            if (p0 != null) {
                if (p0.startsWith("#lang:")) {
                    theList = db.proverdiDAO().loadAllByLanguage(p0.substring(6))
                } else {
                    theList = db.proverdiDAO()
                        .loadAllByTag(String.format("%%%s%%", p0))
                }
                CoroutineScope(Dispatchers.Main).launch {
                    lista.value = theList
                }
            }
        }
    }
}