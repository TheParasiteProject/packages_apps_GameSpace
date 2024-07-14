/*
 * Copyright (C) 2021 Chaldeaprjkt
 *               2022 crDroid Android Project
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
package com.android.settings.game.data

import android.content.Context
import android.os.UserHandle
import android.provider.Settings
import com.android.settings.game.utils.GameModeUtils
import javax.inject.Inject

class SystemSettings @Inject constructor(
    context: Context,
    private val gameModeUtils: GameModeUtils
) {

    private val resolver = context.contentResolver

    var userGames
        get() =
            Settings.System.getStringForUser(
                resolver, Settings.System.GAMESPACE_GAME_LIST,
                UserHandle.USER_CURRENT
            )
                ?.split(";")
                ?.toList()?.filter { it.isNotEmpty() }
                ?.map { UserGame.fromSettings(it) } ?: emptyList()
        set(games) {
            Settings.System.putStringForUser(
                resolver,
                Settings.System.GAMESPACE_GAME_LIST,
                if (games.isEmpty()) "" else
                    games.joinToString(";") { it.toString() },
                UserHandle.USER_CURRENT
            )
            gameModeUtils.setupBatteryMode(games.isNotEmpty())
        }

    private fun Boolean.toInt() = if (this) 1 else 0
}
