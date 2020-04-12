package com.springtest;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ChartReceive extends Thread {

    //格式

    private AudioFormat format = new AudioFormat(

            AudioFormat.Encoding.PCM_SIGNED, 44100.0f, 16, 1, 2, 44100.0f, false);


    //管道

    private SourceDataLine line;

    private byte[] data;


    public ChartReceive() {

        try {

            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

            line = (SourceDataLine) AudioSystem.getLine(info);

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    public void run() {

        System.out.println("receive threading start");

        int length=(int)(format.getFrameSize()*format.getFrameRate()/2.0f);

        try{line.open(format);line.start();

            DatagramSocket socket=new DatagramSocket(ChartSend.PORT);

            while(true){

                //数组的创建载什么时候，是否影响数据信息？

                data=new byte[length];

                DatagramPacket dp=new DatagramPacket(data,data.length);

                socket.receive(dp);

                line.write(data,0,data.length);

//                System.out.println("receive success "+new String(data,"UTF-8"));
                System.out.println("receive success");
            }

        }catch(Exception e){

            e.printStackTrace();

        }

    }

}
