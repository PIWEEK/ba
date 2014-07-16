package gw.contest.res

import gw.app.Module

class ResourcesModule extends Module {

    void configureModule() {
        bindResource().to(ContestResource)
    }

}
