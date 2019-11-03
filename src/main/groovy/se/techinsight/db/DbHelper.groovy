package se.techinsight.db

import groovy.sql.Sql
import groovy.transform.Canonical


class DbHelper {

    private Sql sql

    DbHelper(Map credentials) {
        sql = Sql.newInstance(
                url: "jdbc:postgresql://${credentials?.host ?: 'localhost'}:${credentials?.port ?: 5432}/${credentials?.db ?: 'postgres'}",
                user: credentials?.user,
                password: credentials?.password,
                driver: 'org.postgresql.Driver'
        )
    }

    Map getDbVersion() {
        def row = sql.firstRow('select version()')
        ["version": row.version]
    }

    List<User> getAllUsers() {
        String txt = 'SELECT * FROM pg_user;'
        sql.rows(txt).collect { row ->
            new User(id: row.usesysid, name: row.usename, isAdmin: row.usesuper)
        }
    }
//
//    Product findProductById(int id) {
//        String txt = 'select * from product where id=?'
//        def row = sql.firstRow(txt, [id])
//
//        row ? new Product(id: row.id, name: row.name, price: row.price) : null
//    }

    @Canonical
    class User {
        int id
        String name
        boolean isAdmin
    }

}
