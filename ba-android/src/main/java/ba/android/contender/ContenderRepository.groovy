package ba.android.contender

import android.database.Cursor
import ba.android.core.Repository

interface ContenderRepository extends Repository {

    Cursor addContender(String contenderID, String contestId)

}