package com.example.musicplayer.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [
        Playlist::class,
        PlaylistSongCrossRef::class,
        FavoriteSong::class,
        PlayHistory::class
    ],
    version = 1,
    exportSchema = false
)
abstract class MusicDatabase : RoomDatabase() {
    abstract fun playlistDao(): PlaylistDao
}
