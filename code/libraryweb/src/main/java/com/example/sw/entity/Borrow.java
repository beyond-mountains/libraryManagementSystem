package com.example.sw.entity;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Borrow {

  private String stuid;
  private String bookid;
  private String lenddate;
  private String duedate;
  private String returndate;
  private String id;


  public String getStuid() {
    return stuid;
  }

  public void setStuid(String stuid) {
    this.stuid = stuid;
  }


  public String getBookid() {
    return bookid;
  }

  public void setBookid(String bookid) {
    this.bookid = bookid;
  }


  public String getLenddate() {
    return lenddate;
  }

  public void setLenddate(String lenddate) {
    this.lenddate = lenddate;
  }

  /*
  自动获取借书时间
   */
  public Date setLenddate(){
    long now=System.currentTimeMillis();
    Date date=new Date();
    date.setTime(now);
    SimpleDateFormat datef=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String time = datef.format(date);
    this.lenddate = time;
    return date;
  }

  public String getDuedate() {
    return duedate;
  }

  public void setDuedate(String duedate) {
    this.duedate = duedate;
  }

  /*
  自动计算还书时间，默认借阅时间为30天
   */
  public void setDuedate(Date date){
    String result = "";
    try {
      SimpleDateFormat sd = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
      Calendar cal = Calendar.getInstance();
      cal.setTime(date);
      cal.add(Calendar.DATE, 30);
      result = sd.format(cal.getTime());
      duedate = result;
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String getReturndate() {
    return returndate;
  }

  public void setReturndate(String returndate) {
    this.returndate = returndate;
  }

  public void setReturndate(){
    long now=System.currentTimeMillis();
    Date date=new Date();
    date.setTime(now);
    SimpleDateFormat datef=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    String time = datef.format(date);
    this.returndate = time;
  }

  /*
  判断还书是否超期
  返回  1超期 0未超期
   */
  public int overdue(){
    SimpleDateFormat datef=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    try {
      Date due = datef.parse(duedate);
      Date re = datef.parse(returndate);

      if(due.compareTo(re) >= 0){
        //未逾期
        return 0;
      }else{
        return 1;
      }
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return 1;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
