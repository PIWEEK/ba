package gw.contest.rep

interface Repository {

    Map get(Long id)
    List<Map> list()
    Map save(Map contest)

}
