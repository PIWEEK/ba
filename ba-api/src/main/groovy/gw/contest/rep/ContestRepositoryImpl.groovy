package gw.contest.rep

class ContestRepositoryImpl extends JdbiRepository implements ContestRepository {

    Closure<Map> retrieveContestWithId = { Long id ->
        return { handle ->
            handle
                .createQuery('select id, name, description from contest where id=:id')
                .bind('id', id)
                .map(new gw.jdbi.MapMapper())
                .first()
        }
    }

    Map get(Long id) {
        return jdbi.withHandle(retrieveContestWithId(id))
    }

    List<Map> list() {
        return jdbi
            .withHandle { handle ->
                handle
                    .createQuery('select id, name, description from contest')
                    .list()
            }
    }

    Map save(Map contest) {
        return jdbi
            .inTransaction { handle, txStatus ->
                def savedContest =
                    handle
                        .createStatement('insert into contest (name, description) values (:name, :description)')
                        .bind('name', contest.name)
                        .bind('description', contest.description)
                        .executeAndReturnGeneratedKeys(new gw.jdbi.MapMapper())
                        .first()

                return retrieveContestWithId(savedContest.id).doCall(handle)
            }
    }

}
