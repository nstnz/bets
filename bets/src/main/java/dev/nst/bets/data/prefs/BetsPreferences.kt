package dev.nst.bets.data.prefs

import dev.nst.core.data.prefs.SharedPreferencesProvider
import javax.inject.Inject

private const val PREFS_NAME = "BETS_PREFS"
private const val LAST_UPDATE_TIMESTAMP = "LAST_UPDATE_TIMESTAMP"
private const val LAST_SCREEN = "LAST_SCREEN"

class BetsPreferences @Inject constructor(
    private val provider: SharedPreferencesProvider
) {
    private val prefs by lazy { provider.getPrefs(PREFS_NAME) }

    fun getLastUpdatedTimestamp(): Long = prefs.getLong(LAST_UPDATE_TIMESTAMP, 0L)

    fun setLastUpdatedTimestamp(lastTs: Long) = with(prefs.edit()) {
        putLong(LAST_UPDATE_TIMESTAMP, lastTs)
        apply()
    }

    fun getLastScreen(): String = prefs.getString(LAST_SCREEN, "").orEmpty()

    fun setLastScreen(lastScreen: String) = with(prefs.edit()) {
        putString(LAST_SCREEN, lastScreen)
        apply()
    }
}