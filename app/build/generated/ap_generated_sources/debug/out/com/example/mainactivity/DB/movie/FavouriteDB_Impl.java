package com.example.mainactivity.DB.movie;

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
public final class FavouriteDB_Impl extends FavouriteDB {
  private volatile FavouriteDao _favouriteDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `favourite` (`id` INTEGER NOT NULL, `title` TEXT, `overview` TEXT, `vote_average` REAL NOT NULL, `poster_path` TEXT, `backdrop_path` TEXT, `release_date` TEXT, PRIMARY KEY(`id`))");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"e370671f26adda07ed3ac974ef29e24b\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `favourite`");
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
        final HashMap<String, TableInfo.Column> _columnsFavourite = new HashMap<String, TableInfo.Column>(7);
        _columnsFavourite.put("id", new TableInfo.Column("id", "INTEGER", true, 1));
        _columnsFavourite.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsFavourite.put("overview", new TableInfo.Column("overview", "TEXT", false, 0));
        _columnsFavourite.put("vote_average", new TableInfo.Column("vote_average", "REAL", true, 0));
        _columnsFavourite.put("poster_path", new TableInfo.Column("poster_path", "TEXT", false, 0));
        _columnsFavourite.put("backdrop_path", new TableInfo.Column("backdrop_path", "TEXT", false, 0));
        _columnsFavourite.put("release_date", new TableInfo.Column("release_date", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysFavourite = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesFavourite = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoFavourite = new TableInfo("favourite", _columnsFavourite, _foreignKeysFavourite, _indicesFavourite);
        final TableInfo _existingFavourite = TableInfo.read(_db, "favourite");
        if (! _infoFavourite.equals(_existingFavourite)) {
          throw new IllegalStateException("Migration didn't properly handle favourite(com.example.mainactivity.DB.movie.Favourite).\n"
                  + " Expected:\n" + _infoFavourite + "\n"
                  + " Found:\n" + _existingFavourite);
        }
      }
    }, "e370671f26adda07ed3ac974ef29e24b", "9e590fe9fec46ada89de7aa64bf11da8");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "favourite");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `favourite`");
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
  public FavouriteDao favouriteDao() {
    if (_favouriteDao != null) {
      return _favouriteDao;
    } else {
      synchronized(this) {
        if(_favouriteDao == null) {
          _favouriteDao = new FavouriteDao_Impl(this);
        }
        return _favouriteDao;
      }
    }
  }
}
