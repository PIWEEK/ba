package ba.api.app

import gw.ast.Application

@Application(
    module= ContestApplicationModule,
    configuration = ContestConfiguration
)
class ContestApplication {

    static void main(String[] args) {
        new ContestApplication().run(args)
    }

}

