package arquitectura.proyecto.android.appsgpl.POJOS;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.text.NumberFormat;

/**
 * Created by Jair Barzola on 21-Apr-17.
 */

public class Proyecto implements Parcelable, Serializable {
    String monto;
    private int idProyecto;
    private String nombreProyecto;
    private String codigoProyecto;
    private String jefeProyecto;
    private String dateStart;
    private String dateEnd;
    private int montoProyecto;
    private String descripcionProyecto;
    private String statusProyecto;

    public Proyecto (int idProyecto,String nombreProyecto,String codigoProyecto,String jefeProyecto,int montoProyecto,String dateStart,String dateEnd,String statusProyecto,String descripcionProyecto){
        this.idProyecto=idProyecto;
        this.codigoProyecto=codigoProyecto;
        this.montoProyecto=montoProyecto;
        this.dateEnd=dateEnd;
        this.dateStart=dateStart;
        this.jefeProyecto=jefeProyecto;
        this.nombreProyecto=nombreProyecto;
        this.statusProyecto=statusProyecto;
        this.descripcionProyecto=descripcionProyecto;
    }



    public int getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(int idProyecto) {
        this.idProyecto = idProyecto;
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

    public String getStatusProyecto() {
        return statusProyecto;
    }

    public void setStatusProyecto(String statusProyecto) {
        this.statusProyecto = statusProyecto;
    }

    public String getJefeProyecto() {
        return jefeProyecto;
    }

    public void setJefeProyecto(String jefeProyecto) {
        this.jefeProyecto = jefeProyecto;
    }

    public int getMontoProyecto() {
        return montoProyecto;
    }

    public void setMontoProyecto(int montoProyecto) {
        this.montoProyecto = montoProyecto;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(monto);
        dest.writeInt(idProyecto);
        dest.writeString(nombreProyecto);
        dest.writeString(codigoProyecto);
        dest.writeString(jefeProyecto);
        dest.writeString(dateStart);
        dest.writeString(dateEnd);
        dest.writeInt(montoProyecto);
        dest.writeString(descripcionProyecto);
        dest.writeString(statusProyecto);
    }

    protected Proyecto(Parcel in) {
        monto = in.readString();
        idProyecto = in.readInt();
        nombreProyecto = in.readString();
        codigoProyecto = in.readString();
        jefeProyecto = in.readString();
        dateStart = in.readString();
        dateEnd = in.readString();
        montoProyecto = in.readInt();
        descripcionProyecto = in.readString();
        statusProyecto = in.readString();
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
