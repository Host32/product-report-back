package product.report

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Company {
    String name

    static hasMany = [products: Product]
}
