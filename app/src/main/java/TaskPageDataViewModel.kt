import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import database.Task

class TaskPageDataViewModel : ViewModel() {

    private val _data = MutableLiveData<List<Task>>()
    val data: LiveData<List<Task>> = _data

    fun setData(data: LiveData<List<Task>>) {
        data.observeForever { taskList ->
            _data.value = taskList
        }
    }
}