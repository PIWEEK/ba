package gw.contest.res

import gw.app.Module

import gw.contest.rep.ContestRepository
import gw.contest.rep.ContenderRepository
import gw.contest.rep.ContestRepositoryImpl
import gw.contest.rep.ContenderRepositoryInMemory

class ResourcesModule extends Module {

    void configureModule() {

        bind(ContestRepository).to(ContestRepositoryImpl)
        bind(ContenderRepository).to(ContenderRepositoryInMemory)

        bindResource().to(ContestResource)
        bindResource().to(ContenderResource)
    }

}
