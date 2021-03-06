package com.blstream.myhoard.db.model;

import java.util.Arrays;
import java.util.Date;

public class MediaDS {

	private int id;
	private byte[] file;
	private byte[] thumbnail;
	private ItemDS itemDS;
	private UserDS owner;
	private Date createdDate;

	public MediaDS() {
		super();
	}

	public MediaDS(int id, byte[] file, byte[] thumbnail, ItemDS itemDS,
			Date createdDate) {
		super();
		this.id = id;
		this.file = file;
		this.thumbnail = thumbnail;
		this.itemDS = itemDS;
		this.createdDate = createdDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public byte[] getFile() {
		return file;
	}

	public void setFile(byte[] file) {
		this.file = file;
	}

	public byte[] getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(byte[] thumbnail) {
		this.thumbnail = thumbnail;
	}

	public ItemDS getItemDS() {
		return itemDS;
	}

	public void setItemDS(ItemDS itemDS) {
		this.itemDS = itemDS;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return "MediaDS [id=" + id + ", file=" + Arrays.toString(file)
				+ ", thumbnail=" + Arrays.toString(thumbnail) + ", itemDS="
				+ itemDS + ", createdDate=" + createdDate + "]";
	}

	public UserDS getOwner() {
		return owner;
	}

	public void setOwner(UserDS owner) {
		this.owner = owner;
	}

}
