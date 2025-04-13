package com.mohyaseensidd.studynitp;

public class experiment {
    private String name;
    private String pdfUrl;

    private String ytUrl;

    public void setYtUrl(String ytUrl) {
        this.ytUrl = ytUrl;
    }

    public String getYtUrl() {
        return ytUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPdfUrl(String pdfUrl) {
        this.pdfUrl = pdfUrl;
    }

    public experiment(String name,String ytUrl, String pdfUrl) {
        this.name = name;
        this.ytUrl = ytUrl;
        this.pdfUrl = pdfUrl;
    }

    public String getName() {
        return name;
    }

    public String getPdfUrl() {
        return pdfUrl;
    }
}
