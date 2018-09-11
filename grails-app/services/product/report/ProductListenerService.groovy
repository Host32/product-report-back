package product.report

import grails.events.annotation.gorm.Listener
import groovy.transform.CompileStatic
import org.grails.datastore.mapping.engine.event.PreUpdateEvent

@CompileStatic
class ProductListenerService {

    @Listener(Product)
    void onProductPreUpdate(PreUpdateEvent event) {
        event.entityAccess.setProperty("lastUpdate", new Date())
    }
}
