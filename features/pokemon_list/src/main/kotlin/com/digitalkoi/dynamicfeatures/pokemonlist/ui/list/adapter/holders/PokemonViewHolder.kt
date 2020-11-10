package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.digitalkoi.commons.ui.base.BaseViewHolder
import com.digitalkoi.dynamicfeatures.pokemonlist.databinding.ListItemPokemonBinding
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.PokemonListViewModel
import com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.model.PokemonItem

/**
 * Class describes character view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class PokemonViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemPokemonBinding>(
    binding = ListItemPokemonBinding.inflate(inflater)
) {

    /**
     * Bind view data binding variables.
     *
     * @param viewModel Pokemon list view model.
     * @param item Pokemon list item.
     */
    fun bind(viewModel: PokemonListViewModel, item: PokemonItem) {
        binding.viewModel = viewModel
        binding.pokemon = item
        binding.executePendingBindings()
    }
}
