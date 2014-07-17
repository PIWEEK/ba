package ba.api.res

import gw.ast.Inject
import gw.ast.Resource

import ba.api.ser.ContestService

@Resource('/api/contest')
class ContestResource {

    @Inject ContestService contestService

    def 'GET/'() {
        return contestService.list()
    }

    def 'GET/{id}'(Long id) {
        return contestService.get(id)
    }

    def 'POST/'(Map contest) {
        return contestService.save(contest)
    }

}
