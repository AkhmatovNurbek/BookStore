package uz.jl.java_ee.service;

public abstract class Service<R> {
    protected final R dao;

    public Service(R dao) {
        this.dao = dao;
    }
}
