package product.report

class BootStrap {

    def init = { servletContext ->
        Company banho = new Company(name: "Empresa de Produtos Para Banho").save()
        new Product(description: "Sabonete", gtin: "7891000200001", lastUpdate: DateUtils.FORMATTER.parse("2018-06-01"), company: banho).save()
        new Product(description: "Shampoo", gtin: "7891000200002", lastUpdate: DateUtils.FORMATTER.parse("2018-05-01"), company: banho).save()
        new Product(description: "Caixa Shampoo", gtin: "78910002000021", lastUpdate: DateUtils.FORMATTER.parse("2018-05-02"), company: banho).save()
        new Product(description: "Caixa Sabonete", gtin: "78910002000011", lastUpdate: DateUtils.FORMATTER.parse("2018-05-03"), company: banho).save()

        Company higienico = new Company(name: "Empresa de Produtos Higiênicos").save()
        new Product(description: "Escova de dente", gtin: "7891000200003", lastUpdate: DateUtils.FORMATTER.parse("2018-05-01"), company: higienico).save()
        new Product(description: "Caixa Escova", gtin: "78910002000031", lastUpdate: DateUtils.FORMATTER.parse("2018-05-02"), company: higienico).save()

        Company bebidas = new Company(name: "Empresa de Bebidas").save()
        new Product(description: "Cerveja Lata", gtin: "7891000200004", lastUpdate: DateUtils.FORMATTER.parse("2018-08-16"), company: bebidas).save()
        new Product(description: "Cerveja Garrafa 1L", gtin: "7891000200005", lastUpdate: DateUtils.FORMATTER.parse("2018-08-16"), company: bebidas).save()
        new Product(description: "Caixa Cerveja 12 Latas", gtin: "78910002000041", lastUpdate: DateUtils.FORMATTER.parse("2018-08-16"), company: bebidas).save()
    }
    def destroy = {
    }
}
