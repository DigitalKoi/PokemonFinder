package com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.digitalkoi.commons.ui.base.BaseFragment
import com.digitalkoi.commons.ui.extensions.observe
import com.digitalkoi.commons.views.ProgressBarDialog
import com.digitalkoi.dynamicfeatures.characterslist.R
import com.digitalkoi.dynamicfeatures.characterslist.databinding.FragmentPokemonDetailBinding
import com.digitalkoi.dynamicfeatures.pokemonlist.databinding.FragmentCharacterDetailBinding
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.di.PokemonDetailModule
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.detail.di.DaggerCharacterDetailComponent
import com.digitalkoi.pokemonfinder.PokemonApp.Companion.coreComponent
import javax.inject.Inject

/**
 * View detail for selected character, displaying extra info and with option to add it to favorite.
 *
 * @see BaseFragment
 */
class PokemonDetailFragment :
    BaseFragment<FragmentPokemonDetailBinding, PokemonDetailViewModel>(
        layoutId = R.layout.fragment_pokemon_detail
    ) {

    @Inject
    lateinit var progressDialog: ProgressBarDialog

    private val args: PokemonDetailFragmentArgs by navArgs()

    /**
     * Called to have the fragment instantiate its user interface view.
     *
     * @param view The view returned by onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     * @see BaseFragment.onViewCreated
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.state, ::onViewStateChange)
        viewModel.loadPokemonDetail(args.characterId)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerPokemonDetailComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .characterDetailModule(PokemonDetailModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
    }

    // region Private observers methods

    /**
     * Observer view state change on [PokemonDetailViewState].
     *
     * @param viewState State of character detail.
     */
    private fun onViewStateChange(viewState: PokemonDetailViewState) {
        when (viewState) {
            is PokemonDetailViewState.Loading ->
                progressDialog.show(R.string.pokemon_detail_dialog_loading_text)
            is PokemonDetailViewState.Error ->
                progressDialog.dismissWithErrorMessage(R.string.pokemon_detail_dialog_error_text)
            is PokemonDetailViewState.Dismiss ->
                findNavController().navigateUp()
            else -> progressDialog.dismiss()
        }
    }

    // endregion
}
