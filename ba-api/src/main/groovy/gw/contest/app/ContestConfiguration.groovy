package gw.contest.app

import gw.ast.Configuration

import io.dropwizard.db.DataSourceFactory

import javax.validation.Valid
import javax.validation.constraints.NotNull

@Configuration
class ContestConfiguration {

    @Valid
    @NotNull
    DataSourceFactory database = new DataSourceFactory()

}
