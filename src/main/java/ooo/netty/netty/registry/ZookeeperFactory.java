package ooo.netty.netty.registry;

import com.github.zkclient.ZkClient;

import java.net.URL;

public class ZookeeperFactory implements RegistryFactory {
    public Registry createRegistry() {
        String url = ConfigUtil.REGISTRY_ADDRESS + ":" + ConfigUtil.REGISTRY_PORT;
        return new ZookeeperRegistry(new SingleZookeeperClient(new ZkClient(url)));
    }
}
