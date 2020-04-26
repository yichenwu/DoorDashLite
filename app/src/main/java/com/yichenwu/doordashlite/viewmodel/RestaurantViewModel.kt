package com.yichenwu.doordashlite.viewmodel

import androidx.lifecycle.ViewModel
import com.yichenwu.doordashlite.converter.RestaurantStateConverter
import com.yichenwu.doordashlite.model.ui.*
import com.yichenwu.doordashlite.repository.RestaurantRepository
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers.io
import io.reactivex.subjects.BehaviorSubject

class RestaurantViewModel : ViewModel() {
    private val restaurantRepository = RestaurantRepository()
    private val disposables = CompositeDisposable()
    private val restaurantsSubject = BehaviorSubject.create<State>()
    private val restaurantStateConverter: RestaurantStateConverter by lazy { RestaurantStateConverter }

    init {
        fetchRestaurants()
    }

    override fun onCleared() {
        disposables.dispose()
        super.onCleared()
    }

    // hide the details this subject from UI
    fun observeRestaurants(): Observable<State> = restaurantsSubject.hide()

    // this triggers the restaurants api call
    fun fetchRestaurants() {
        disposables.add(restaurantRepository.getRestaurants()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { restaurantsSubject.onNext(LoadingState) }
            .subscribe({ restaurants -> restaurantsSubject.onNext(restaurantStateConverter.convert(restaurants)) },
                { restaurantsSubject.onNext(ErrorState) })  // potentially also want to log the error here
        )
    }
}
