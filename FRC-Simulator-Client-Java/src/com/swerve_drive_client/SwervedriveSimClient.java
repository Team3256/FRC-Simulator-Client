package com.swerve_drive_client;

import java.io.IOException;
import java.net.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;


public class SwervedriveSimClient {
    private DatagramSocket socket;
    private InetAddress address;
    private int port;
    public RobotData data;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
    private boolean connected = false;

    public SwervedriveSimClient() {
        data = new RobotData();
    }
    public void connect(String simAddress, int simPort) {
        try {
            socket = new DatagramSocket(5000);
            address = InetAddress.getByName(simAddress);
            port = simPort;
            connected = true;
        }
        catch (SocketException e) {
            System.out.println("Failed to connect:");
            System.out.println(e);
        }
        catch (UnknownHostException e) {
            System.out.println("Failed to connect:");
            System.out.println(e);
        }

    }
    public void sendData() throws IOException {
        if (connected) {
            String json = ow.writeValueAsString(data);
            byte[] toSend = json.getBytes();
            DatagramPacket packet = new DatagramPacket(toSend, toSend.length, address, port);
            socket.send(packet);
        }
    }
    public void checkData() {
        try {
            String json = ow.writeValueAsString(data);
            System.out.println(json);
        }
        catch (JsonProcessingException e) {
            System.out.println("Failed to convert data to json:");
            System.out.println(e);
        }
    }
    public void close() {
        socket.close();
    }

    public static void main(String args[]) {}
}
