package ziapps.ilokyou.app.base.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

open class CoroutineViewModel : ViewModel() {
    private val viewModelJob = SupervisorJob()
    protected val uiScope = CoroutineScope(Dispatchers.Main.immediate + viewModelJob)

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
