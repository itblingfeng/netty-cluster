package ooo.netty.netty.registry;

import com.github.zkclient.IZkClient;

import java.util.List;

public class SingleZookeeperClient implements ZookeeperClient {
    private IZkClient zkClient;

    public SingleZookeeperClient(IZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public void create(String path, boolean ephemeral) {
        if (ephemeral) {
            zkClient.createEphemeral(path);
        } else {
            zkClient.createPersistent(path);
        }
    }

    public void create(String path, boolean ephemeral, byte[] data) {
        if (ephemeral) {
            zkClient.createEphemeral(path, data);
        } else {
            zkClient.createPersistent(path, data);
        }
    }

    public void create(String parentPath, String node, byte[] data) {
        zkClient.createPersistent(parentPath, true);
        zkClient.createEphemeral(parentPath + node, data);
    }

    public void delete(String path) {
        zkClient.delete(path);
    }

    public List<String> getChildren(String path) {
        return zkClient.getChildren(path);
    }

    public boolean isConnected() {
        return zkClient.isConnected();
    }

    public void close() {
        zkClient.close();
    }

    public void registryParent(String path) {
        zkClient.createPersistent(path, true);
    }

}
