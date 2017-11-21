package com.example.hasee.myconnection1;


import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.lang.String;

public class MainActivity extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().
                detectDiskWrites().detectNetwork().penaltyLog().build());

        Button button =  this.findViewById(R.id.button);
        final EditText editText3= this.findViewById(R.id.edit3);
        final EditText editText_ip= this.findViewById(R.id.edit_ip);
        final EditText editText_port=this.findViewById(R.id.edit_port);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                try {
                    System.out.println("Client：Connecting");
                    //IP地址和端口号（对应服务端），我这的IP是本地路由器的IP地址
                    String ip1=editText_ip.getText().toString();
                    String port2=editText_port.getText().toString();
                    Integer port1=Integer.valueOf(port2);
                    Socket socket =new Socket(ip1, port1);
                    //发送给服务端的消息
                    String message = editText3.getText().toString();
                    try {
                        System.out.println("Client Sending: '" + message + "'");

                        //第二个参数为True则为自动flush
                        PrintWriter out = new PrintWriter(
                                new BufferedWriter(new OutputStreamWriter(
                                        socket.getOutputStream())), true);
                        out.println(message);
//                      out.flush();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        //关闭Socket
                        socket.close();
                        System.out.println("Client:Socket closed");
                    }
                } catch (UnknownHostException e1) {
                    e1.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
    }
}
