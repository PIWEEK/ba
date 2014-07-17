package gw.contest.rep

interface ContenderRepository extends Repository {

    Map addContenderToContest(String contenderId, Long contestId)
    int countContendersByContest(Long contestId)

}
