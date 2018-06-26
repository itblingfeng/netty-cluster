package ooo.netty.netty.registry;

import java.util.List;

public interface ZookeeperClient {

    void create(String path, boolean ephemeral);

    void create(String path, boolean ephemeral, byte[] data);

    void create(String parentPath, String node, byte[] data);

    void delete(String path);

    List<String> getChildren(String path);

    boolean isConnected();

    void close();

    void registryParent(String path);
}
