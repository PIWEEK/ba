package ba.api.app

import gw.ast.ApplicationModule
import ba.api.res.ResourcesModule
import com.scoyo.commons.vfs.S3Util

@ApplicationModule
class ContestApplicationModule {

    void configureModule() {
        install(new ResourcesModule())

        S3Util.initS3Provider(
            System.getenv('S3_AWS_KEY'),
            System.getenv('S3_AWS_PASSWD')
        )
    }

}
