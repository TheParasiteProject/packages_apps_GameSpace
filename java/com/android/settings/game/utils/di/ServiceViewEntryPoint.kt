package com.android.settings.game.utils.di

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.android.settings.game.data.SystemSettings
import com.android.settings.game.utils.GameModeUtils


@EntryPoint
@InstallIn(SingletonComponent::class)
interface ServiceViewEntryPoint {
    fun systemSettings(): SystemSettings
    fun gameModeUtils(): GameModeUtils
}
