package ooo.netty.netty.registry;

public interface Registry {

    public void registry(String path);

    public void registry(String path, byte[] data);

    public void close();
}
