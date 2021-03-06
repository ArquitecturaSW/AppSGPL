package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 18-Jun-17.
 */

public class Entregable {
    private String idDocumento;
    private String idCategoria;
    private String nombreDoc;
    private String versionDoc;
    private String dateCreated;
    private String comentarioDoc;
    private String urlDoc;

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(String idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreDoc() {
        return nombreDoc;
    }

    public void setNombreDoc(String nombreDoc) {
        this.nombreDoc = nombreDoc;
    }

    public String getVersionDoc() {
        return versionDoc;
    }

    public void setVersionDoc(String versionDoc) {
        this.versionDoc = versionDoc;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getComentarioDoc() {
        return comentarioDoc;
    }

    public void setComentarioDoc(String comentarioDoc) {
        this.comentarioDoc = comentarioDoc;
    }

    public String getUrlDoc() {
        return urlDoc;
    }

    public void setUrlDoc(String urlDoc) {
        this.urlDoc = urlDoc;
    }
}
