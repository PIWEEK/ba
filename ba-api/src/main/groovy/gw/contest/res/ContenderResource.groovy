package gw.contest.res

import gw.ast.Inject
import gw.ast.Resource

import gw.contest.rep.ContenderRepository

@Resource('/api/contender')
class ContenderResource {

    @Inject ContenderRepository contenderRepository

    Map 'POST/add'(Map data) {
        return contenderRepository
            .addContenderToContest(
                data.contenderId,
                data.contestId.toLong()
            )
    }

}
