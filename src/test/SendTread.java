package test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Socket;

public 
class SendTread implements Runnable {

	public SendTread(Socket[] socketPool, ByteArrayOutputStream outStream) {
		super();
		this.socketPool = socketPool;
		this.outStream = outStream;
	}

	Socket[] socketPool;
	ByteArrayOutputStream outStream;

	@Override
	public void run() {
		while(true){
			for(int i=0;i<10;i++){
				int randomNum = (int) Math.round(Math.random() * (socketPool.length-1 - 0) + 0);
				System.out.println("randomNum:" + randomNum);
				Socket socket = socketPool[randomNum];
				try {
					socket.getOutputStream().write(outStream.toByteArray());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					socket.getOutputStream().flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}
		// TODO Auto-generated method stub

	}

}
