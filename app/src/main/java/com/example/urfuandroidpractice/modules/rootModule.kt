import com.example.urfuandroidpractice.listWithDetails.data.repository.AnimeRepository
import com.example.urfuandroidpractice.listWithDetails.domain.repository.IAnimeRepository
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.AnimeDetailsViewModel
import com.example.urfuandroidpractice.listWithDetails.presentation.viewModel.AnimeListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val rootModule = module {
    single<IAnimeRepository> { AnimeRepository() }

    viewModel { AnimeListViewModel(get(), it.get()) }
    viewModel { AnimeDetailsViewModel(get(), it.get(), it.get()) }
}