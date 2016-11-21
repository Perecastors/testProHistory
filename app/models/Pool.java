package models;

import play.db.jpa.Model;

public class Pool extends Model {

    public Champion championTop;
    public Champion championJungle;
    public Champion championMid;
    public Champion championAdc;
    public Champion championSupport;
    public int note;
}
