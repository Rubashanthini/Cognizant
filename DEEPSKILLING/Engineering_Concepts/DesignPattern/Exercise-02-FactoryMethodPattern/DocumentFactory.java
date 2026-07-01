
public abstract class DocumentFactory {

    /**
     * Factory method to be implemented by concrete factories.
     *
     * @return a new Document instance
     */
    public abstract Document createDocument();

   
    public Document createAndOpenDocument() {
        Document document = createDocument();
        document.open();
        return document;
    }
}
