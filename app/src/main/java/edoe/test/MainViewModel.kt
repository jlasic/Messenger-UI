package edoe.test

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edoe.test.models.Message
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val messagesLD: MutableLiveData<List<Message>> = MutableLiveData(
        listOf(
            Message(0, "Mr. Stark.", "07:22 PM", Message.Source.OTHER),
            Message(1, "You are strange..", "07:22 PM", Message.Source.ME),
            Message(2, "No shit Sherlock!", "07:30 PM", Message.Source.OTHER),
            Message(3, "So, you know…\u2028Many conventional colleges and universities are now offering online courses…", "07:30 PM", Message.Source.ME),
            Message(4, "Voice recording", "07:34 PM", Message.Source.OTHER),
            Message(5, "No shit Sherlock!", "07:35 PM", Message.Source.OTHER),
            Message(6, "Hola! Mucho Gusto! Me llamo Elliot! Hola Juan, hola Esteban, dónde esta esa biblioteca? esa es la casa de mi tía, no gracias, soy alérgico a los crustaceos. Muy bien! mejor no podría estar!", "07:37 PM", Message.Source.OTHER),
            Message(7, "U’re strange…", "07:35 PM", Message.Source.ME),
            Message(8, "So, you know…\u2028Many conventional colleges and universities are now offering online courses…", "07:35 PM", Message.Source.ME),
            Message(9, "Mr. Stark.", "07:40 PM", Message.Source.OTHER)
        )
    )

    private val statusLD: MutableLiveData<Boolean> = MutableLiveData()

    fun start(): LiveData<Boolean> {
        viewModelScope.launch(Dispatchers.IO) {
            repeat(100) {
                delay(3000)
                statusLD.postValue(statusLD.value?.not() ?: false)
            }
        }

        return statusLD
    }
}