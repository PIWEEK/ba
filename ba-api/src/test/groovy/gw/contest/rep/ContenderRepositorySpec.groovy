package gw.contest.rep

import spock.lang.Specification

class ContenderRepositorySpec extends Specification {

    void 'A given user should be able to join any contest'() {
        given: 'a user id and a contest'
            def contender1 = "234kjhlkjh1"
            def contender2 = "234kjhlkjh2"
            def contest= 12L
        and: 'creating a new instance of the repository'
            def contenderRepository = new ContenderRepositoryInMemory()
        when: 'adding a new contender'
            def contenderInfo1 = contenderRepository.addContenderToContest(contender1, contest)
            def contenderInfo2 = contenderRepository.addContenderToContest(contender2, contest)
        and: 'making sure there is one contender for the given contest'
            def contendersByContest =
                contenderRepository.countContendersByContest(contest)
        then: 'service should return the contest-user id'
            with(contenderInfo1) {
                contestId
                contenderId
            }
        and: 'there should be only one contender'
            contendersByContest == 2
    }

}
