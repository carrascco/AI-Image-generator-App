package com.example.pmd_aiproject.model;

public class Image {
    private Integer id;
    private String user_name;
    private String prompt;
    private String fecha;

    public Image(Integer id, String user_name, String prompt, String fecha, byte[] image) {
        this.id = id;
        this.user_name = user_name;
        this.prompt = prompt;
        this.fecha = fecha;
        this.image = image;
    }

    private byte[] image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }



}
