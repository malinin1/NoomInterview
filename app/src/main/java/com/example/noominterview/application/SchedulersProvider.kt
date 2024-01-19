package com.example.noominterview.application

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SchedulersProvider @Inject constructor() {

    fun getComputationScheduler(): Scheduler {
        return Schedulers.computation()
    }

    fun getIOScheduler(): Scheduler {
        return Schedulers.io()
    }

    fun getTrampolineScheduler(): Scheduler {
        return Schedulers.trampoline()
    }

    fun getMainThreadScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}