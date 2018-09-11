package product.report

import java.util.zip.ZipOutputStream

class ExcelController {

    ProductService productService
    ProductExcelService productExcelService

    def index() {
        response.setContentType('APPLICATION/OCTET-STREAM')
        response.setHeader('Content-Disposition', 'Attachment;Filename="report.zip"')

        Date beginDate = DateUtils.FORMATTER.parse(params.beginDate ?: "0000-00-00")
        Date endDate = DateUtils.FORMATTER.parse(params.endDate ?: "9999-99-99")
        Layout layout = Layout.valueOf(params.layout ?: "ANY")
        Integer companyId = params.int("companyId")

        ZipOutputStream zip = new ZipOutputStream(response.outputStream)

        getCompanies(companyId).each { company ->
            if (layout == Layout.BOX || layout == Layout.ANY) {
                productExcelService.exportZip(
                        zip,
                        "company-${company.id}-box.xlsx",
                        company,
                        productService.findAllByGTINLengthAndCompanyAndLastUpdateBetween(Layout.BOX.size, company.id, beginDate, endDate)
                )
            }
            if (layout == Layout.UNITY || layout == Layout.ANY) {
                productExcelService.exportZip(
                        zip,
                        "company-${company.id}-unity.xlsx",
                        company,
                        productService.findAllByGTINLengthAndCompanyAndLastUpdateBetween(Layout.UNITY.size, company.id, beginDate, endDate)
                )
            }
        }
        zip.close()
    }

    private static List<Company> getCompanies(Integer companyId) {
        companyId != null ? [Company.get(companyId)] : Company.findAll()
    }
}
