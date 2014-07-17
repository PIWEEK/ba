package gw.contest.rep

class ContenderRepositoryInMemory implements ContenderRepository {

    def contenderContest = []

    Map addContenderToContest(String contenderId, Long contestId) {
        def entry = [contestId: contestId, contenderId: contenderId]
        contenderContest.add(entry)

        return entry
    }

    int countContendersByContest(Long contestId) {
        return contenderContest.count { it.contestId == contestId }
    }

}
