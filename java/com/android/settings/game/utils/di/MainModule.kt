/*
 * Copyright (C) 2021 Chaldeaprjkt
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.settings.game.utils.di

import android.content.Context
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import com.android.settings.game.data.SystemSettings
import com.android.settings.game.utils.GameModeUtils
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {
    @Provides
    fun provideBaseGson() = Gson()

    @Provides
    @Singleton
    fun provideGameModeUtils(@ApplicationContext context: Context) = GameModeUtils(context)

    @Provides
    @Singleton
    fun provideSystemSettings(@ApplicationContext context: Context, gameModeUtils: GameModeUtils) =
        SystemSettings(context, gameModeUtils)
}
