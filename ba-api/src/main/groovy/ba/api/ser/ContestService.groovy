package ba.api.ser

import gw.ast.Inject
import ba.api.rep.ContestRepository

class ContestService {

    @Inject ContestRepository contestRepository
    @Inject ImageService imageService

    Map get(Long id) {
        return contestRepository.get(id)
    }

    List<Map> list() {
        return contestRepository.list()
    }

    Map save(Map contest) {
        return contestRepository.save(contest)
    }

}
