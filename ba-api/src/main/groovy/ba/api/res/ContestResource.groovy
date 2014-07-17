package ba.api.res

import gw.ast.Inject
import gw.ast.Resource

import ba.api.rep.ContestRepository

@Resource('/api/contest')
class ContestResource {

    @Inject ContestRepository contestRepository

    def 'GET/'() {
        return contestRepository.list()
    }

    def 'GET/{id}'(Long id) {
        return contestRepository.get(id)
    }

    def 'POST/'(Map contest) {
        return contestRepository.save(contest)
    }

}
