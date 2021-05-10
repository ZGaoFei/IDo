package com.example.baselibrary.sendobj;

public abstract class HostRegister {

    abstract Host createHost();

    public void register() {
        Host host = createHost();
        RouteDispatch.getInstance().addHost(host);
    }
}
