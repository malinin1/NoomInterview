package com.example.noominterview.application.dagger

import android.app.Application
import com.example.noominterview.application.ui.MainActivity
import com.example.noominterview.foodsearch.dagger.FoodSearchComponent
import dagger.BindsInstance
import dagger.Component
import javax.inject.Scope
import javax.inject.Singleton


@Scope
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ActivityScope
@Singleton
@Component(modules = [ApplicationModule::class, NetworkModule::class, SubcomponentsModule::class])
interface ApplicationComponent {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun bindApplication(application: Application): Builder

        fun build(): ApplicationComponent
    }

    fun foodSearchComponent(): FoodSearchComponent.Builder
    fun inject(activity: MainActivity)
}