package ba.api.ser

import gw.ast.Inject
import com.google.common.collect.ImmutableMap
import ba.api.rep.ContestRepository

class ContestService {

    @Inject ContestRepository contestRepository
    @Inject ImageService imageService

    Map get(Long id) {
        return transformAndAddImage(contestRepository.get(id))
    }

    Map transformAndAddImage(Map row) {
        return new ImmutableMap.Builder()
            .putAll(row)
            .put('image', imageService.getImageURI(row.id))
            .build()
    }

    List<Map> list() {
        return contestRepository
            .list()
            .collect(this.&transformAndAddImage)
    }

    Map save(Map contest) {
        return transformAndAddImage(contestRepository.save(contest))
    }

}
