package ba.api.ser

import gw.ast.Inject
import ba.api.app.ContestConfiguration
import org.apache.commons.vfs2.VFS

class ImageService {

    @Inject ContestConfiguration configuration

    String getImageURI(String imageId) {
        return VFS
            .manager
            .resolveFile(
                URI.create(configuration.imageBasePath)
                   .resolve(imageId)
                   .toString())
            .getSignedUrl(configuration.secondsToExpireURL)
    }

}
