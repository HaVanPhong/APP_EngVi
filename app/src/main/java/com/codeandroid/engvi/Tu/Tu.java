package com.codeandroid.engvi.Tu;

public class Tu {
    private String tuAnh;
    private String phienAmAnh;
    private String loai;
    private String cacTuDongNghiaAnh;
    private String tuViet;
    private String cacTuDongNghiaViet;
    private String vdAnh;
    private String vdViet;

    public Tu() {
    }

    public Tu(String tuAnh, String phienAmAnh, String loaiAnh, String cacTuDongNghiaAnh, String tuViet, String cacTuDongNghiaViet, String vdAnh, String vdViet) {
        this.tuAnh = tuAnh;
        this.phienAmAnh = phienAmAnh;
        this.loai = loaiAnh;
        this.cacTuDongNghiaAnh = cacTuDongNghiaAnh;
        this.tuViet = tuViet;
        this.cacTuDongNghiaViet = cacTuDongNghiaViet;
        this.vdAnh = vdAnh;
        this.vdViet = vdViet;
    }


    public String getTuAnh() {
        return tuAnh;
    }

    public void setTuAnh(String tuAnh) {
        this.tuAnh = tuAnh;
    }

    public String getPhienAmAnh() {
        return phienAmAnh;
    }

    public void setPhienAmAnh(String phienAmAnh) {
        this.phienAmAnh = phienAmAnh;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loaiAnh) {
        this.loai = loaiAnh;
    }

    public String getCacTuDongNghiaAnh() {
        return cacTuDongNghiaAnh;
    }

    public void setCacTuDongNghiaAnh(String cacTuDongNghiaAnh) {
        this.cacTuDongNghiaAnh = cacTuDongNghiaAnh;
    }

    public String getTuViet() {
        return tuViet;
    }

    public void setTuViet(String tuViet) {
        this.tuViet = tuViet;
    }


    public String getCacTuDongNghiaViet() {
        return cacTuDongNghiaViet;
    }

    public void setCacTuDongNghiaViet(String cacTuDongNghiaViet) {
        this.cacTuDongNghiaViet = cacTuDongNghiaViet;
    }

    public String getVdAnh() {
        return vdAnh;
    }

    public void setVdAnh(String vdAnh) {
        this.vdAnh = vdAnh;
    }

    public String getVdViet() {
        return vdViet;
    }

    public void setVdViet(String vdViet) {
        this.vdViet = vdViet;
    }


    @Override
    public String toString() {
        return "Tu{" +
                "tuAnh='" + tuAnh + '\'' +
                ", phienAmAnh='" + phienAmAnh + '\'' +
                ", loai='" + loai + '\'' +
                ", cacTuDongNghiaAnh='" + cacTuDongNghiaAnh + '\'' +
                ", tuViet='" + tuViet + '\'' +
                ", cacTuDongNghiaViet='" + cacTuDongNghiaViet + '\'' +
                ", vdAnh='" + vdAnh + '\'' +
                ", vdViet='" + vdViet + '\'' +
                '}';
    }
}
