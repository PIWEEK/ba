package gw.contest.android.core

import android.database.Cursor;

/**
 */
public interface Repository {

    Cursor list()
    Cursor get(Long id)


}
