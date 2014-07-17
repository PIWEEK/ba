package ba.api.app

import gw.ast.ApplicationModule
import ba.api.res.ResourcesModule

@ApplicationModule
class ContestApplicationModule {

    void configureModule() {
        install(new ResourcesModule())
    }

}
