package ooo.netty.netty.registry;

import com.github.zkclient.exception.ZkNoNodeException;
import com.github.zkclient.exception.ZkNodeExistsException;

public class ZookeeperRegistry implements Registry {
    private ZookeeperClient zkClient;

    ZookeeperRegistry(ZookeeperClient zkClient) {
        this.zkClient = zkClient;
    }

    public void registry(String path) {
        zkClient.create(path, true);
    }

    public void registry(String path, byte[] data) {
        try {
            zkClient.create(path, true, data);
        } catch (ZkNodeExistsException e) {
            throw e;
        } catch (ZkNoNodeException e) {
            String parenPath = path.substring(0, path.lastIndexOf("/"));
            String node = path.substring(path.lastIndexOf("/"));
            zkClient.create(parenPath, node, data);
        }
    }

    public void close() {
        zkClient.close();
    }

    public void registryParent(String path) {
        zkClient.registryParent(path);
    }


}
