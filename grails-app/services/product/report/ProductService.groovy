package product.report

import grails.gorm.services.Query
import grails.gorm.services.Service

@Service(Product)
interface ProductService {

    Product get(Serializable id)

    List<Product> list(Map args)

    Long count()

    void delete(Serializable id)

    Product save(Product product)

    @Query("SELECT ${p} FROM ${Product p} INNER JOIN ${Company c = p.company} WHERE LENGTH(${p.gtin}) = $gtinLength AND ${c.id} = $companyId AND ${p.lastUpdate} BETWEEN $beginDate AND $endDate")
    List<Product> findAllByGTINLengthAndCompanyAndLastUpdateBetween(Integer gtinLength, Long companyId, Date beginDate, Date endDate)
}