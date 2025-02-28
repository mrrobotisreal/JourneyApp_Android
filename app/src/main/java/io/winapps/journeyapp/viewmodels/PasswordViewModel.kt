package io.winapps.journeyapp.viewmodels
//
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.snapshotFlow
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.*
//import kotlinx.coroutines.launch
//
//class PasswordViewModel : ViewModel() {
//    var password = mutableStateOf("")
//    var isPasswordValid = mutableStateOf<Boolean?>(null)
//    var errorMessage = mutableStateOf("")
//
//    init {
//        viewModelScope.launch {
//            snapshotFlow { password.value }
//                .debounce(1500)
//                .distinctUntilChanged()
//                .filter { it.isNotBlank() }
//                .collect { newPassword ->
//                    isPasswordValid.value = checkIsPasswordValid(newPassword)
//                }
//        }
//    }
//
//    private fun checkIsPasswordValid(password: String): Boolean {
//        return true // handle this later with regex
//    }
//}