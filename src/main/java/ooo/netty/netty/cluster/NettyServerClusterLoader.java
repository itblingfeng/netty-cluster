package ooo.netty.netty.cluster;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import ooo.netty.netty.registry.Registry;
import ooo.netty.netty.registry.RegistryFactory;
import ooo.netty.netty.registry.ZookeeperFactory;

public class NettyServerClusterLoader {
    final RegistryFactory factory = new ZookeeperFactory();
    final Registry registry = factory.createRegistry();

    public static void main(String[] args) {
        new NettyServerClusterLoader().start(9090);
        new NettyServerClusterLoader().start(9091);
    }

    public void start(final int port) {
        ServerBootstrap bootstrap = new ServerBootstrap();
        EventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group).channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    protected void initChannel(SocketChannel ch) throws Exception {

                    }
                });

        ChannelFuture f = bootstrap.bind("localhost", port);
        final String url = "localhost:" + port;
        f.addListener(new ChannelFutureListener() {
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    registry.registry("/netty/povider/" + url, url.getBytes());
                    System.out.println("注册成功");
                } else {
                    System.out.println("server启动失败");
                }
            }
        });
    }
}
