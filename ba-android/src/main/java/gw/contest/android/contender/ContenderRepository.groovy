package gw.contest.android.contender

import android.database.Cursor
import gw.contest.android.core.Repository

interface ContenderRepository extends Repository {

    Cursor addContender(String contenderID, String contestId)

}