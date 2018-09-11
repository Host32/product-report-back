package product.report

enum Layout {
    UNITY(13), BOX(14), ANY(0)

    int size

    private Layout(int size) {
        this.size = size
    }
}