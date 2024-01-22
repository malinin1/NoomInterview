package com.example.noominterview.util


import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

// A modification of MutableLiveData which ensures the value is only delivered once
// Useful for handling user actions such as navigation, where re-delivering the value can lead
// to unwanted behaviors
// Allows for Fragment/Activity communication via a shared Activity-level ViewModel for handling
// user interactons at the Activity level
class SingleLiveEvent<T>: MutableLiveData<T>() {

    private val mPending = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        if (hasActiveObservers()) {
            Log.w(TAG, "Multiple observers registered but only one will be notified of changes.")
        }
        // Observe the internal MutableLiveData
        super.observe(owner, object : Observer<T> {
            override fun onChanged(value: T) {
                if (mPending.compareAndSet(true, false)) {
                    observer.onChanged(value)
                }
            }
        })
    }

    @MainThread
    override fun setValue(t: T?) {
        mPending.set(true)
        super.setValue(t)
    }
    /**
     * Used for cases where T is Void, to make calls cleaner.
     */
    @MainThread
    fun call() {
        setValue(null)
    }

    companion object {
        private val TAG = "SingleLiveEvent"
    }
}


