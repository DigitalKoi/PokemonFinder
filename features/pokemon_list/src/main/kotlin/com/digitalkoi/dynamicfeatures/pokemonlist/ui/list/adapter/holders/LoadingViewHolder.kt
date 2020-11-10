package com.digitalkoi.dynamicfeatures.pokemonlist.ui.list.adapter.holders

import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.digitalkoi.commons.ui.base.BaseViewHolder
import com.digitalkoi.dynamicfeatures.pokemonlist.databinding.ListItemLoadingBinding

/**
 * Class describes characters loading view and metadata about its place within the [RecyclerView].
 *
 * @see BaseViewHolder
 */
class LoadingViewHolder(
    inflater: LayoutInflater
) : BaseViewHolder<ListItemLoadingBinding>(
    binding = ListItemLoadingBinding.inflate(inflater)
)
