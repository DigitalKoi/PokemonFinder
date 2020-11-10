package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.digitalkoi.commons.ui.base.BaseViewHolder
import com.digitalkoi.dynamicfeatures.pokemonlist.databinding.ListItemErrorBinding
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.PokemonListViewModel

/**
 * Class describes pokemons error view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class ErrorViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemErrorBinding>(
    binding = ListItemErrorBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel character list view model.
     */
    fun bind(viewModel: PokemonListViewModel) {
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
}
