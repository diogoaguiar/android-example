package pt.dg7.android.example.repositories

import dagger.Component
import pt.dg7.android.example.network.ApiModule
import pt.dg7.android.example.viewmodels.RecyclerViewViewModel
import javax.inject.Singleton

@Component(modules = [ ApiModule::class ])
@Singleton
interface RepositoryComponent {
    fun inject(viewModel: RecyclerViewViewModel)
}