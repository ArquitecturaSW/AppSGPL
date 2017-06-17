package arquitectura.proyecto.android.appsgpl.POJOS;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class Proyecto implements Parcelable, Serializable {

    private int idProyecto;
    private int idEstado;
    private int idEmpresa;
    private int idCategoriaP;
    private String nombreProyecto;
    private String codigoProyecto;
    private String descripcionProyecto;
    private String dateStart;
    private String dateEnd;
    private String dateEndFake;
    private int monto;

    public Proyecto(int idEmpresa,int idCategoriaP,String nombreProyecto,String codigoProyecto,String descripcionProyecto
    ,String dateStart,String dateEnd,int monto){
        this.idEmpresa=idEmpresa;
        this.idCategoriaP=idCategoriaP;
        this.nombreProyecto=nombreProyecto;
        this.codigoProyecto=codigoProyecto;
        this.descripcionProyecto=descripcionProyecto;
        this.dateStart=dateStart;
        this.dateEnd=dateEnd;
        this.monto=monto;
    }

    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
    }

    public int getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(int idEstado) {
        this.idEstado = idEstado;
    }

    public int getIdEmpresa() {
        return idEmpresa;
    }

    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }

    public int getIdCategoriaP() {
        return idCategoriaP;
    }

    public void setIdCategoriaP(int idCategoriaP) {
        this.idCategoriaP = idCategoriaP;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getCodigoProyecto() {
        return codigoProyecto;
    }

    public void setCodigoProyecto(String codigoProyecto) {
        this.codigoProyecto = codigoProyecto;
    }

    public String getDescripcionProyecto() {
        return descripcionProyecto;
    }

    public void setDescripcionProyecto(String descripcionProyecto) {
        this.descripcionProyecto = descripcionProyecto;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateEndFake() {
        return dateEndFake;
    }

    public void setDateEndFake(String dateEndFake) {
        this.dateEndFake = dateEndFake;
    }

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(getIdProyecto());
        dest.writeInt(getIdEstado());
        dest.writeInt(getIdEmpresa());
        dest.writeInt(getIdCategoriaP());
        dest.writeString(getNombreProyecto());
        dest.writeString(getCodigoProyecto());
        dest.writeString(getDescripcionProyecto());
        dest.writeString(getDateStart());
        dest.writeString(getDateEnd());
        dest.writeString(getDateEndFake());
        dest.writeInt(getMonto());

    }

    protected Proyecto(Parcel in) {
        setIdProyecto(in.readInt());
        setIdEstado(in.readInt());
        setIdCategoriaP(in.readInt());
        setIdEmpresa(in.readInt());
        setNombreProyecto(in.readString());
        setCodigoProyecto(in.readString());
        setDescripcionProyecto(in.readString());
        setDateStart(in.readString());
        setDateEnd(in.readString());
        setDateEndFake(in.readString());
        setMonto(in.readInt());
    }
    public static final Creator<Proyecto> CREATOR = new Creator<Proyecto>() {
        @Override
        public Proyecto createFromParcel(Parcel in) {
            return new Proyecto(in);
        }

        @Override
        public Proyecto[] newArray(int size) {
            return new Proyecto[size];
        }
    };

}
