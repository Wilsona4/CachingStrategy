package com.funcrib.cachingstrategy.viewmodel

import androidx.lifecycle.*
import com.funcrib.cachingstrategy.data.domain.Restaurant
import com.funcrib.cachingstrategy.repository.Repository
import com.funcrib.cachingstrategy.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: Repository,
) : ViewModel() {

//    val getRestaurant = repository.getRestaurant().asLiveData()

    private var _getRestaurant = MutableLiveData<Resource<List<Restaurant>>>()
    val getRestaurant: LiveData<Resource<List<Restaurant>>> get() = _getRestaurant

    init {
        getRestaurant()
    }

    /*get Restaurant List*/

    private fun getRestaurant() {
        viewModelScope.launch {
            val response = repository.getRestaurant()
            response.collect {
                _getRestaurant.value = it
            }
        }
    }

}