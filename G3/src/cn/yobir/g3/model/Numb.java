package cn.yobir.g3.model;

public class Numb {
	private int id;
	private String mark;
	private String num;
	private String date;
	private String miss;
	private String missavg;
	public String getMiss() {
		return miss;
	}
	public void setMiss(String miss) {
		this.miss = miss;
	}
	public String getMissavg() {
		return missavg;
	}
	public void setMissavg(String missavg) {
		this.missavg = missavg;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMark() {
		return mark;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	@Override
	public String toString() {
		return "Numb [id=" + id + ", mark=" + mark + ", num=" + num + ", date=" + date + ", miss=" + miss + ", missavg="
				+ missavg + "]";
	}
	

}
