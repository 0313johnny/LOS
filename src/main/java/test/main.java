package test;

import tcp_connect.NettyClient;

public class main {
    public static void main(String[] args) {
        NettyClient LocalServer = new NettyClient();
        LocalServer.start();
    }
}
