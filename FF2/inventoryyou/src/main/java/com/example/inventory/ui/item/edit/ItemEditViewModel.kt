/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.inventory.ui.item.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.ItemsRepository
import com.example.inventory.ui.item.entry.ItemEntryViewModel
import com.example.inventory.ui.item.entry.toItem
import com.example.inventory.ui.item.entry.toItemUiState
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

/**
 * ViewModel to retrieve and update an item from the [ItemsRepository]'s data source.
 */
class ItemEditViewModel(
    savedStateHandle: SavedStateHandle,
    itemsRepository: ItemsRepository,
) : ItemEntryViewModel(itemsRepository) {

    private val itemId: Int = checkNotNull(savedStateHandle[ItemEditDestination.itemIdArg])

    init {
        viewModelScope.launch {
            itemUiState = itemsRepository.getItemStream(itemId)
                .filterNotNull()
                .first()
                .toItemUiState(true)
        }
    }

    override suspend fun saveItem() {
        if (validateInput(itemUiState.itemDetails)) {
            itemsRepository.updateItem(itemUiState.itemDetails.toItem())
        }
    }
}


