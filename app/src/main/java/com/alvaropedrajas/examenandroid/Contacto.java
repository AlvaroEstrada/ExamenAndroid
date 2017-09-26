package com.alvaropedrajas.examenandroid;


import java.io.Serializable;

public class Contacto implements Serializable{
    private String nombre;
    private String mail;
    private Integer telefono;


    public Contacto(){
        super();
    }

    public Contacto(String nombre, Integer telefono){
        this.nombre = nombre;
        this.telefono = telefono;
    }

    public Contacto(String nombre, String mail, Integer telefono) {
        this.nombre = nombre;
        this.mail = mail;
        this.telefono = telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contacto)) return false;

        Contacto contacto = (Contacto) o;

        if (getNombre() != null ? !getNombre().equals(contacto.getNombre()) : contacto.getNombre() != null)
            return false;
        if (getMail() != null ? !getMail().equals(contacto.getMail()) : contacto.getMail() != null)
            return false;
        return getTelefono() != null ? getTelefono().equals(contacto.getTelefono()) : contacto.getTelefono() == null;

    }

    @Override
    public int hashCode() {
        int result = getNombre() != null ? getNombre().hashCode() : 0;
        result = 31 * result + (getMail() != null ? getMail().hashCode() : 0);
        result = 31 * result + (getTelefono() != null ? getTelefono().hashCode() : 0);
        return result;
    }
}

