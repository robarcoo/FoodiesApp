package com.example.utils

import androidx.compose.runtime.snapshots.SnapshotStateList

fun <T> SnapshotStateList<T>.swap(data: List<T>) {
    this.clear()
    this.addAll(data)
}