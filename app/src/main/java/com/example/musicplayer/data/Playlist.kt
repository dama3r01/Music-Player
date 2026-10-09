package com.example.musicplayer.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo
import androidx.room.Index

@Entity(tableName = "playlists")
data class Playlist(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "playlist_songs",
    primaryKeys = ["playlistId", "songId"],
    indices = [Index("songId")]
)
data class PlaylistSongCrossRef(
    val playlistId: Long,
    val songId: String
)

@Entity(tableName = "history")
data class PlayHistory(
    @PrimaryKey
    val songId: String,
    val lastPlayed: Long = System.currentTimeMillis(),
    val playCount: Int = 1
)

@Entity(tableName = "favorites")
data class FavoriteSong(
    @PrimaryKey
    val songId: String,
    val addedAt: Long = System.currentTimeMillis()
)
