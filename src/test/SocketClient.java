package test;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket sc = new Socket("localhost", 9999);

			java.io.ByteArrayOutputStream bos = new java.io.ByteArrayOutputStream();
			java.io.DataOutputStream dos = new java.io.DataOutputStream(bos);
			//dos.writeInt(1000);
			String str = "aaaabbbbccccdddd";
			dos.writeInt(str.getBytes().length);
			// dos.writeBytes(str);
			dos.write(str.getBytes("GBK"));
			dos.flush();
			byte[] data = bos.toByteArray();
			// debugData("客户端==>服务端", data);
			sc.getOutputStream().write(data);
			sc.getOutputStream().flush();

//			InputStream is = sc.getInputStream();
//			DataInputStream dis = new DataInputStream(is);
//			int commandId = dis.readInt();
//			System.out.println(commandId);
//			int dataLength = dis.readInt();
//			System.out.println(dataLength);
//			byte[] b = new byte[dataLength];
//			dis.readFully(b);
//			String retStr = new String(b, "GBK");
//			System.out.println(retStr);

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void debugData(String dir, byte[] data) {
		System.out.println("调试数据:" + data.length + " " + dir);
		int count = 0;
		for (int i = 0; i < data.length; i++) {
			int b = data[i];
			if (b < 0) {
				b += 256;
			}
			// 16进制如果不满2位则补零
			String hexString = Integer.toHexString(b);
			hexString = (hexString.length() == 1) ? "0" + hexString : hexString;
			System.out.println(hexString + "  ");
			count++;
			if (count % 4 == 0) {
				System.out.println(" ");
			}
			if (count % 16 == 0) {
				System.out.println("\r\n");
			}
		}
		System.out.println("\r\n");
	}
}
