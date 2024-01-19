package com.example.noominterview.application.dagger

import android.app.Application
import com.example.noominterview.application.MainActivity
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun inject(activity: MainActivity)
}