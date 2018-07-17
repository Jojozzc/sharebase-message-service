package com.kele.service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISignInConfirm extends Remote {
    public String confirmAndGetUserId(String token) throws RemoteException;
}
