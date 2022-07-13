package com.example.cupcake.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class OrderViewModel : ViewModel() {

    private val _quantity = MutableLiveData<Int>(0)
    val quantity: LiveData<Int> = _quantity

    private val _flavor = MutableLiveData<String>("")
    val flavor: LiveData<String> = _flavor

    private val _date = MutableLiveData<String>("")
    val date: LiveData<String> = _date

    private val _price = MutableLiveData<Double>(0.0)
    val price: LiveData<Double> = _price

    fun setQuantity(numberCupcakes: Int) {
        _quantity.value = numberCupcakes
    }

    fun setFlavor(desiredFlavor: String) {
        _flavor.value = desiredFlavor
    }

    fun setDate(pickupDate: String) {
        _date.value = pickupDate
    }
    // 컵케이크의 맛이 설정 되었는지 여부 확인
    fun hasNoFlavorSet(): Boolean {
        return _flavor.value.isNullOrEmpty()
    }

    val dateOptions = getPickupOptions()

    private fun getPickupOptions(): List<String> {
        val options = mutableListOf<String>()
        val formatter = SimpleDateFormat("E MMM d", Locale.getDefault())
        val calendar = Calendar.getInstance()
        repeat(4) {
            options.add(formatter.format(calendar.time))
            calendar.add(Calendar.DATE, 1)
        }
        return options
    }

    fun resetOrder() {
        _quantity.value = 0
        _flavor.value = ""
        _date.value = dateOptions[0]
        _price.value = 0.0
    }

    init {
        resetOrder()
    }

}