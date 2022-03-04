package dev.nst.app.di

import dagger.Component
import dagger.hilt.android.internal.modules.ApplicationContextModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationContextModule::class
])
interface ApplicationComponent {
}