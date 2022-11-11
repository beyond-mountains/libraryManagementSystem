package entity;


import java.text.SimpleDateFormat;
import java.util.Date;

public class Operate {

  private String manid;
  private String stuid;
  private String operatetime;
  private String operatestu;
  private String id;


  public String getManid() {
    return manid;
  }

  public void setManid(String manid) {
    this.manid = manid;
  }


  public String getStuid() {
    return stuid;
  }

  public void setStuid(String stuid) {
    this.stuid = stuid;
  }


  public String getOperatetime() {
    return operatetime;
  }

  public void setOperatetime(String operatetime) {
    this.operatetime = operatetime;
  }

  public void setOperatetime(){
    long now=System.currentTimeMillis();
    Date date=new Date();
    date.setTime(now);
    SimpleDateFormat datef=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String time = datef.format(date);
    this.operatetime = time;
  }

  public String getOperatestu() {
    return operatestu;
  }

  public void setOperatestu(String operatestu) {
    this.operatestu = operatestu;
  }


  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
