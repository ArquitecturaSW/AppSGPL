package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class EntregableP {

    private int idProyecto;
    private int categoriaDoc;
    private String nombreDoc;
    private String versionDoc;
    private String comentarioDoc;
    private String urlDoc;

    public EntregableP(int categoriaDoc, int idProyecto, String nombreDoc, String versionDoc, String comentarioDoc, String urlDoc){
        this.categoriaDoc=categoriaDoc;
        this.idProyecto=idProyecto;
        this.nombreDoc=nombreDoc;
        this.versionDoc=versionDoc;
        this.comentarioDoc=comentarioDoc;
        this.urlDoc=urlDoc;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getCategoriaDoc() {
        return categoriaDoc;
    }

    public void setCategoriaDoc(int categoriaDoc) {
        this.categoriaDoc = categoriaDoc;
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
