package ba.api.rep

interface CrudRepository extends Repository {

    Map get(Long id)
    List<Map> list()
    Map save(Map contest)

}
