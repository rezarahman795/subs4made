package com.example.mainactivity.DB.movie;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class FavouriteDao_Impl implements FavouriteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfFavourite;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfFavourite;

  public FavouriteDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFavourite = new EntityInsertionAdapter<Favourite>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `favourite`(`id`,`title`,`overview`,`vote_average`,`poster_path`,`backdrop_path`,`release_date`) VALUES (?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Favourite value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getOverview() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getOverview());
        }
        stmt.bindDouble(4, value.getVoteAverage());
        if (value.getPosterPath() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getPosterPath());
        }
        if (value.getBackdropPath() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getBackdropPath());
        }
        if (value.getReleaseDate() == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.getReleaseDate());
        }
      }
    };
    this.__deletionAdapterOfFavourite = new EntityDeletionOrUpdateAdapter<Favourite>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `favourite` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Favourite value) {
        stmt.bindLong(1, value.getId());
      }
    };
  }

  @Override
  public void addDataFav(Favourite favourite) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfFavourite.insert(favourite);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(Favourite favourite) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfFavourite.handle(favourite);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Favourite> getDataFav() {
    final String _sql = "SELECT * FROM favourite";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfId = _cursor.getColumnIndexOrThrow("id");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfOverview = _cursor.getColumnIndexOrThrow("overview");
      final int _cursorIndexOfVoteAverage = _cursor.getColumnIndexOrThrow("vote_average");
      final int _cursorIndexOfPosterPath = _cursor.getColumnIndexOrThrow("poster_path");
      final int _cursorIndexOfBackdropPath = _cursor.getColumnIndexOrThrow("backdrop_path");
      final int _cursorIndexOfReleaseDate = _cursor.getColumnIndexOrThrow("release_date");
      final List<Favourite> _result = new ArrayList<Favourite>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Favourite _item;
        _item = new Favourite();
        final int _tmpId;
        _tmpId = _cursor.getInt(_cursorIndexOfId);
        _item.setId(_tmpId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final String _tmpOverview;
        _tmpOverview = _cursor.getString(_cursorIndexOfOverview);
        _item.setOverview(_tmpOverview);
        final double _tmpVoteAverage;
        _tmpVoteAverage = _cursor.getDouble(_cursorIndexOfVoteAverage);
        _item.setVoteAverage(_tmpVoteAverage);
        final String _tmpPosterPath;
        _tmpPosterPath = _cursor.getString(_cursorIndexOfPosterPath);
        _item.setPosterPath(_tmpPosterPath);
        final String _tmpBackdropPath;
        _tmpBackdropPath = _cursor.getString(_cursorIndexOfBackdropPath);
        _item.setBackdropPath(_tmpBackdropPath);
        final String _tmpReleaseDate;
        _tmpReleaseDate = _cursor.getString(_cursorIndexOfReleaseDate);
        _item.setReleaseDate(_tmpReleaseDate);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public int x_Favourite(int id) {
    final String _sql = "SELECT EXISTS (SELECT 1 FROM favourite WHERE id=?)";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _result;
      if(_cursor.moveToFirst()) {
        _result = _cursor.getInt(0);
      } else {
        _result = 0;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
