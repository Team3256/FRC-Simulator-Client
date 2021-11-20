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
    RobotData data;
    private ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

    public SwervedriveSimClient(String simAddress, int simPort) throws SocketException, UnknownHostException {
        socket = new DatagramSocket(5000);
        address = InetAddress.getByName(simAddress);
        port = simPort;
        data = new RobotData();
    }
    public void sendData() throws IOException {
        String json = ow.writeValueAsString(data);
        byte[] toSend = json.getBytes();
        DatagramPacket packet = new DatagramPacket(toSend, toSend.length, address, port);
        socket.send(packet);
    }
    public void checkData() throws JsonProcessingException {
        String json = ow.writeValueAsString(data);
        System.out.println(json);
    }
    public void close() {
        socket.close();
    }

    public static void main(String args[]) {}
}
