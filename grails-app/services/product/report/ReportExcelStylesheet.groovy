package product.report

import builders.dsl.spreadsheet.api.FontStyle
import builders.dsl.spreadsheet.builder.api.CanDefineStyle
import builders.dsl.spreadsheet.builder.api.Stylesheet
import groovy.transform.CompileStatic

@CompileStatic
class ReportExcelStylesheet implements Stylesheet {

    public static final String STYLE_HEADER = "header"

    @Override
    void declareStyles(CanDefineStyle stylable) {
        stylable.style(STYLE_HEADER, { st ->
            st.font { f -> f.style(FontStyle.BOLD) }
        })
    }
}
