package com.hr.ent.model;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * 证书详情实体类
 * @author 800hr：zhuhui
 *
 * 2014-12-2
 */
@SuppressLint("ParcelCreator")
public class CertificateInfoBean implements Parcelable {
	private String user_id;//用户ID
	private String getyear;//拿到的年份
	private String getmonth;//拿到的月份
	private String cerlist;//
	private String certname;//证书名字
	private String scores;//证书描述
	private String cert_filekey;//
	private String resume_id;//简历ID
	private String resume_language;//简历语言
	private String certificate_id;//证书ID

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		// TODO Auto-generated method stub
		dest.writeString(user_id);
		dest.writeString(getyear);
		dest.writeString(getmonth);
		dest.writeString(cerlist);
		dest.writeString(certname);
		dest.writeString(scores);
		dest.writeString(cert_filekey);
		dest.writeString(resume_id);
		dest.writeString(resume_language);
		dest.writeString(certificate_id);

	}
	
	
	public static final Parcelable.Creator<CertificateInfoBean> CREATOR = new Parcelable.Creator<CertificateInfoBean>() {

		@Override
		public CertificateInfoBean createFromParcel(Parcel source) {
			// TODO Auto-generated method stub
			CertificateInfoBean certificateInfoBean = new CertificateInfoBean();
			certificateInfoBean.user_id = source.readString();
			certificateInfoBean.getyear = source.readString();
			certificateInfoBean.getmonth = source.readString();
			certificateInfoBean.cerlist = source.readString();
			
			certificateInfoBean.certname = source.readString();
			certificateInfoBean.scores = source.readString();
			certificateInfoBean.cert_filekey = source.readString();
			certificateInfoBean.resume_id = source.readString();
			certificateInfoBean.resume_language = source.readString();
			certificateInfoBean.certificate_id = source.readString();
			return certificateInfoBean;
		}

		@Override
		public CertificateInfoBean[] newArray(int size) {
			return new CertificateInfoBean[size];
		}
		
	};

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getGetyear() {
		return getyear;
	}

	public void setGetyear(String getyear) {
		this.getyear = getyear;
	}

	public String getGetmonth() {
		return getmonth;
	}

	public void setGetmonth(String getmonth) {
		this.getmonth = getmonth;
	}

	public String getCerlist() {
		return cerlist;
	}

	public void setCerlist(String cerlist) {
		this.cerlist = cerlist;
	}

	public String getCertname() {
		return certname;
	}

	public void setCertname(String certname) {
		this.certname = certname;
	}

	public String getScores() {
		return scores;
	}

	public void setScores(String scores) {
		this.scores = scores;
	}

	public String getCert_filekey() {
		return cert_filekey;
	}

	public void setCert_filekey(String cert_filekey) {
		this.cert_filekey = cert_filekey;
	}

	public String getResume_id() {
		return resume_id;
	}

	public void setResume_id(String resume_id) {
		this.resume_id = resume_id;
	}

	public String getResume_language() {
		return resume_language;
	}

	public void setResume_language(String resume_language) {
		this.resume_language = resume_language;
	}

	public String getCertificate_id() {
		return certificate_id;
	}

	public void setCertificate_id(String certificate_id) {
		this.certificate_id = certificate_id;
	}
}
