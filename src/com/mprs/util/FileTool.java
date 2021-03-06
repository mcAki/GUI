package com.mprs.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class FileTool {

	public static void copyPic(File src, File dst) {
		InputStream in = null;
		OutputStream out = null;

		try {
			in = new BufferedInputStream(new FileInputStream(src), Const.BUFFER_SIZE);
			out = new BufferedOutputStream(new FileOutputStream(dst),
					Const.BUFFER_SIZE);
			byte[] buffer = new byte[Const.BUFFER_SIZE];
			while (in.read(buffer) > 0) {
				out.write(buffer);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (null != in) {
					in.close();
				}
				if (null != out) {
					out.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	/**
	 * 复制文件
	 * @param uploadFileName
	 * @param realPath
	 * @param upLoadFile
	 * @return
	 */
	public static File copyFile(String uploadFileName,String realPath,File upLoadFile) {
		UUID uuid = UUID.randomUUID();
        // Dest File Name
        String fileName = uuid.toString()
                + ""
                + uploadFileName.substring(uploadFileName
                        .lastIndexOf("."));

        File dstFile = new File(realPath
                + "/" + fileName);
        FileTool.copyPic(upLoadFile, dstFile);
		return dstFile;
	}

	
	
}
