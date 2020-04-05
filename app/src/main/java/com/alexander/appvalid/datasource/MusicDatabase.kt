package com.alexander.appvalid.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alexander.appvalid.datasource.dao.ArtistsDao
import com.alexander.appvalid.datasource.dao.TracksDao
import com.alexander.appvalid.models.Artist
import com.alexander.appvalid.models.Track

@Database(entities = [Artist::class, Track::class], version = 1, exportSchema = false)
abstract class MusicDatabase : RoomDatabase() {

    companion object {
        @Volatile
        private var INSTANCE: MusicDatabase? = null

        fun getDatabase(context: Context): MusicDatabase {
            val tempInstance =
                INSTANCE
            if (tempInstance != null)
                return tempInstance

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MusicDatabase::class.java,
                    "movie_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }
    }

    abstract fun artistDao(): ArtistsDao

    abstract fun trackDao(): TracksDao

}