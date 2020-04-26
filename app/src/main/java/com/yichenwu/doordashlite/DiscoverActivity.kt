package com.yichenwu.doordashlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout.VERTICAL
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yichenwu.doordashlite.model.ui.*
import com.yichenwu.doordashlite.ui.RestaurantListAdapter
import com.yichenwu.doordashlite.viewmodel.RestaurantViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_discover.*

class DiscoverActivity : AppCompatActivity() {
    private val disposables = CompositeDisposable()
    private lateinit var restaurantViewModel: RestaurantViewModel
    private lateinit var listLayoutManager: RecyclerView.LayoutManager
    private lateinit var listAdapter: RestaurantListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_discover)
        setupRestaurantList()
    }

    override fun onDestroy() {
        disposables.dispose()
        super.onDestroy()
    }

    private fun setupRestaurantList() {
        listLayoutManager = LinearLayoutManager(this)
        listAdapter = RestaurantListAdapter()
        restaurantList.layoutManager = listLayoutManager
        restaurantList.adapter = listAdapter
        restaurantList.setHasFixedSize(true)
        restaurantList.addItemDecoration(DividerItemDecoration(this, VERTICAL))

        restaurantViewModel = ViewModelProvider(this).get(RestaurantViewModel::class.java)
        disposables.add(
            restaurantViewModel.observeRestaurants()
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(this::updateScreen)
        )
    }

    private fun updateScreen(state: State) {
        when (state) {
            is LoadingState -> showLoadingScreen()
            is SuccessState -> showRestaurantList(state)
            is EmptyState -> showEmptyScreen()
            ErrorState -> showErrorDialog()
        }
    }

    // show a loading screen when waiting for API call
    private fun showLoadingScreen() {
        loadingScreen.visibility = View.VISIBLE
        restaurantList.visibility = View.GONE
        emptyScreen.visibility = View.GONE
    }

    // when there is a lot of results to show
    private fun showRestaurantList(state: SuccessState) {
        listAdapter.restaurants = state.restaurants.toMutableList()
        restaurantList.visibility = View.VISIBLE
        loadingScreen.visibility = View.GONE
    }

    // when the result is empty or user cancel the action after seeing error
    private fun showEmptyScreen() {
        emptyScreen.visibility = View.VISIBLE
        loadingScreen.visibility = View.GONE
    }

    // when api call to fetch restaurants fails, pop up a dialog for user to retry or cancel
    private fun showErrorDialog() {
        loadingScreen.visibility = View.GONE
        AlertDialog.Builder(this)
            .setMessage(R.string.error_message)
            .setPositiveButton(R.string.retry) { dialog, _ ->
                run {
                    dialog.cancel()
                    restaurantViewModel.fetchRestaurants()
                }
            }
            .setNegativeButton(R.string.cancel) { dialog, _ ->
                run {
                    dialog.cancel()
                    showEmptyScreen()
                }
            }
            .setCancelable(false)
            .show()
    }
}
