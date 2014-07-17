package ba.api.res

import gw.app.Module

import ba.api.rep.ContestRepository
import ba.api.rep.ContenderRepository
import ba.api.rep.ContestRepositoryImpl
import ba.api.rep.ContenderRepositoryInMemory

class ResourcesModule extends Module {

    void configureModule() {

        bind(ContestRepository).to(ContestRepositoryImpl)
        bind(ContenderRepository).to(ContenderRepositoryInMemory)

        bindResource().to(ContestResource)
        bindResource().to(ContenderResource)
    }

}
