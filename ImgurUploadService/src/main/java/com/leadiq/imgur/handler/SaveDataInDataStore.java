package com.leadiq.imgur.handler;

import com.leadiq.imgur.datastore.ImageStore;

public class SaveDataInDataStore {

	public static void storeImgIdWithUrl(String imgUrl) {

		ImageStore.imgLinkList.add(imgUrl);
	}

}
