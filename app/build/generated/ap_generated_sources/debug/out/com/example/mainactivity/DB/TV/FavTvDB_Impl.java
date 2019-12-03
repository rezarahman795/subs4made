package com.example.mainactivity.DB.TV;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class FavTvDB_Impl extends FavTvDB {
  private volatile FavTvDao _favTvDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `favourite_tv` (`id` INTEGER NOT NULL, `title` TEXT, `overview` TEXT, `vote_average` REAL NOT NULL, `poster_path` TEXT, `backdrop_path` TEXT, `release_date` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"7ce41f7e98b92f01add986c3a6deb5f2\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `favourite_tv`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsFavouriteTv = new HashMap<String, TableInfo.Column>(7);
        _columnsFavouriteTv.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsFavouriteTv.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsFavouriteTv.put("overview", new TableInfo.Column("overview", "TEXT", false, 0));
        _columnsFavouriteTv.put("vote_average", new TableInfo.Column("vote_average", "REAL", true, 0));
        _columnsFavouriteTv.put("poster_path", new TableInfo.Column("poster_path", "TEXT", false, 0));
        _columnsFavouriteTv.put("backdrop_path", new TableInfo.Column("backdrop_path", "TEXT", false, 0));
        _columnsFavouriteTv.put("release_date", new TableInfo.Column("release_date", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavouriteTv = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavouriteTv = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavouriteTv = new TableInfo("favourite_tv", _columnsFavouriteTv, _foreignKeysFavouriteTv, _indicesFavouriteTv);
        final TableInfo _existingFavouriteTv = TableInfo.read(_db, "favourite_tv");
        if (! _infoFavouriteTv.equals(_existingFavouriteTv)) {
          throw new IllegalStateException("Migration didn't properly handle favourite_tv(com.example.mainactivity.DB.TV.FavTV).\n"
                  + " Expected:\n" + _infoFavouriteTv + "\n"
                  + " Found:\n" + _existingFavouriteTv);
        }
      }
    }, "7ce41f7e98b92f01add986c3a6deb5f2", "70cea61d165197171eb85510aa90f762");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "favourite_tv");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `favourite_tv`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public FavTvDao favTvDao() {
    if (_favTvDao != null) {
      return _favTvDao;
    } else {
      synchronized(this) {
        if(_favTvDao == null) {
          _favTvDao = new FavTvDao_Impl(this);
        }
        return _favTvDao;
      }
    }
  }
}
