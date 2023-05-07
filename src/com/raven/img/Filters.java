package com.raven.img;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Filters extends Remote {
    byte[] filterImage(byte[] imageByte,int kernel) throws RemoteException;
}
