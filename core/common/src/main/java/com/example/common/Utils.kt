package com.example.common

import androidx.compose.runtime.snapshots.SnapshotStateList

fun <T> SnapshotStateList<T>.swap(withContext: List<T>) {
    this.clear()
    this.addAll(withContext)
}
