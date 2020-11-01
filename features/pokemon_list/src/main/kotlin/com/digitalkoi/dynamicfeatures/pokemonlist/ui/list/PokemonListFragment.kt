/*
 * Copyright 2019 vmadalin.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.paging.PagedList
import com.digitalkoi.android.SampleApp.Companion.coreComponent
import com.digitalkoi.commons.ui.base.BaseFragment
import com.digitalkoi.commons.ui.extensions.gridLayoutManager
import com.digitalkoi.commons.ui.extensions.observe
import com.digitalkoi.dynamicfeatures.characterslist.R
import com.digitalkoi.dynamicfeatures.characterslist.databinding.FragmentPokemonListBinding
import com.digitalkoi.dynamicfeatures.pokemonlist.R
import com.digitalkoi.dynamicfeatures.pokemonlist.databinding.FragmentCharactersListBinding
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.adapter.PokemonListAdapter
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.adapter.PokemonListAdapterState
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.di.PokemonListModule
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.di.DaggerCharactersListComponent
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model.PokemonItem
import com.digitalkoi.pokemonfinder.PokemonApp.Companion.coreComponent
import javax.inject.Inject

/**
 * View listing the all marvel characters with option to display the detail view.
 *
 * @see BaseFragment
 */
class PokemonListFragment :
    BaseFragment<FragmentPokemonListBinding, PokemonListViewModel>(
        layoutId = R.layout.fragment_pokemon_list
    ) {

    @Inject
    lateinit var viewAdapter: PokemonListAdapter

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
        observe(viewModel.data, ::onViewDataChange)
        observe(viewModel.event, ::onViewEvent)
    }

    /**
     * Initialize dagger injection dependency graph.
     */
    override fun onInitDependencyInjection() {
        DaggerCharactersListComponent
            .builder()
            .coreComponent(coreComponent(requireContext()))
            .charactersListModule(PokemonListModule(this))
            .build()
            .inject(this)
    }

    /**
     * Initialize view data binding variables.
     */
    override fun onInitDataBinding() {
        viewBinding.viewModel = viewModel
        viewBinding.includeList.charactersList.apply {
            adapter = viewAdapter
            gridLayoutManager?.spanSizeLookup = viewAdapter.getSpanSizeLookup()
        }
    }

    // region Private observers methods

    /**
     * Observer view data change on [PokemonListViewModel].
     *
     * @param viewData Paged list of characters.
     */
    private fun onViewDataChange(viewData: PagedList<PokemonItem>) {
        viewAdapter.submitList(viewData)
    }

    /**
     * Observer view state change on [PokemonListViewModel].
     *
     * @param viewState State of pokemon list.
     */
    private fun onViewStateChange(viewState: PokemonListViewState) {
        when (viewState) {
            is PokemonListViewState.Loaded ->
                viewAdapter.submitState(PokemonListAdapterState.Added)
            is PokemonListViewState.AddLoading ->
                viewAdapter.submitState(PokemonListAdapterState.AddLoading)
            is PokemonListViewState.AddError ->
                viewAdapter.submitState(PokemonListAdapterState.AddError)
            is PokemonListViewState.NoMoreElements ->
                viewAdapter.submitState(PokemonListAdapterState.NoMore)
        }
    }

    /**
     * Observer view event change on [PokemonListViewModel].
     *
     * @param viewEvent Event on pokemon list.
     */
    private fun onViewEvent(viewEvent: PokemonListViewEvent) {
        when (viewEvent) {
            is PokemonListViewEvent.OpenCharacterDetail ->
                findNavController().navigate(
                    PokemonListFragmentDirections
                        .actionPokemonListFragmentToCharacterDetailFragment(viewEvent.id)
                )
        }
    }

    // endregion
}
