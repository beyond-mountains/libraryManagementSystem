package entity;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Maintain {

  private String manid;
  private String bookid;
  private String maintaintime;
  private String maintainbook;
  private String id;


  public String getManid() {
    return manid;
  }

  public void setManid(String manid) {
    this.manid = manid;
  }


  public String getBookid() {
    return bookid;
  }

  public void setBookid(String bookid) {
    this.bookid = bookid;
  }


  public String getMaintaintime() {
    return maintaintime;
  }

  public void setMaintaintime(String maintaintime) {
    this.maintaintime = maintaintime;
  }

  public void setMaintaintime(){
    long now=System.currentTimeMillis();
    Date date=new Date();
    date.setTime(now);
    SimpleDateFormat datef=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String time = datef.format(date);
    this.maintaintime = time;
  }

  public String getMaintainbook() {
    return maintainbook;
  }

  public void setMaintainbook(String maintainbook) {
    this.maintainbook = maintainbook;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
