package edu.comillas.icai.gitt.pat.spring.p5.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Pez {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;

    @Column(nullable = false) String tipo;
    @Column(nullable = false) String color;
    @Column(nullable = false) String sexo;
    @Column(nullable = false) String correo;

    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    public void setColor(String color){
        this.color = color;
    }
    public void setSexo(String sexo){
        this.sexo = sexo;
    }
    public void setCorreo(String correo){
        this.correo = correo;
    }


}
