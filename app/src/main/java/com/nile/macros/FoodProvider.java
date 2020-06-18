package com.nile.macros;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FoodProvider extends ContentProvider {

    // Perhaps one database for food items and another for saved food items?

    // Database columns
    private static final String COLUMN_FOODID = "_id";
    private static final String COLUMN_DAY = "day";
    public static final String COLUMN_FOOD_NAME = "name";
    public static final String COLUMN_PROTEIN = "protein";
    public static final String COLUMN_CARBS = "carbs";
    public static final String COLUMN_FAT = "fat";

    // Database Related Constants
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "data";
    private static final String DATABASE_TABLE = "macros";

    // Content Provider Uri and Authority
    public static final String AUTHORITY = "com.nile.macros.FoodProvider";
    public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/food");

    // MIME types used for listing tasks or looking up a single task
    private static final String ITEMS_MIME_TYPE = ContentResolver.CURSOR_DIR_BASE_TYPE
            + "/vnd.com.nile.food.items";
    private static final String ITEM_MIME_TYPE = ContentResolver.CURSOR_ITEM_BASE_TYPE
            + "/vnd.com.nile.food.item";

    // UriMatcher stuff
    private static final int LIST_FOOD = 0;
    private static final int ITEM_FOOD = 1;
    private static final UriMatcher URI_MATCHER = buildUriMatcher();

    // The database itself
    SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        // Grab a connection to our database
        db = new DatabaseHelper(getContext()).getWritableDatabase();
        return true;
    }

    // Builds a UriMatcher for search suggestion and shortcut refresh queries
    private static UriMatcher buildUriMatcher() {
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(AUTHORITY, "food", LIST_FOOD);
        matcher.addURI(AUTHORITY, "food/#", ITEM_FOOD);
        return matcher;
    }

    // This method is required in order to query the supported types
    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        switch (URI_MATCHER.match(uri)) {
            case LIST_FOOD:
                return ITEMS_MIME_TYPE;
            case ITEM_FOOD:
                return ITEM_MIME_TYPE;
            default:
                throw new IllegalArgumentException("Unknown Uri: " + uri);
        }
    }

    // Create
    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    // A helper class which knows how to create and update the database;
    protected static class DatabaseHelper extends SQLiteOpenHelper {

        static final String DATABASE_CREATE =
                "create table " + DATABASE_TABLE + " (" +
                        COLUMN_FOODID + " integer primary key autoincrement, " +
                        COLUMN_DAY + " integer not null, " +
                        COLUMN_FOOD_NAME + " text not null, " +
                        COLUMN_PROTEIN + " float not null, " +
                        COLUMN_CARBS + " float not null, " +
                        COLUMN_FAT + " float not null)";

        DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DATABASE_CREATE);  // execute SQL command
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            throw new UnsupportedOperationException();
        }
    }

}
