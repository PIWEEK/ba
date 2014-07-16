package gw.contest.app

import gw.ast.ApplicationModule
import gw.contest.res.ResourcesModule

@ApplicationModule
class ContestApplicationModule {

    void configureModule() {
        install(new ResourcesModule())
    }

}
