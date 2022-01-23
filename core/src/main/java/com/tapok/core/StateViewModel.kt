package com.tapok.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class StateViewModel<S : State, A : Action, E : Event> : ViewModel() {

    init {
        subscribeEvents()
    }

    private val initialState: S by lazy { createInitialState() }
    protected abstract fun createInitialState(): S

    private val _uiState: MutableStateFlow<S> = MutableStateFlow(initialState)
    val uiState: StateFlow<S> get() = _uiState.asStateFlow()

    val currentState get() = _uiState.value

    private val _action: Channel<A> = Channel()
    val action: Flow<A> = _action.receiveAsFlow()

    private val event: MutableSharedFlow<E> = MutableSharedFlow()

    fun setEvent(newEvent: E) {
        viewModelScope.launch { event.emit(newEvent) }
    }

    protected fun setState(state: S) {
        _uiState.value = state
    }

    protected fun setAction(action: () -> A) {
        viewModelScope.launch { _action.send(action()) }
    }

    private fun subscribeEvents() {
        viewModelScope.launch { event.collect { handleEvent(it) } }
    }

    protected abstract fun handleEvent(event: E)
}

interface State

interface Action

interface Event