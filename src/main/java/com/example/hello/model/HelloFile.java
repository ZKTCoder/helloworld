package com.example.hello.model;

import javax.persistence.*;

@Entity
@Table(name = "hello_file")
public class HelloFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "file")
    private byte[] file;

    public HelloFile() {
    }

    public HelloFile(Integer id, String type, byte[] file) {
        this.id = id;
        this.type = type;
        this.file = file;
    }

    public HelloFile(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }
}
