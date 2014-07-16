package gw.contest.res

import gw.app.Module

import gw.contest.rep.ContestRepository
import gw.contest.rep.ContestRepositoryImpl

class ResourcesModule extends Module {

    void configureModule() {
        bind(ContestRepository).to(ContestRepositoryImpl)
        bindResource().to(ContestResource)
    }

}
