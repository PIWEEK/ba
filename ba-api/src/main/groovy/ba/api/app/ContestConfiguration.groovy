package ba.api.app

import gw.ast.Configuration

import io.dropwizard.db.DataSourceFactory

import javax.validation.Valid
import javax.validation.constraints.NotNull

@Configuration
class ContestConfiguration {

    @Valid
    @NotNull
    DataSourceFactory database = new DataSourceFactory()

    @Valid
    @NotNull
    Integer secondsToExpireURL

    @Valid
    @NotNull
    String imageBasePath

}
