package se.techinsight.db

import groovy.sql.Sql

@Singleton(lazy = true)
class DbHelperSimple {

    Sql sql = Sql.newInstance(
            url: 'jdbc:postgresql://192.168.178.100:5432/sonar',
            user: 'u_sonar',
            password: 'u_sonar',
            driver: 'org.postgresql.Driver',
    )


    def getDbVersion() {
        def row = sql.firstRow('select version()')
        ["version": row.version]

        //return row
    }


//        List<Product> getAllProducts() {
//        String txt = 'select * from product'
//        sql.rows(txt).collect { row ->
//            new Product(id: row.id, name: row.name, price: row.price)
//        }
//    }
//
//    Product findProductById(int id) {
//        String txt = 'select * from product where id=?'
//        def row = sql.firstRow(txt, [id])
//
//        row ? new Product(id: row.id, name: row.name, price: row.price) : null
//    }


}
