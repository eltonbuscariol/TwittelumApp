package br.com.caelum.twittelumapp.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration

object Migration1Para2 : Migration(1,2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        val sql = "alter table Tweet add column foto text"
        database.execSQL(sql)
    }

}
