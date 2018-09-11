package product.report

import builders.dsl.spreadsheet.builder.poi.PoiSpreadsheetBuilder
import groovy.transform.CompileStatic

import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

@CompileStatic
class ProductExcelService {
    public static final String SHEET_NAME = "Products"
    public static final String HEADER_ID = "ID"
    public static final String HEADER_GTIN = "GTIN"
    public static final String HEADER_DESCRIPTION = "Description"
    public static final String HEADER_LAST_UPDATE = "Last Update"
    public static final String HEADER_COMPANY_ID = "Company ID"
    public static final String HEADER_COMPANY_NAME = "Company Name"
    public static final String EXCEL_FILE_SUFIX = ".xlsx"
    public static final String EXCEL_FILE_PREFIX = "products"

    void exportZip(ZipOutputStream zip, String entryName, Company company, List<Product> list) {
        zip.putNextEntry(new ZipEntry(entryName))
        ByteArrayOutputStream stream = new ByteArrayOutputStream()
        exportExcel(stream, company, list)
        zip.write(stream.toByteArray())
        stream.close()
    }

    void exportExcel(OutputStream outs, Company company, List<Product> list) {
        File file = File.createTempFile(EXCEL_FILE_PREFIX, EXCEL_FILE_SUFIX)
        PoiSpreadsheetBuilder.create(outs).build {
            apply ReportExcelStylesheet
            sheet(SHEET_NAME) { s ->
                row {
                    [HEADER_ID, HEADER_GTIN, HEADER_DESCRIPTION, HEADER_LAST_UPDATE, HEADER_COMPANY_ID, HEADER_COMPANY_NAME].each { header ->
                        cell {
                            value header
                            style ReportExcelStylesheet.STYLE_HEADER
                        }
                    }
                }
                list.each { product ->
                    row {
                        cell(product.id)
                        cell(product.gtin)
                        cell(product.description)
                        cell(DateUtils.FORMATTER.format(product.lastUpdate))
                        cell(company.id)
                        cell(company.name)
                    }
                }
            }
        }
        file
    }
}
