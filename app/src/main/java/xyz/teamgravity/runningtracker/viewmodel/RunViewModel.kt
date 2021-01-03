package xyz.teamgravity.runningtracker.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel

class RunViewModel @ViewModelInject constructor(
    val repository: RunRepository
) : ViewModel() {
}