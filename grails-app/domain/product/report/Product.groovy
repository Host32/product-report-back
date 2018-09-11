package product.report

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Product {
    String gtin
    String description
    Date lastUpdate

    static constraints = {
        lastUpdate nullable: true
    }

    static belongsTo = [company: Company]
}
