package ba.api.ser

import gw.ast.Inject
import io.dropwizard.Configuration
import org.apache.commons.vfs2.VFS

class ImageService {

    @Inject Configuration configuration

    String getImageURI(Long imageId) {
        return VFS
            .manager
            .resolveFile(
                URI.create(configuration.imageBasePath)
                   .resolve("${imageId}.png")
                   .toString())
            .getSignedUrl(configuration.secondsToExpireURL)
    }

}
