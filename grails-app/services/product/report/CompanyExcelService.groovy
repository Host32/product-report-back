package product.report

import builders.dsl.spreadsheet.builder.poi.PoiSpreadsheetBuilder
import groovy.transform.CompileStatic

@CompileStatic
class CompanyExcelService {
    public static final String SHEET_NAME = "Products"
    public static final String HEADER_ID = "ID"
    public static final String HEADER_NAME = "Name"
    public static final String EXCEL_FILE_SUFIX = ".xlsx"
    public static final String EXCEL_FILE_PREFIX = "companies"

    void exportExcel(OutputStream outs, List<Company> list) {
        File file = File.createTempFile(EXCEL_FILE_PREFIX, EXCEL_FILE_SUFIX)
        PoiSpreadsheetBuilder.create(outs).build {
            apply ReportExcelStylesheet
            sheet(SHEET_NAME) { s ->
                row {
                    [HEADER_ID, HEADER_NAME].each { header ->
                        cell {
                            value header
                            style ReportExcelStylesheet.STYLE_HEADER
                        }
                    }
                }
                list.each { company ->
                    row {
                        cell(company.id)
                        cell(company.name)
                    }
                }
            }
        }
        file
    }
}
