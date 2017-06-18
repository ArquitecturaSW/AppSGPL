package arquitectura.proyecto.android.appsgpl.POJOS;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class Entregable {

    private int idProyecto;
    private int categoriaDoc;
    private String nombreDoc;
    private String versionDoc;
    private String comentarioDoc;
    private String urlDoc;

    private int id_documento;
    private int id_categoria;
    private String nombre_documento;
    private String version_documento;
    private String dateCreated;
    private String url_documento;
    private String comentario_documento;



    public Entregable(int categoriaDoc,int idProyecto, String nombreDoc,String versionDoc, String comentarioDoc,String urlDoc){
        this.categoriaDoc=categoriaDoc;
        this.idProyecto=idProyecto;
        this.nombreDoc=nombreDoc;
        this.versionDoc=versionDoc;
        this.comentarioDoc=comentarioDoc;
        this.urlDoc=urlDoc;
    }

    public Entregable(String nombre_documento,
                      String version_documento, String dateCreated, String url_documento){

        this.nombre_documento=nombre_documento;
        this.version_documento=version_documento;
        this.dateCreated=dateCreated;
        this.url_documento=url_documento;
    }

    public int getId_documento() {
        return id_documento;
    }

    public void setId_documento(int id_documento) {
        this.id_documento = id_documento;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_documento() {
        return nombre_documento;
    }

    public void setNombre_documento(String nombre_documento) {
        this.nombre_documento = nombre_documento;
    }

    public String getVersion_documento() {
        return version_documento;
    }

    public void setVersion_documento(String version_documento) {
        this.version_documento = version_documento;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUrl_documento() {
        return url_documento;
    }

    public void setUrl_documento(String url_documento) {
        this.url_documento = url_documento;
    }

    public String getComentario_documento() {
        return comentario_documento;
    }

    public void setComentario_documento(String comentario_documento) {
        this.comentario_documento = comentario_documento;
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
