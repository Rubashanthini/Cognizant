
public class FactoryTest {
    public static void main(String[] args) {
        System.out.println("=== Factory Method Pattern Test ===");

        DocumentFactory wordFactory = new WordDocumentFactory();
        Document wordDoc = wordFactory.createAndOpenDocument();
        wordDoc.save();

        System.out.println();

        DocumentFactory pdfFactory = new PdfDocumentFactory();
        Document pdfDoc = pdfFactory.createAndOpenDocument();
        pdfDoc.save();

        System.out.println();

        DocumentFactory excelFactory = new ExcelDocumentFactory();
        Document excelDoc = excelFactory.createAndOpenDocument();
        excelDoc.save();
    }
}
