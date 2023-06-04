package com.core.book;


import java.util.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "book")
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String tieude;
	private String tacgia;
	private String mota;
	
	private Date ngayphathanh;
	
	private int sotrang;
	private String theloai;
	private String anh;
	private String rate;
	private String nhanxet;
	private int soluongdaban;
	
	public int getSoluongdaban() {
		return soluongdaban;
	}

	public void setSoluongdaban(int soluongdaban) {
		this.soluongdaban = soluongdaban;
	}

	public Book() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public String getTieude() {
		return tieude;
	}

	public String getTacgia() {
		return tacgia;
	}

	public String getMota() {
		return mota;
	}

	public Date getNgayphathanh() {
		return ngayphathanh;
	}

	public int getSotrang() {
		return sotrang;
	}

	public String getTheloai() {
		return theloai;
	}

	public String getAnh() {
		return anh;
	}

	public String getRate() {
		return rate;
	}

	public String getNhanxet() {
		return nhanxet;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setTieude(String tieude) {
		this.tieude = tieude;
	}

	public void setTacgia(String tacgia) {
		this.tacgia = tacgia;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public void setNgayphathanh(Date ngayphathanh) {
		this.ngayphathanh = ngayphathanh;
	}

	public void setSotrang(int sotrang) {
		this.sotrang = sotrang;
	}

	public void setTheloai(String theloai) {
		this.theloai = theloai;
	}

	public void setAnh(String anh) {
		this.anh = anh;
	}

	public void setRate(String rate) {
		this.rate = rate;
	}

	public void setNhanxet(String nhanxet) {
		this.nhanxet = nhanxet;
	}

	public Book(int id, String tieude, String tacgia, String mota, Date ngayphathanh, int sotrang, String theloai,
			String anh, String rate, String nhanxet, int soluongdaban) {
		super();
		this.id = id;
		this.tieude = tieude;
		this.tacgia = tacgia;
		this.mota = mota;
		this.ngayphathanh = ngayphathanh;
		this.sotrang = sotrang;
		this.theloai = theloai;
		this.anh = anh;
		this.rate = rate;
		this.nhanxet = nhanxet;
		this.soluongdaban = soluongdaban;
	}

	
	
	
}
