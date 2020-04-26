package com.yichenwu.doordashlite.model.ui

import com.yichenwu.doordashlite.model.data.Restaurant

sealed class State

object LoadingState : State()

data class SuccessState(val restaurants: List<Restaurant>) : State()

object EmptyState : State()

object ErrorState : State()
