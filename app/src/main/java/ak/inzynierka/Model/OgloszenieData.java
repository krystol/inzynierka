package ak.inzynierka.Model;

import java.io.Serializable;
import java.util.Date;

public class OgloszenieData implements Serializable {

    private String autor;
    private String tytul;
    private String tresc;
    private Date date;

    public OgloszenieData(String autor, String tytul, String tresc, Date date) {
        this.autor = autor;
        this.tytul = tytul;
        this.tresc = tresc;
        this.date = date;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "OgloszenieData{" +
                "autor='" + autor + '\'' +
                ", tytul='" + tytul + '\'' +
                ", tresc='" + tresc + '\'' +
                ", date=" + date +
                '}';
    }
}
