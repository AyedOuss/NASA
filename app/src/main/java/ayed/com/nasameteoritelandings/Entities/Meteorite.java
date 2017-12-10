package ayed.com.nasameteoritelandings.Entities;

/**
 * Created by oussama on 08/12/2017.
 */

public class Meteorite {

    private  int id ;
    private String name ;
    private String nametype ;
    private String recclass ;
    private String mass ;
    private String fall ;
    private String year ;
    private String reclat ;
    private String reclong ;
    private String latitude ;
    private String longitude ;

    public Meteorite() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNametype() {
        return nametype;
    }

    public void setNametype(String nametype) {
        this.nametype = nametype;
    }

    public String getRecclass() {
        return recclass;
    }

    public void setRecclass(String recclass) {
        this.recclass = recclass;
    }

    public String getMass() {
        return mass;
    }

    public void setMass(String mass) {
        this.mass = mass;
    }

    public String getFall() {
        return fall;
    }

    public void setFall(String fall) {
        this.fall = fall;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getReclat() {
        return reclat;
    }

    public void setReclat(String reclat) {
        this.reclat = reclat;
    }

    public String getReclong() {
        return reclong;
    }

    public void setReclong(String reclong) {
        this.reclong = reclong;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
